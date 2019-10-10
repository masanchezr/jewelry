package com.je.admin.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.forms.SearchForm;
import com.je.services.adjustments.Adjustment;
import com.je.services.adjustments.AdjustmentService;
import com.je.services.places.PlaceService;
import com.je.utils.constants.ConstantsJsp;
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

	private static final String VIEWSEARCHADJUSTMENT = "admin/adjustments/searchadjustment";
	private static final String VIEWSEARCHSUMADJUSTMENTS = "admin/adjustments/searchsumadjustments";

	@PostMapping(value = "/searchsumadjustments")
	public ModelAndView searchSumAdjustments() {
		ModelAndView model = new ModelAndView(VIEWSEARCHSUMADJUSTMENTS);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchForm());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		return model;
	}

	@PostMapping(value = "/sumadjustments")
	public ModelAndView sumAdjustments(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchForm searchForm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		adminSearchFormValidator.validate(searchForm, result);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		if (result.hasErrors()) {
			model.addObject(ConstantsJsp.FORMSEARCH, searchForm);
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
			model.setViewName(VIEWSEARCHSUMADJUSTMENTS);
		} else {
			Date from = DateUtil.getDate(searchForm.getDatefrom());
			Date until = DateUtil.getDate(searchForm.getDateuntil());
			model.addAllObjects(adjustmentService.sumAdjustmentByDates(from, until, searchForm.getPlace()));
			model.setViewName("admin/adjustments/sumadjustments");
		}
		return model;
	}

	@PostMapping(value = "/searchadjustment")
	public ModelAndView searchAdjustment() {
		ModelAndView model = new ModelAndView(VIEWSEARCHADJUSTMENT);
		model.addObject(ConstantsJsp.FORMADJUSTMENT, new Adjustment());
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	@PostMapping(value = "/resultadjustment")
	public ModelAndView resultadjustment(@ModelAttribute(ConstantsJsp.FORMADJUSTMENT) Adjustment adjustment) {
		ModelAndView model = new ModelAndView();
		Long idadjustment = adjustment.getIdadjustment();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		if (idadjustment == null || idadjustment.compareTo(1L) < 0) {
			model.setViewName(VIEWSEARCHADJUSTMENT);
		} else {
			adjustment = adjustmentService.findById(idadjustment);
			model.addObject(ConstantsJsp.FORMADJUSTMENT, adjustment);
			model.setViewName("admin/adjustments/resultadjustment");
		}
		model.addObject(ConstantsJsp.FORMADJUSTMENT, adjustment);
		return model;
	}

}
