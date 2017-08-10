package com.je.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.admin.validators.SearchMissingNumbersValidator;
import com.je.services.places.PlaceService;
import com.je.services.searchmissingnumbers.SearchMissingNumberService;
import com.je.services.searchmissingnumbers.SearchMissingNumbers;

@Controller
public class SearchMissingShoppingsPawnsController {

	@Autowired
	private SearchMissingNumberService searchMissingNumberService;

	/** The place service. */
	@Autowired
	private PlaceService placeService;

	@Autowired
	private SearchMissingNumbersValidator searchMissingNumbersValidator;

	@RequestMapping(value = "/searchPosibleRepeated")
	public ModelAndView searchMissingShoppings() {
		ModelAndView model = new ModelAndView("searchmissingshoppings");
		model.addObject("adminForm", new AdminForm());
		model.addObject("places", placeService.getAllPlaces());
		model.addObject("searchmissingshoppings", new SearchMissingNumbers());
		return model;
	}

	@RequestMapping(value = "/resultmissingshoppings")
	public ModelAndView resultMissingShoppings(
			@ModelAttribute("searchmissingshoppings") SearchMissingNumbers searchmissingshoppings,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject("adminForm", new AdminForm());
		searchMissingNumbersValidator.validate(searchmissingshoppings, result);
		if (result.hasErrors()) {
			model.addObject("places", placeService.getAllPlaces());
			model.addObject("searchmissingshoppings", new SearchMissingNumbers());
			model.setViewName("searchmissingshoppings");
		} else {
			model.addObject("nummissing", searchMissingNumberService.searchMissingShoppings(searchmissingshoppings));
			model.setViewName("resultmissingshoppings");
		}
		return model;
	}
}
