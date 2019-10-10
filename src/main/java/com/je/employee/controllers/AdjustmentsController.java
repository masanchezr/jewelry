package com.je.employee.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.employee.validators.AdjustmentValidator;
import com.je.services.adjustments.Adjustment;
import com.je.services.adjustments.AdjustmentService;
import com.je.services.payment.PaymentService;
import com.je.utils.constants.ConstantsJsp;
import com.je.utils.date.DateUtil;

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
	@GetMapping("/employee/newadjustment")
	public ModelAndView newadjustment() {
		ModelAndView model = new ModelAndView("employee/newadjustment");
		model.addObject(ConstantsJsp.FORMADJUSTMENT, new Adjustment());
		model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
		return model;
	}

	/**
	 * Save adjustment.
	 *
	 * @param adjustment the adjustment
	 * @param result     the result
	 * @return the model and view
	 */
	@PostMapping("/employee/saveAdjustment")
	public ModelAndView saveAdjustment(@ModelAttribute(ConstantsJsp.FORMADJUSTMENT) Adjustment adjustment,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		adjustmentValidator.validate(adjustment, result);
		if (result.hasErrors()) {
			model.setViewName("employee/newadjustment");
			model.addObject(ConstantsJsp.FORMADJUSTMENT, adjustment);
			model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			adjustment.setUser(user);
			model.addObject(ConstantsJsp.DAILY, adjustmentService.save(adjustment));
			model.setViewName(ConstantsJsp.VIEWDAILYARROW);
			model.addObject(ConstantsJsp.DATEDAILY, DateUtil.getStringDateddMMyyyy(new Date()));
		}
		return model;
	}
}
