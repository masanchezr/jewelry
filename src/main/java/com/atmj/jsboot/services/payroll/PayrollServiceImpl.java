package com.atmj.jsboot.services.payroll;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.PayrollEntity;
import com.atmj.jsboot.dbaccess.entities.PayrolltypeEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceUserEntity;
import com.atmj.jsboot.dbaccess.entities.UserEntity;
import com.atmj.jsboot.dbaccess.repositories.PayrollRepository;
import com.atmj.jsboot.dbaccess.repositories.PayrollTypesRepository;
import com.atmj.jsboot.dbaccess.repositories.PlaceUserRepository;
import com.atmj.jsboot.dbaccess.repositories.UsersRepository;
import com.atmj.jsboot.services.dailies.Daily;
import com.atmj.jsboot.services.dailies.DailyService;
import com.atmj.jsboot.utils.date.DateUtil;

@Service
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

	@Autowired
	private UsersRepository usersrepository;

	@Override
	public Daily addPayroll(PayrollEntity payroll) {
		UserEntity user = usersrepository.findByUsername(payroll.getUser().getUsername());
		List<PlaceUserEntity> lplue = placeUserRepository.findByUser(user);
		payroll.setCreationdate(new Date());
		payroll.setUser(user);
		payrollrepository.save(payroll);
		return dailyService.getDaily(DateUtil.getDateFormated(new Date()), lplue.get(0).getPlace(), null);
	}

	@Override
	public boolean existsPayroll(PayrollEntity payroll) {
		UserEntity user = usersrepository.findByUsername(payroll.getUser().getUsername());
		payroll.setUser(user);
		payroll = payrollrepository.findByYearAndMonthAndUserAndPayrolltype(payroll.getYear(), payroll.getMonth(),
				payroll.getUser(), payroll.getPayrolltype());
		return payroll != null;
	}

	@Override
	public Iterable<PayrolltypeEntity> getPayrollTypes() {
		return payrolltypesrepository.findAll();
	}
}
