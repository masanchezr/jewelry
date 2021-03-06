package com.je.jsboot.employee.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.jsboot.employee.validators.OtherConceptValidator;
import com.je.jsboot.services.otherconcepts.OtherConcept;
import com.je.jsboot.services.otherconcepts.OtherConceptService;
import com.je.jsboot.utils.constants.ConstantsViews;
import com.je.jsboot.utils.date.DateUtil;

/**
 * The Class OthersConceptsController.
 */
@Controller
public class OthersConceptsController {

	/** The others concepts service. */
	@Autowired
	private OtherConceptService otherConceptService;

	private static final String FORMOTHERCONCEPT = "otherconcept";

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
	public ModelAndView saveconcept(
			@Validated(OtherConceptValidator.class) @ModelAttribute("otherconcept") OtherConcept otherconcept,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.addObject(FORMOTHERCONCEPT, otherconcept);
			model.setViewName("employee/otherconcepts/newotherconcept");
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			otherconcept.setUser(user);
			model.addObject(ConstantsViews.DAILY, otherConceptService.save(otherconcept));
			model.setViewName(ConstantsViews.VIEWDAILYARROW);
			model.addObject(ConstantsViews.DATEDAILY, DateUtil.getStringDateddMMyyyy(new Date()));
		}
		return model;
	}
}
