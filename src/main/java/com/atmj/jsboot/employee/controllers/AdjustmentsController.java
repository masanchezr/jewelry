package com.atmj.jsboot.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.services.adjustments.Adjustment;
import com.atmj.jsboot.services.adjustments.AdjustmentService;
import com.atmj.jsboot.services.payment.PaymentService;
import com.atmj.jsboot.utils.constants.ConstantsViews;

import jakarta.validation.Valid;

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

	/**
	 * Newadjustment.
	 *
	 * @return the model and view
	 */
	@GetMapping("/employee/newadjustment")
	public ModelAndView newadjustment() {
		ModelAndView model = new ModelAndView("employee/adjustments/newadjustment");
		model.addObject(ConstantsViews.FORMADJUSTMENT, new Adjustment());
		model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
		return model;
	}

	@GetMapping("/employee/retiredadjustment")
	public ModelAndView retiredadjustment() {
		ModelAndView model = new ModelAndView("employee/adjustments/retiredadjustment");
		model.addObject(ConstantsViews.FORMADJUSTMENT, new Adjustment());
		model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
		return model;
	}

	/**
	 * Save adjustment. El cliente retira el arreglo
	 * 
	 * @param adjustment the adjustment
	 * @param result     the result
	 * @return the model and view
	 */
	@PostMapping("/employee/saveAdjustment")
	public ModelAndView saveAdjustment(@Valid Adjustment adjustment, BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.setViewName("employee/adjustments/retiredadjustment");
			model.addObject(ConstantsViews.FORMADJUSTMENT, adjustment);
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			adjustment.setUser(user);
			model.addObject(ConstantsViews.DAILY, adjustmentService.save(adjustment));
			model.setViewName(ConstantsViews.VIEWDAILYARROW);
		}
		return model;
	}

	@PostMapping("/employee/saveWork")
	public ModelAndView saveWork(@Valid Adjustment adjustment, BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.setViewName("employee/adjustments/newadjustment");
			model.addObject(ConstantsViews.FORMADJUSTMENT, adjustment);
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			adjustment.setUser(user);
			model.addObject(ConstantsViews.DAILY, adjustmentService.saveWorkshop(adjustment));
			model.setViewName(ConstantsViews.VIEWDAILYARROW);
		}
		return model;
	}
}
