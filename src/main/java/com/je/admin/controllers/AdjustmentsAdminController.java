package com.je.admin.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.forms.SearchForm;
import com.je.services.adjustments.Adjustment;
import com.je.services.adjustments.AdjustmentService;
import com.je.services.places.PlaceService;
import com.je.utils.constants.ConstantsViews;
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

	@GetMapping("/searchsumadjustments")
	public ModelAndView searchSumAdjustments() {
		ModelAndView model = new ModelAndView(VIEWSEARCHSUMADJUSTMENTS);
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.FORMSEARCH, new SearchForm());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		return model;
	}

	@PostMapping("/sumadjustments")
	public ModelAndView sumAdjustments(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchForm searchForm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		adminSearchFormValidator.validate(searchForm, result);
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		if (result.hasErrors()) {
			model.addObject(ConstantsViews.FORMSEARCH, searchForm);
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
			model.setViewName(VIEWSEARCHSUMADJUSTMENTS);
		} else {
			Date from = DateUtil.getDate(searchForm.getDatefrom());
			Date until = DateUtil.getDate(searchForm.getDateuntil());
			model.addAllObjects(adjustmentService.sumAdjustmentByDates(from, until, searchForm.getPlace()));
			model.setViewName("admin/adjustments/sumadjustments");
		}
		return model;
	}

	@GetMapping("/searchadjustment")
	public ModelAndView searchAdjustment() {
		ModelAndView model = new ModelAndView(VIEWSEARCHADJUSTMENT);
		model.addObject(ConstantsViews.FORMADJUSTMENT, new Adjustment());
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	@PostMapping("/resultadjustment")
	public ModelAndView resultadjustment(@ModelAttribute(ConstantsViews.FORMADJUSTMENT) Adjustment adjustment) {
		ModelAndView model = new ModelAndView();
		Long idadjustment = adjustment.getIdadjustment();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		if (idadjustment == null || idadjustment.compareTo(1L) < 0) {
			model.setViewName(VIEWSEARCHADJUSTMENT);
		} else {
			adjustment = adjustmentService.findById(idadjustment);
			model.addObject(ConstantsViews.FORMADJUSTMENT, adjustment);
			model.setViewName("admin/adjustments/resultadjustment");
		}
		model.addObject(ConstantsViews.FORMADJUSTMENT, adjustment);
		return model;
	}

}
