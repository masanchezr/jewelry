package com.je.services.registers;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.je.dbaccess.entities.PlaceUserEntity;
import com.je.dbaccess.entities.RegisterEntity;
import com.je.dbaccess.entities.UserEntity;
import com.je.dbaccess.repositories.HolidayRepository;
import com.je.dbaccess.repositories.PlaceUserRepository;
import com.je.dbaccess.repositories.RegisterRepository;
import com.je.dbaccess.repositories.UsersRepository;
import com.je.utils.date.DateUtil;

public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterRepository registerRepository;

	@Autowired
	private UsersRepository employeesRepository;

	/** The holiday repository. */
	@Autowired
	private HolidayRepository holidayRepository;

	@Autowired
	private PlaceUserRepository placeUserRepository;

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(RegisterServiceImpl.class);

	public void register(String user, String ipaddress) {
		Calendar calendar = Calendar.getInstance();
		List<PlaceUserEntity> placeuser = placeUserRepository.findByUsername(user);
		if (Calendar.SUNDAY != calendar.get(Calendar.DAY_OF_WEEK)
				&& Calendar.SATURDAY != calendar.get(Calendar.DAY_OF_WEEK)
				&& holidayRepository.findByHolidayAndPlace(calendar.getTime(), placeuser.get(0).getPlace()) == null) {
			Optional<UserEntity> employee = employeesRepository.findById(user);
			if (employee.isPresent()) {
				UserEntity userEntity = employee.get();
				RegisterEntity register = registerRepository.findByDateAndEmployee(DateUtil.getDateFormated(new Date()),
						userEntity);
				if (register == null) {
					register = new RegisterEntity();
					register.setEmployee(userEntity);
					register.setDate(new Date());
					register.setIpaddress(ipaddress);
				}
				if (register.getTimeinmorning() == null) {
					calendar.set(Calendar.HOUR_OF_DAY, 10);
					register.setTimeinmorning(calendar.getTime());
					registerRepository.save(register);
				} else if (register.getTimeoutmorning() == null) {
					calendar.set(Calendar.HOUR_OF_DAY, 14);
					register.setTimeoutmorning(calendar.getTime());
					registerRepository.save(register);
				} else if (register.getTimeinafternoon() == null) {
					calendar.set(Calendar.HOUR_OF_DAY, 16);
					register.setTimeinafternoon(calendar.getTime());
					registerRepository.save(register);
				} else if (register.getTimeoutafternoon() == null) {
					calendar.set(Calendar.HOUR_OF_DAY, 20);
					register.setTimeoutafternoon(calendar.getTime());
					registerRepository.save(register);
				}
			}
		}

	}

	public List<RegisterEntity> findByDates(String datefrom, String dateuntil) {
		Date from;
		Date until;
		if (dateuntil == null || dateuntil.isEmpty()) {
			until = new Date();
		} else {
			until = DateUtil.getDate(dateuntil);
		}
		from = DateUtil.getDate(datefrom);
		return registerRepository.findByDateBetweenOrderByDate(from, until);
	}

	@Override
	public void generatePdf(List<RegisterEntity> register, File file) {
		try (PdfWriter writer = new PdfWriter(file)) {
			PdfDocument pdf = new PdfDocument(writer);
			Document document = new Document(pdf);
			float[] columns = { 30f, 30f, 30f, 30f, 30f, 30f, 30f };
			Table table = new Table(columns);
			Iterator<RegisterEntity> iregister = register.iterator();
			RegisterEntity r;
			Paragraph para = new Paragraph(
					"Estos datos solo podrán cederse a terceros con la finalidad de dar cumplimiento a las obligaciones de carácter legal o contractual relacionadas con el desarrollo de la actividad laboral.");
			document.add(new Paragraph("NUMISGOLD S.L. Registro de empleados").setItalic());
			table.addCell(new Cell().add("DNI"));
			table.addCell(new Cell().add("Nombre"));
			table.addCell(new Cell().add("Fecha"));
			table.addCell(new Cell().add("Hora entrada por la mañana"));
			table.addCell(new Cell().add("Hora salida por la mañana"));
			table.addCell(new Cell().add("Hora entrada por la tarde"));
			table.addCell(new Cell().add("Hora salida por la tarde"));
			while (iregister.hasNext()) {
				r = iregister.next();
				table.addCell(new Cell().add(r.getEmployee().getDni()));
				table.addCell(new Cell().add(r.getEmployee().getName()));
				table.addCell(new Cell().add(r.getDate().toString()));
				table.addCell(new Cell().add(r.getTimeinmorning().toString()));
				table.addCell(new Cell().add(String.valueOf((r.getTimeoutmorning()))));
				table.addCell(new Cell().add(String.valueOf((r.getTimeinafternoon()))));
				table.addCell(new Cell().add(String.valueOf((r.getTimeoutafternoon()))));
			}
			document.add(table);
			document.add(para);
			document.close();
		} catch (IOException e) {
			log.error("No se ha podido generar el ticket.");
		}

	}
}