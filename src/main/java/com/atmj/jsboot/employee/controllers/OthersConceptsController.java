package com.atmj.jsboot.employee.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.employee.validators.OtherConceptValidator;
import com.atmj.jsboot.services.otherconcepts.OtherConcept;
import com.atmj.jsboot.services.otherconcepts.OtherConceptService;
import com.atmj.jsboot.utils.constants.ConstantsViews;

/**
 * The Class OthersConceptsController.
 */
@Controller
public class OthersConceptsController {

	/** The others concepts service. */

	private final OtherConceptService otherConceptService;

	private final OtherConceptValidator validator;

	private static final String FORMOTHERCONCEPT = "otherconcept";

	public OthersConceptsController(OtherConceptService otherConceptService, OtherConceptValidator validator) {
		this.otherConceptService = otherConceptService;
		this.validator = validator;
	}

	/**
	 * Newconcept.
	 *
	 * @return the model and view
	 */
	@GetMapping("/employee/newconcept")
	public ModelAndView newconcept() {
		ModelAndView model = new ModelAndView("employee/otherconcepts/newotherconcept");
		model.addObject(FORMOTHERCONCEPT, new OtherConcept());
		return model;
	}

	/**
	 * Saveconcept.
	 *
	 * @param otherconcept the otherconcept
	 * @param result       the result
	 * @return the model and view
	 */
	@PostMapping("/employee/saveConcept")
	public ModelAndView saveconcept(@ModelAttribute("otherconcept") OtherConcept otherconcept, BindingResult result) {
		ModelAndView model = new ModelAndView();
		validator.validate(otherconcept, result);
		if (result.hasErrors()) {
			model.addObject(FORMOTHERCONCEPT, otherconcept);
			model.setViewName("employee/otherconcepts/newotherconcept");
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			otherconcept.setUser(user);
			model.addObject(ConstantsViews.DAILY, otherConceptService.save(otherconcept));
			model.setViewName(ConstantsViews.VIEWDAILYARROW);
		}
		return model;
	}
}
