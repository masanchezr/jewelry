package com.je.jsboot.admin.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.jsboot.admin.forms.AdminForm;
import com.je.jsboot.admin.forms.BillingForm;
import com.je.jsboot.services.workshop.BillingService;
import com.je.jsboot.utils.constants.ConstantsViews;
import com.je.jsboot.utils.date.DateUtil;
import com.je.jsboot.utils.string.Util;
import com.je.jsboot.validators.SearchFormValidator;

@Controller
public class BillingAdminController {
	@Autowired
	private BillingService billingService;

	@Autowired
	private SearchFormValidator validator;

	private static final String FORMBILLING = "billingForm";
	private static final String VIEWBILLINGADMIN = "admin/workshop/billing";
	private static final String VIEWSEARCHBILL = "admin/workshop/searchbill";

	@GetMapping("/searchbill")
	public ModelAndView searchBill() {
		ModelAndView model = new ModelAndView(VIEWSEARCHBILL);
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(FORMBILLING, new BillingForm());
		return model;
	}

	@PostMapping("/bill")
	public ModelAndView bill(BillingForm billingForm, BindingResult result) {
		ModelAndView model = new ModelAndView(VIEWBILLINGADMIN);
		String sdate = billingForm.getDate();
		Date date;
		String jsp;
		validator.validate(billingForm, result);
		if (result.hasErrors()) {
			jsp = VIEWSEARCHBILL;
		} else {
			if (Util.isEmpty(sdate)) {
				date = new Date();
			} else {
				date = DateUtil.getDate(billingForm.getDate());
			}
			Calendar cdate = Calendar.getInstance();
			cdate.setTime(date);
			model.addAllObjects(billingService.getBill(cdate));
			jsp = VIEWBILLINGADMIN;
		}
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.setViewName(jsp);
		return model;
	}
}
