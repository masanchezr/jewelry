package com.je.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.forms.SearchForm;
import com.je.services.registers.RegisterService;
import com.je.utils.constants.ConstantsJsp;
import com.je.validators.SearchFormValidator;

@Controller
public class RegisterEmployeesController {

	@Autowired
	private RegisterService registerService;

	@Autowired
	private SearchFormValidator adminSearchValidator;

	@RequestMapping(value = "/searchRegisterEmployees")
	public ModelAndView searchRegisterEmployees() {
		ModelAndView model = new ModelAndView("searchregisteremployees");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject("searchDateForm", new SearchForm());
		return model;
	}

	@RequestMapping(value = "/registeremployees")
	public ModelAndView registeremployees(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchForm form,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		adminSearchValidator.validate(form, result);
		if (result.hasErrors()) {
			model.addObject(ConstantsJsp.FORMSEARCH, new SearchForm());
			model.setViewName("searchregisteremployees");
		} else {
			model.addObject("register", registerService.findByDates(form.getDatefrom(), form.getDateuntil()));
			model.setViewName("register");
		}
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}
}
