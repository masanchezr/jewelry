package com.je.jsboot.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.jsboot.admin.forms.AdminForm;
import com.je.jsboot.admin.validators.HolidayValidator;
import com.je.jsboot.services.holidays.Holiday;
import com.je.jsboot.services.holidays.HolidayService;
import com.je.jsboot.services.places.PlaceService;
import com.je.jsboot.utils.constants.Constants;
import com.je.jsboot.utils.constants.ConstantsViews;

/**
 * The Class HolidaysController.
 */
@Controller
public class HolidaysController {

	/** The holiday service. */
	@Autowired
	private HolidayService holidayService;

	/** The place service. */
	@Autowired
	private PlaceService placeService;

	@Autowired
	private HolidayValidator validator;

	private static final String VIEWNEWHOLIDAY = "admin/holidays/newholiday";

	/**
	 * New holiday.
	 *
	 * @return the model and view
	 */
	@GetMapping("/newHoliday")
	public ModelAndView newHoliday() {
		ModelAndView model = new ModelAndView(VIEWNEWHOLIDAY);
		model.addObject(ConstantsViews.FORMHOLIDAY, new Holiday());
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlaces());
		return model;
	}

	/**
	 * Adds the holiday.
	 *
	 * @param holiday the holiday
	 * @return the model and view
	 */
	@PostMapping("/addHoliday")
	public ModelAndView addHoliday(Holiday holiday, BindingResult result) {
		ModelAndView model = new ModelAndView();
		validator.validate(model, result);
		if (result.hasErrors()) {
			model.addObject(ConstantsViews.FORMHOLIDAY, holiday);
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
			model.setViewName(VIEWNEWHOLIDAY);
		} else if (holidayService.existsHoliday(holiday)) {
			result.rejectValue(ConstantsViews.FORMHOLIDAY, "holidayexists");
			model.addObject(ConstantsViews.FORMHOLIDAY, holiday);
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
			model.setViewName(VIEWNEWHOLIDAY);
		} else {
			model.setViewName("admin/success");
			if (holiday.getPlace().getIdplace().equals(Constants.ALLPLACES)) {
				holidayService.addHolidayAllPlaces(holiday);
			} else {
				holidayService.addHoliday(holiday);
			}
		}
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	@GetMapping("/searchHolidays")
	public ModelAndView searchHolidays() {
		ModelAndView model = new ModelAndView("admin/holidays/searchholidays");
		model.addObject(ConstantsViews.FORMHOLIDAY, new Holiday());
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	@PostMapping("/resultHolidays")
	public ModelAndView resultHolidays(@ModelAttribute(ConstantsViews.FORMHOLIDAY) Holiday holiday) {
		ModelAndView model = new ModelAndView("admin/holidays/allholidays");
		model.addObject("holidays", holidayService.findByBetweenDates(holiday));
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * All holidays.
	 *
	 * @return the model and view
	 */
	@GetMapping("/allHolidays")
	public ModelAndView allHolidays() {
		ModelAndView model = new ModelAndView("admin/holidays/allholidays");
		List<Holiday> holidays = holidayService.findAll();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject("holidays", holidays);
		return model;
	}
}
