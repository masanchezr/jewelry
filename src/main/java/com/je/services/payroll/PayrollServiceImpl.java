package com.je.services.payroll;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.PayrollEntity;
import com.je.dbaccess.entities.PayrolltypeEntity;
import com.je.dbaccess.entities.PlaceUserEntity;
import com.je.dbaccess.repositories.PayrollRepository;
import com.je.dbaccess.repositories.PayrollTypesRepository;
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
	private PayrollTypesRepository payrolltypesrepository;

	@Autowired
	private PlaceUserRepository placeUserRepository;

	@Override
	public Daily addPayroll(PayrollEntity payroll) {
		List<PlaceUserEntity> lplue = placeUserRepository.findByUsername(payroll.getUser().getUsername());
		payroll.setCreationdate(new Date());
		payrollrepository.save(payroll);
		return dailyService.getDaily(DateUtil.getDateFormated(new Date()), lplue.get(0).getPlace(), null);
	}

	@Override
	public boolean existsPayroll(PayrollEntity payroll) {
		payroll = payrollrepository.findByYearAndMonthAndUserAndPayrolltype(payroll.getYear(), payroll.getMonth(),
				payroll.getUser(), payroll.getPayrolltype());
		return payroll != null;
	}

	@Override
	public Iterable<PayrolltypeEntity> getPayrollTypes() {
		return payrolltypesrepository.findAll();
	}
}
