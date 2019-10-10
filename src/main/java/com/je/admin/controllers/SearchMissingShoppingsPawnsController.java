package com.je.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.admin.forms.SearchMissingNumbers;
import com.je.admin.validators.SearchMissingNumbersValidator;
import com.je.services.places.PlaceService;
import com.je.services.searchmissingnumbers.SearchMissingNumberService;
import com.je.utils.constants.ConstantsJsp;

@Controller
public class SearchMissingShoppingsPawnsController {

	@Autowired
	private SearchMissingNumberService searchMissingNumberService;

	/** The place service. */
	@Autowired
	private PlaceService placeService;

	@Autowired
	private SearchMissingNumbersValidator searchMissingNumbersValidator;

	private static final String SEARCHMISSINGSHOPPINGS = "searchmissingshoppings";

	@GetMapping("/searchPosibleRepeated")
	public ModelAndView searchMissingShoppings() {
		ModelAndView model = new ModelAndView(SEARCHMISSINGSHOPPINGS);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		model.addObject(SEARCHMISSINGSHOPPINGS, new SearchMissingNumbers());
		return model;
	}

	@PostMapping("/resultmissingshoppings")
	public ModelAndView resultMissingShoppings(
			@ModelAttribute(SEARCHMISSINGSHOPPINGS) SearchMissingNumbers searchmissingshoppings, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		searchMissingNumbersValidator.validate(searchmissingshoppings, result);
		if (result.hasErrors()) {
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
			model.addObject(SEARCHMISSINGSHOPPINGS, new SearchMissingNumbers());
			model.setViewName(SEARCHMISSINGSHOPPINGS);
		} else {
			model.addObject("nummissing", searchMissingNumberService.searchMissingShoppings(searchmissingshoppings));
			model.setViewName(ConstantsJsp.VIEWSEARCHMISSINGSHOPPINGS);
		}
		return model;
	}
}
