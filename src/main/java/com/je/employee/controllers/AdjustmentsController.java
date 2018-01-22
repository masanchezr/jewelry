package com.je.employee.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.employee.validators.AdjustmentValidator;
import com.je.services.adjustments.Adjustment;
import com.je.services.adjustments.AdjustmentService;
import com.je.services.payment.PaymentService;

/**
 * The Class AdjustmentsController.
 */
@Controller
public class AdjustmentsController {

	/** The adjustment service. */
	@Autowired
	private AdjustmentService adjustmentService;

	@Autowired
	private PaymentService paymentService;

	/** The adjustment validator. */
	@Autowired
	private AdjustmentValidator adjustmentValidator;

	/**
	 * Newadjustment.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/newadjustment")
	public ModelAndView newadjustment() {
		ModelAndView model = new ModelAndView("newadjustment");
		model.addObject("adjustment", new Adjustment());
		model.addObject("payments", paymentService.findAllActive());
		return model;
	}

	/**
	 * Save adjustment.
	 *
	 * @param adjustment
	 *            the adjustment
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/saveAdjustment")
	public ModelAndView saveAdjustment(@ModelAttribute("adjustment") Adjustment adjustment, BindingResult result) {
		ModelAndView model = new ModelAndView();
		adjustmentValidator.validate(adjustment, result);
		if (result.hasErrors()) {
			model.setViewName("newadjustment");
			model.addObject("adjustment", adjustment);
			model.addObject("payments", paymentService.findAllActive());
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			adjustment.setUser(user);
			model.addObject("daily", adjustmentService.save(adjustment));
			model.setViewName("dailyarrow");
			model.addObject("datedaily", new Date());
		}
		return model;
	}
}
