package com.je.workshop.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.services.workshop.BillingService;

/**
 * The Class BillingController.
 */
@Controller
public class BillingController {

	/** The billing service. */
	@Autowired
	private BillingService billingService;

	/**
	 * Billing.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/workshop/billing")
	public ModelAndView billing() {
		ModelAndView model = new ModelAndView("billing");
		Calendar current = Calendar.getInstance();
		current.setTime(new Date());
		model.addAllObjects(billingService.getBill(current));
		return model;
	}

	@RequestMapping(value = "/workshop/billingprevious")
	public ModelAndView billingprevious() {
		ModelAndView model = new ModelAndView("billing");
		Calendar current = Calendar.getInstance();
		current.setTime(new Date());
		current.add(Calendar.MONTH, -1);
		model.addAllObjects(billingService.getBill(current));
		return model;
	}
}
