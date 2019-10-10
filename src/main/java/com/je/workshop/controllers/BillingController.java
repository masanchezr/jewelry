package com.je.workshop.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
	@PostMapping(value = "/workshop/billing")
	public ModelAndView billing() {
		ModelAndView model = new ModelAndView("workshop/billing");
		Calendar current = Calendar.getInstance();
		current.setTime(new Date());
		model.addAllObjects(billingService.getBill(current));
		return model;
	}

	@PostMapping(value = "/workshop/billingprevious")
	public ModelAndView billingprevious() {
		ModelAndView model = new ModelAndView("workshop/billing");
		Calendar current = Calendar.getInstance();
		current.setTime(new Date());
		current.add(Calendar.MONTH, -1);
		model.addAllObjects(billingService.getBill(current));
		return model;
	}
}
