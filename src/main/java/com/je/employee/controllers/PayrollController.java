package com.je.employee.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.employee.validators.PayrollFormValidator;
import com.je.services.payroll.Payroll;
import com.je.services.payroll.PayrollService;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;

@Controller
public class PayrollController {

	@Autowired
	private PayrollService payrollservice;

	@Autowired
	private PayrollFormValidator payrollFormValidator;

	private static final String VIEWNEWPAYROLL = "newpayroll";
	private static final String FORMPAYROLL = "payrollForm";

	@RequestMapping(value = "/employee/newpayroll")
	public ModelAndView newPayroll() {
		ModelAndView model = new ModelAndView(VIEWNEWPAYROLL);
		model.addObject(FORMPAYROLL, new Payroll());
		return model;
	}

	@RequestMapping(value = "/employee/savepayroll")
	public ModelAndView savePayroll(@ModelAttribute(FORMPAYROLL) Payroll payroll, BindingResult result) {
		ModelAndView model = new ModelAndView();
		payrollFormValidator.validate(payroll, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWNEWPAYROLL);
			model.addObject(FORMPAYROLL, payroll);
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			// compruebo si ya existe
			payroll.setUser(user);
			if (!payrollservice.existsPayroll(payroll)) {
				model.addObject(ConstantsJsp.DAILY, payrollservice.addPayroll(payroll));
				model.setViewName(ConstantsJsp.VIEWDAILYARROW);
				model.addObject(ConstantsJsp.DATEDAILY, new Date());
			} else {
				model.setViewName(VIEWNEWPAYROLL);
				model.addObject(FORMPAYROLL, payroll);
				result.rejectValue(Constants.AMOUNT, "payrollexists");
			}
		}
		return model;
	}
}
