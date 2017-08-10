package com.je.admin.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.admin.forms.BillingForm;
import com.je.admin.validators.BillingFormValidator;
import com.je.services.workshop.BillingService;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

@Controller
public class BillingAdminController {
	@Autowired
	private BillingService billingService;

	@Autowired
	private BillingFormValidator billingFormValidator;

	@RequestMapping(value = "/searchbill")
	public ModelAndView searchBill() {
		ModelAndView model = new ModelAndView("searchbill");
		model.addObject("adminForm", new AdminForm());
		model.addObject("billingForm", new BillingForm());
		return model;
	}

	@RequestMapping(value = "/bill")
	public ModelAndView bill(
			@ModelAttribute("billingForm") BillingForm billingForm,
			BindingResult result) {
		ModelAndView model = new ModelAndView("billingadmin");
		String sdate = billingForm.getDate();
		Date date;
		String jsp;
		billingFormValidator.validate(billingForm, result);
		if (result.hasErrors()) {
			jsp = "searchbill";
		} else {
			if (Util.isEmpty(sdate)) {
				date = new Date();
			} else {
				date = DateUtil.getDate(billingForm.getDate());
			}
			Calendar cdate = Calendar.getInstance();
			cdate.setTime(date);
			model.addAllObjects(billingService.getBill(cdate));
			jsp = "billingadmin";
		}
		model.addObject("adminForm", new AdminForm());
		model.setViewName(jsp);
		return model;
	}
}
