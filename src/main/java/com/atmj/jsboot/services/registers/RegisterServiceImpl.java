package com.atmj.jsboot.services.registers;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.atmj.jsboot.dbaccess.entities.PlaceUserEntity;
import com.atmj.jsboot.dbaccess.entities.RegisterEntity;
import com.atmj.jsboot.dbaccess.entities.UserEntity;
import com.atmj.jsboot.dbaccess.repositories.HolidayRepository;
import com.atmj.jsboot.dbaccess.repositories.PlaceUserRepository;
import com.atmj.jsboot.dbaccess.repositories.RegisterRepository;
import com.atmj.jsboot.dbaccess.repositories.UsersRepository;
import com.atmj.jsboot.utils.date.DateUtil;

@Service
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
		List<PlaceUserEntity> placeuser = placeUserRepository.findByUser(employeesRepository.findByUsername(user));
		if (Calendar.SUNDAY != calendar.get(Calendar.DAY_OF_WEEK)
				&& Calendar.SATURDAY != calendar.get(Calendar.DAY_OF_WEEK)
				&& holidayRepository.findByHolidayAndPlace(calendar.getTime(), placeuser.get(0).getPlace()) == null) {
			UserEntity employee = employeesRepository.findByUsername(user);
			if (employee != null) {
				RegisterEntity register = registerRepository.findByDateAndEmployee(DateUtil.getDateFormated(new Date()),
						employee);
				if (register == null) {
					register = new RegisterEntity();
					register.setEmployee(employee);
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
			Paragraph dni = new Paragraph("DNI");
			Paragraph name = new Paragraph("Nombre");
			Paragraph date = new Paragraph("Fecha");
			Paragraph inmorning = new Paragraph("Hora entrada por la mañana");
			Paragraph outmorning = new Paragraph("Hora salida por la mañana");
			Paragraph inafternoon = new Paragraph("Hora entrada por la tarde");
			Paragraph outafternoon = new Paragraph("Hora salida por la tarde");
			document.add(new Paragraph("TOMEORO S.L. Registro de empleados").simulateItalic());
			table.addCell(new Cell().add(dni));
			table.addCell(new Cell().add(name));
			table.addCell(new Cell().add(date));
			table.addCell(new Cell().add(inmorning));
			table.addCell(new Cell().add(outmorning));
			table.addCell(new Cell().add(inafternoon));
			table.addCell(new Cell().add(outafternoon));
			while (iregister.hasNext()) {
				r = iregister.next();
				dni = new Paragraph(r.getEmployee().getDni());
				name = new Paragraph(r.getEmployee().getName());
				date = new Paragraph(r.getDate().toString());
				inmorning = new Paragraph(r.getTimeinmorning().toString());
				outmorning = new Paragraph(String.valueOf((r.getTimeoutmorning())));
				inafternoon = new Paragraph(String.valueOf((r.getTimeinafternoon())));
				outafternoon = new Paragraph(String.valueOf((r.getTimeoutafternoon())));
				table.addCell(new Cell().add(dni));
				table.addCell(new Cell().add(name));
				table.addCell(new Cell().add(date));
				table.addCell(new Cell().add(inmorning));
				table.addCell(new Cell().add(outmorning));
				table.addCell(new Cell().add(inafternoon));
				table.addCell(new Cell().add(outafternoon));
			}
			document.add(table);
			document.add(para);
			document.close();
		} catch (IOException e) {
			log.error("No se ha podido generar el ticket.");
		}

	}
}