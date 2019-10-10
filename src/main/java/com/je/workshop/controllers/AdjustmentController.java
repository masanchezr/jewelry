package com.je.workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.services.adjustments.Adjustment;
import com.je.services.adjustments.AdjustmentService;
import com.je.utils.constants.ConstantsJsp;
import com.je.workshop.validators.AdjustmentValidator;

/**
 * The Class AdjustmentController.
 */
@Controller
public class AdjustmentController {

	/** The adjustment service. */
	@Autowired
	private AdjustmentService adjustmentService;

	/** The adjustment form validator. */
	@Autowired
	private AdjustmentValidator adjustmentFormValidator;

	/**
	 * Newadjustment.
	 *
	 * @return the model and view
	 */
	@GetMapping("/workshop/newadjustment")
	public ModelAndView newadjustment() {
		ModelAndView model = new ModelAndView("workshop/newadjustment");
		model.addObject(ConstantsJsp.FORMADJUSTMENT, new Adjustment());
		return model;
	}

	/**
	 * Save adjustment.
	 *
	 * @param adjustment the adjustment
	 * @param result     the result
	 * @return the model and view
	 */
	@GetMapping("/workshop/saveAdjustment")
	public ModelAndView saveAdjustment(@ModelAttribute(ConstantsJsp.FORMADJUSTMENT) Adjustment adjustment,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		adjustmentFormValidator.validate(adjustment, result);
		if (result.hasErrors()) {
			model.setViewName("workshop/newadjustment");
			model.addObject(ConstantsJsp.FORMADJUSTMENT, adjustment);
		} else {
			adjustmentService.saveWorkshop(adjustment);
			model.setViewName("workshop/success");
		}
		return model;
	}
}
