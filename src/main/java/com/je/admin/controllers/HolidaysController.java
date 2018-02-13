package com.je.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.admin.validators.HolidayValidator;
import com.je.services.holidays.Holiday;
import com.je.services.holidays.HolidayService;
import com.je.services.places.PlaceService;
import com.je.utils.constants.ConstantsJsp;

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
	private HolidayValidator holidayValidator;

	private static final String VIEWNEWHOLIDAY = "newholiday";

	/**
	 * New holiday.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/newHoliday")
	public ModelAndView newHoliday() {
		ModelAndView model = new ModelAndView(VIEWNEWHOLIDAY);
		model.addObject(ConstantsJsp.FORMHOLIDAY, new Holiday());
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlaces());
		return model;
	}

	/**
	 * Adds the holiday.
	 *
	 * @param holiday
	 *            the holiday
	 * @return the model and view
	 */
	@RequestMapping(value = "/addHoliday")
	public ModelAndView addHoliday(@ModelAttribute(ConstantsJsp.FORMHOLIDAY) Holiday holiday, BindingResult result) {
		ModelAndView model = new ModelAndView();
		holidayValidator.validate(holiday, result);
		if (result.hasErrors()) {
			model.addObject(ConstantsJsp.FORMHOLIDAY, holiday);
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlaces());
			model.setViewName(VIEWNEWHOLIDAY);
		} else if (holidayService.existsHoliday(holiday)) {
			result.rejectValue(ConstantsJsp.FORMHOLIDAY, "holidayexists");
			model.addObject(ConstantsJsp.FORMHOLIDAY, holiday);
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlaces());
			model.setViewName(VIEWNEWHOLIDAY);
		} else {
			model.setViewName(ConstantsJsp.SUCCESS);
			if (holiday.isAllplaces()) {
				holidayService.addHolidayAllPlaces(holiday);
			} else {
				holidayService.addHoliday(holiday);
			}
		}
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	@RequestMapping(value = "/searchHolidays")
	public ModelAndView searchHolidays() {
		ModelAndView model = new ModelAndView("searchholidays");
		model.addObject(ConstantsJsp.FORMHOLIDAY, new Holiday());
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	@RequestMapping(value = "/resultHolidays")
	public ModelAndView resultHolidays(@ModelAttribute(ConstantsJsp.FORMHOLIDAY) Holiday holiday) {
		ModelAndView model = new ModelAndView("allholidays");
		model.addObject("holidays", holidayService.findByBetweenDates(holiday));
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * All holidays.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/allHolidays")
	public ModelAndView allHolidays() {
		ModelAndView model = new ModelAndView("allholidays");
		List<Holiday> holidays = holidayService.findAll();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject("holidays", holidays);
		return model;
	}
}
