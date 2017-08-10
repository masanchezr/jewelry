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

	/**
	 * New holiday.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/newHoliday")
	public ModelAndView newHoliday() {
		ModelAndView model = new ModelAndView("newholiday");
		model.addObject("holiday", new Holiday());
		model.addObject("adminForm", new AdminForm());
		model.addObject("places", placeService.getAllPlaces());
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
	public ModelAndView addHoliday(@ModelAttribute("holiday") Holiday holiday, BindingResult result) {
		ModelAndView model = new ModelAndView();
		holidayValidator.validate(holiday, result);
		if (result.hasErrors()) {
			model.addObject("holiday", holiday);
			model.addObject("places", placeService.getAllPlaces());
			model.setViewName("newholiday");
		} else if (holidayService.existsHoliday(holiday)) {
			result.rejectValue("holiday", "holidayexists");
			model.addObject("holiday", holiday);
			model.addObject("places", placeService.getAllPlaces());
			model.setViewName("newholiday");
		} else {
			model.setViewName("success");
			if (holiday.isAllplaces()) {
				holidayService.addHolidayAllPlaces(holiday);
			} else {
				holidayService.addHoliday(holiday);
			}
		}
		model.addObject("adminForm", new AdminForm());
		return model;
	}

	@RequestMapping(value = "/searchHolidays")
	public ModelAndView searchHolidays() {
		ModelAndView model = new ModelAndView("searchholidays");
		model.addObject("holiday", new Holiday());
		model.addObject("adminForm", new AdminForm());
		return model;
	}

	@RequestMapping(value = "/resultHolidays")
	public ModelAndView resultHolidays(@ModelAttribute("holiday") Holiday holiday) {
		ModelAndView model = new ModelAndView("allholidays");
		model.addObject("holidays", holidayService.findByBetweenDates(holiday));
		model.addObject("adminForm", new AdminForm());
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
		model.addObject("adminForm", new AdminForm());
		model.addObject("holidays", holidays);
		return model;
	}
}
