package com.je.jsboot.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.jsboot.admin.forms.AdminForm;
import com.je.jsboot.admin.forms.SearchMissingNumbers;
import com.je.jsboot.admin.validators.SearchMissingNumbersValidator;
import com.je.jsboot.services.places.PlaceService;
import com.je.jsboot.services.searchmissingnumbers.SearchMissingNumberService;
import com.je.jsboot.utils.constants.ConstantsViews;

@Controller
public class SearchMissingShoppingsPawnsController {

	@Autowired
	private SearchMissingNumberService searchMissingNumberService;

	/** The place service. */
	@Autowired
	private PlaceService placeService;

	private static final String SEARCHMISSINGSHOPPINGS = "searchmissingshoppings";

	@GetMapping("/searchPosibleRepeated")
	public ModelAndView searchMissingShoppings() {
		ModelAndView model = new ModelAndView("admin/searchmissingshoppings/searchmissingshoppings");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		model.addObject(SEARCHMISSINGSHOPPINGS, new SearchMissingNumbers());
		return model;
	}

	@PostMapping("/resultmissingshoppings")
	public ModelAndView resultMissingShoppings(
			@Validated(SearchMissingNumbersValidator.class) @ModelAttribute(SEARCHMISSINGSHOPPINGS) SearchMissingNumbers searchmissingshoppings,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		if (result.hasErrors()) {
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
			model.addObject(SEARCHMISSINGSHOPPINGS, new SearchMissingNumbers());
			model.setViewName(SEARCHMISSINGSHOPPINGS);
		} else {
			model.addObject("nummissing", searchMissingNumberService.searchMissingShoppings(searchmissingshoppings));
			model.setViewName(ConstantsViews.VIEWSEARCHMISSINGSHOPPINGS);
		}
		return model;
	}
}
