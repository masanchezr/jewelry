package com.je.admin.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.forms.SearchForm;
import com.je.services.adjustments.AdjustmentService;
import com.je.services.places.PlaceService;
import com.je.utils.date.DateUtil;
import com.je.validators.SearchFormValidator;

@Controller
public class AdjustmentsAdminController {

	/** The adjustment service. */
	@Autowired
	private AdjustmentService adjustmentService;

	/** The place service. */
	@Autowired
	private PlaceService placeService;

	@Autowired
	private SearchFormValidator adminSearchFormValidator;

	@RequestMapping(value = "/searchsumadjustments")
	public ModelAndView searchSumAdjustments() {
		ModelAndView model = new ModelAndView("searchsumadjustments");
		model.addObject("adminForm", new AdminForm());
		model.addObject("searchForm", new SearchForm());
		model.addObject("places", placeService.getAllPlaces());
		return model;
	}

	@RequestMapping(value = "/sumadjustments")
	public ModelAndView sumAdjustments(@ModelAttribute("searchForm") SearchForm searchForm, BindingResult result) {
		ModelAndView model = new ModelAndView();
		adminSearchFormValidator.validate(searchForm, result);
		model.addObject("adminForm", new AdminForm());
		if (result.hasErrors()) {
			model.addObject("searchForm", searchForm);
			model.addObject("places", placeService.getAllPlaces());
			model.setViewName("searchsumadjustments");
		} else {
			Date from = DateUtil.getDate(searchForm.getDatefrom());
			Date until = DateUtil.getDate(searchForm.getDateuntil());
			model.addAllObjects(adjustmentService.sumAdjustmentByDates(from, until, searchForm.getPlace()));
			model.setViewName("sumadjustments");
		}
		return model;
	}
}
