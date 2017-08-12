package com.je.workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.services.adjustments.Adjustment;
import com.je.services.adjustments.AdjustmentService;
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
	@RequestMapping(value = "/workshop/newadjustment")
	public ModelAndView newadjustment() {
		ModelAndView model = new ModelAndView("newadjustmentworkshop");
		model.addObject("adjustment", new Adjustment());
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
	@RequestMapping(value = "/workshop/saveAdjustment")
	public ModelAndView saveAdjustment(@ModelAttribute("adjustment") Adjustment adjustment, BindingResult result) {
		ModelAndView model = new ModelAndView();
		adjustmentFormValidator.validate(adjustment, result);
		if (result.hasErrors()) {
			model.setViewName("newadjustmentworkshop");
			model.addObject("adjustment", adjustment);
		} else {
			adjustmentService.saveWorkshop(adjustment);
			model.setViewName("successworkshop");
		}
		return model;
	}
}