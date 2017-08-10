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

@Controller
public class PayrollController {

	@Autowired
	private PayrollService payrollservice;

	@Autowired
	private PayrollFormValidator payrollFormValidator;

	@RequestMapping(value = "/employee/newpayroll")
	public ModelAndView newPayroll() {
		ModelAndView model = new ModelAndView("newpayroll");
		model.addObject("payrollForm", new Payroll());
		return model;
	}

	@RequestMapping(value = "/employee/savepayroll")
	public ModelAndView savePayroll(@ModelAttribute("payrollForm") Payroll payroll, BindingResult result) {
		ModelAndView model = new ModelAndView();
		payrollFormValidator.validate(payroll, result);
		if (result.hasErrors()) {
			model.setViewName("newpayroll");
			model.addObject("payrollForm", payroll);
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			// compruebo si ya existe
			payroll.setUser(user);
			if (!payrollservice.existsPayroll(payroll)) {
				model.addObject("daily", payrollservice.addPayroll(payroll));
				model.setViewName("daily");
				model.addObject("datedaily", new Date());
			} else {
				model.setViewName("newpayroll");
				model.addObject("payrollForm", payroll);
				result.rejectValue("amount", "payrollexists");
			}
		}
		return model;
	}
}
