package com.je.services.registers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

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

	/**
	 * @Autowired private Mapper mapper;
	 * 
	 * 
	 *            public void in() {
	 * 
	 * 
	 *            SCardConexion cardConexion = new SCardConexion();
	 *            cardConexion.connect(0, "T=1"); CommandAPDU cpadu= new
	 *            CommandAPDU(); ResponseAPDU rapdu=cardConexion.(capdu);
	 *            List<InOut> inout = new ArrayList<InOut>();
	 *            Iterator<EmployeeEntity> ilin = lin.iterator(); InEntity in; Long
	 *            id; while (ilin.hasNext()) { id = ilin.next().getIdemployee(); if
	 *            (id != null) { in = new InEntity(); in.setDate(new Date());
	 *            in.setEmployee(employeesRepository.findById(id));
	 *            inRepository.save(in); inout.add(mapper.map(in, InOut.class)); } }
	 * 
	 *            }
	 * 
	 *            public List<Register> out(List<EmployeeEntity> lout) {
	 *            List<Register> inout = new ArrayList<Register>();
	 *            Iterator<EmployeeEntity> ilin = lout.iterator(); OutEntity in;
	 *            while (ilin.hasNext()) { in = new OutEntity(); in.setOutdate(new
	 *            Date()); in.setEmployee(mapper.map(ilin.next(),
	 *            EmployeeEntity.class)); outRepository.save(in);
	 *            inout.add(mapper.map(in, Register.class)); } return inout; }
	 **/
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
}