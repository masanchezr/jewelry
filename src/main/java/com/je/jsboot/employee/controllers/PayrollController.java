package com.je.jsboot.employee.controllers;

import java.util.Calendar;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.jsboot.dbaccess.entities.PayrollEntity;
import com.je.jsboot.dbaccess.entities.UserEntity;
import com.je.jsboot.employee.validators.PayrollFormValidator;
import com.je.jsboot.services.payroll.Payroll;
import com.je.jsboot.services.payroll.PayrollService;
import com.je.jsboot.utils.constants.Constants;
import com.je.jsboot.utils.constants.ConstantsViews;
import com.je.jsboot.utils.date.DateUtil;

@Controller
public class PayrollController {

	@Autowired
	private PayrollService payrollservice;

	@Autowired
	private ModelMapper mapper;

	private static final String FORMPAYROLL = "payrollForm";
	private static final String VIEWNEWPAYROLL = "employee/otherconcepts/payroll";

	@GetMapping("/employee/newpayroll")
	public ModelAndView newPayroll() {
		ModelAndView model = new ModelAndView(VIEWNEWPAYROLL);
		model.addObject(FORMPAYROLL, new Payroll());
		model.addObject("payrolltypes", payrollservice.getPayrollTypes());
		return model;
	}

	@PostMapping("/employee/savepayroll")
	public ModelAndView savePayroll(@Validated(PayrollFormValidator.class) @ModelAttribute(FORMPAYROLL) Payroll payroll,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		PayrollEntity payrollEntity = mapper.map(payroll, PayrollEntity.class);
		if (result.hasErrors()) {
			model.setViewName(VIEWNEWPAYROLL);
			model.addObject(FORMPAYROLL, payroll);
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			UserEntity userEntity = new UserEntity();
			Date date = DateUtil.getDate(payroll.getPayrolldate());
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			userEntity.setUsername(user);
			// compruebo si ya existe
			payrollEntity.setMonth(c.get(Calendar.MONTH) + 1);
			payrollEntity.setYear(c.get(Calendar.YEAR));
			payrollEntity.setUser(userEntity);
			if (!payrollservice.existsPayroll(payrollEntity)) {
				model.addObject(ConstantsViews.DAILY, payrollservice.addPayroll(payrollEntity));
				model.setViewName(ConstantsViews.VIEWDAILYARROW);
				model.addObject(ConstantsViews.DATEDAILY, DateUtil.getStringDateddMMyyyy(new Date()));
			} else {
				model.setViewName(VIEWNEWPAYROLL);
				model.addObject(FORMPAYROLL, payroll);
				result.rejectValue(Constants.AMOUNT, "payrollexists");
			}
		}
		return model;
	}
}
