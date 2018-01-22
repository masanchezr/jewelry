package com.je.employee.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.employee.validators.OtherConceptValidator;
import com.je.services.otherconcepts.OtherConcept;
import com.je.services.otherconcepts.OthersConceptsService;

/**
 * The Class OthersConceptsController.
 */
@Controller
public class OthersConceptsController {

	/** The others concepts service. */
	@Autowired
	private OthersConceptsService othersConceptsService;

	/** The other concept validator. */
	@Autowired
	private OtherConceptValidator otherConceptValidator;

	/**
	 * Newconcept.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/newconcept")
	public ModelAndView newconcept() {
		ModelAndView model = new ModelAndView("newconcept");
		model.addObject("otherconcept", new OtherConcept());
		return model;
	}

	/**
	 * Saveconcept.
	 *
	 * @param otherconcept
	 *            the otherconcept
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/saveConcept")
	public ModelAndView saveconcept(@ModelAttribute("otherconcept") OtherConcept otherconcept, BindingResult result) {
		ModelAndView model = new ModelAndView();
		otherConceptValidator.validate(otherconcept, result);
		if (result.hasErrors()) {
			model.addObject("otherconcept", otherconcept);
			model.setViewName("newconcept");
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			otherconcept.setUser(user);
			model.addObject("daily", othersConceptsService.save(otherconcept));
			model.setViewName("dailyarrow");
			model.addObject("datedaily", new Date());
		}
		return model;
	}
}
