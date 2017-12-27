package com.je.services.payroll;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.PayrollEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.repositories.PayrollRepository;
import com.je.dbaccess.repositories.PlaceUserRepository;
import com.je.services.dailies.Daily;
import com.je.services.dailies.DailyService;
import com.je.utils.date.DateUtil;

public class PayrollServiceImpl implements PayrollService {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	@Autowired
	private PayrollRepository payrollrepository;

	@Autowired
	private PlaceUserRepository placeUserRepository;

	@Override
	public Daily addPayroll(Payroll payroll) {
		PayrollEntity payrollentity = new PayrollEntity();
		PlaceEntity place = placeUserRepository.findByUsername(payroll.getUser()).get(0).getPlace();
		Date date = DateUtil.getDate(payroll.getPayrolldate());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String id = String.valueOf(calendar.get(Calendar.YEAR)).concat(String.valueOf(calendar.get(Calendar.MONTH) + 1))
				.concat(String.valueOf(place.getIdplace()));
		payrollentity.setAmount(new BigDecimal(payroll.getAmount()));
		payrollentity.setPlace(place);
		payrollentity.setCreationdate(new Date());
		payrollentity.setIdpayroll(Long.valueOf(id));
		payrollrepository.save(payrollentity);
		return dailyService.getDaily(new Date(), place, null);
	}

	@Override
	public boolean existsPayroll(Payroll payroll) {
		Date date = DateUtil.getDate(payroll.getPayrolldate());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String id = String.valueOf(calendar.get(Calendar.YEAR)).concat(String.valueOf(calendar.get(Calendar.MONTH) + 1))
				.concat(String
						.valueOf(placeUserRepository.findByUsername(payroll.getUser()).get(0).getPlace().getIdplace()));
		return payrollrepository.findById(Long.valueOf(id)).isPresent();
	}
}
