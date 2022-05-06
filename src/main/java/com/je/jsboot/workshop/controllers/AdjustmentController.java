package com.je.jsboot.workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.jsboot.services.adjustments.Adjustment;
import com.je.jsboot.services.adjustments.AdjustmentService;
import com.je.jsboot.utils.constants.ConstantsViews;
import com.je.jsboot.workshop.validators.AdjustmentValidator;

/**
 * The Class AdjustmentController.
 */
@Controller
public class AdjustmentController {

	/** The adjustment service. */
	@Autowired
	private AdjustmentService adjustmentService;

	@Autowired
	private AdjustmentValidator validator;

	/**
	 * Newadjustment.
	 *
	 * @return the model and view
	 */
	@GetMapping("/workshop/newadjustment")
	public ModelAndView newadjustment() {
		ModelAndView model = new ModelAndView("workshop/newadjustment");
		model.addObject(ConstantsViews.FORMADJUSTMENT, new Adjustment());
		return model;
	}

	/**
	 * Save adjustment.
	 *
	 * @param adjustment the adjustment
	 * @param result     the result
	 * @return the model and view
	 */
	@PostMapping("/workshop/saveAdjustment")
	public ModelAndView saveAdjustment(Adjustment adjustment, BindingResult result) {
		ModelAndView model = new ModelAndView();
		validator.validate(adjustment, result);
		if (result.hasErrors()) {
			model.setViewName("workshop/newadjustment");
			model.addObject(ConstantsViews.FORMADJUSTMENT, adjustment);
		} else {
			adjustmentService.saveWorkshop(adjustment);
			model.setViewName("workshop/success");
		}
		return model;
	}
}
