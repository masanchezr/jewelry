package com.je.admin.controllers;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.admin.forms.SearchDailyForm;
import com.je.admin.validators.SearchDailyFormValidator;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.services.dailies.Daily;
import com.je.services.dailies.DailyService;
import com.je.services.places.PlaceService;
import com.je.utils.date.DateUtil;

/**
 * The Class DailiesController.
 */
@Controller
public class DailiesController {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	/** The place service. */
	@Autowired
	private PlaceService placeService;

	@Autowired
	private SearchDailyFormValidator searchDailyFormValidator;

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(DailiesController.class);

	/**
	 * Daily place.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/dailyplace")
	public ModelAndView dailyPlace() {
		ModelAndView model = new ModelAndView("searchdaily");
		model.addObject("adminForm", new AdminForm());
		model.addObject("places", placeService.getAllPlaces());
		model.addObject("searchDailyForm", new SearchDailyForm());
		return model;
	}

	@RequestMapping(value = "/searchcalculatedailies")
	public ModelAndView searchCalculateDailies() {
		ModelAndView model = new ModelAndView("searchcalculatedailies");
		model.addObject("adminForm", new AdminForm());
		model.addObject("places", placeService.getAllPlaces());
		model.addObject("searchDailyForm", new SearchDailyForm());
		return model;
	}

	/**
	 * Search daily.
	 *
	 * @param sdf
	 *            the sdf
	 * @return the model and view
	 */
	@RequestMapping(value = "/searchDaily")
	public ModelAndView searchDaily(@ModelAttribute("searchDailyForm") SearchDailyForm sdf, HttpServletRequest request,
			BindingResult arg1) {
		ModelAndView model;
		Date date = DateUtil.getDate(sdf.getDate());
		PlaceEntity place = sdf.getPlace();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		if (date == null) {
			date = new Date();
			return getDailyModel(ipAddress, date, place);
		} else {
			searchDailyFormValidator.validate(sdf, arg1);
			if (arg1.hasErrors()) {
				model = new ModelAndView();
				model.addObject("places", placeService.getAllPlaces());
				model.addObject("searchDailyForm", sdf);
				model.setViewName("searchdaily");
				model.addObject("adminForm", new AdminForm());
				return model;
			} else {
				return getDailyModel(ipAddress, date, place);
			}
		}
	}

	private ModelAndView getDailyModel(String ipAddress, Date date, PlaceEntity place) {
		ModelAndView model = new ModelAndView();
		Daily daily = dailyService.getDaily(date, place, ipAddress);
		if (daily.getFinalamount() == null) {
			model.setViewName("notdailyadmin");
		} else {
			model.addObject("daily", daily);
			model.setViewName("dailyadmin");
			model.addObject("datedaily", date);
			model.addObject("place", place.getIdplace());
		}
		model.addObject("adminForm", new AdminForm());
		return model;
	}

	@RequestMapping(value = "/calculatedailies")
	public ModelAndView calculateDailies(@ModelAttribute("searchDailyForm") SearchDailyForm sdf,
			HttpServletRequest request, BindingResult arg1) {
		ModelAndView model = new ModelAndView();
		model.addObject("adminForm", new AdminForm());
		searchDailyFormValidator.validate(sdf, arg1);
		if (arg1.hasErrors()) {
			model.setViewName("searchcalculatedailies");
			model.addObject("places", placeService.getAllPlaces());
		} else {
			String sdate = sdf.getDate();
			Date date = DateUtil.getDate(sdf.getDate());
			if (date != null) {
				Calendar c = Calendar.getInstance();
				c.set(2015, 2, 31);
				date = DateUtil.getDate(sdate);
				if (date.after(c.getTime())) {
					dailyService.calculateDailies(date, sdf.getPlace());
					model.setViewName("success");
				} else {
					model.setViewName("searchcalculatedailies");
					model.addObject("places", placeService.getAllPlaces());
				}
			}
		}
		return model;
	}

	@RequestMapping(value = "/beforeday{date}/{place}")
	public ModelAndView beforeday(@PathVariable("date") String sdate, @PathVariable("place") long idplace,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(idplace);
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		log.info("ip remote address:", ipAddress);
		Calendar c = Calendar.getInstance();
		c.set(2015, 2, 31);
		Date date = DateUtil.getDate(sdate);
		boolean existdaily = false;
		while (!existdaily) {
			if (date.after(c.getTime())) {
				date = DateUtil.addDays(date, -1);
				Daily daily = dailyService.getDaily(date, place, ipAddress);
				if (daily.getFinalamount() == null) {
					model.setViewName("notdailyadmin");
				} else {
					model.addObject("daily", daily);
					model.setViewName("dailyadmin");
					model.addObject("datedaily", date);
					model.addObject("place", idplace);
					existdaily = true;
				}
			} else {
				model.setViewName("notdailyadmin");
				existdaily = true;
			}
		}
		model.addObject("adminForm", new AdminForm());
		return model;
	}

	@RequestMapping(value = "/againday{date}/{place}")
	public ModelAndView againday(@PathVariable("date") String sdate, @PathVariable("place") long idplace,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(idplace);
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		log.info("ip remote address:", ipAddress);
		Date date = DateUtil.getDate(sdate);
		boolean existdaily = false;
		while (!existdaily) {
			date = DateUtil.addDays(date, 1);
			if (date.compareTo(new Date()) < 0) {
				Daily daily = dailyService.getDaily(date, place, ipAddress);
				if (daily.getFinalamount() == null) {
					model.setViewName("notdailyadmin");
				} else {
					model.addObject("daily", daily);
					model.setViewName("dailyadmin");
					model.addObject("datedaily", date);
					model.addObject("place", idplace);
					existdaily = true;
				}
			} else {
				model.setViewName("notdailyadmin");
				existdaily = true;
			}
		}
		model.addObject("adminForm", new AdminForm());
		return model;
	}
}
