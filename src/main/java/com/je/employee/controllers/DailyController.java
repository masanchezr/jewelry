package com.je.employee.controllers;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.forms.SearchForm;
import com.je.services.dailies.Daily;
import com.je.services.dailies.DailyService;
import com.je.services.places.PlaceService;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

/**
 * The Class DailyController.
 */
@Controller
public class DailyController {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	@Autowired
	private PlaceService placeService;

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(DailyController.class);

	/**
	 * Daily.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/daily")
	public ModelAndView daily(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		log.info("ip remote address:", ipAddress);
		Daily daily = dailyService.getDaily(new Date(), placeService.getPlaceUser(user), ipAddress);
		if (daily.getFinalamount() == null) {
			model.setViewName("notdaily");
		} else {
			model.addObject("daily", daily);
			model.setViewName("daily");
			model.addObject("datedaily", new Date());
		}
		return model;
	}

	@RequestMapping(value = "/employee/searchdaily")
	public ModelAndView searchdaily() {
		ModelAndView model = new ModelAndView("searchdailyem");
		model.addObject("searchForm", new SearchForm());
		return model;
	}

	@RequestMapping(value = "/employee/beforeday{date}")
	public ModelAndView beforeday(@PathVariable("date") String sdate, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		log.info("ip remote address:", ipAddress);
		Calendar c = Calendar.getInstance();
		c.set(2015, 4, 17);
		Date date = DateUtil.getDate(sdate);
		boolean existdaily = false;
		while (!existdaily) {
			if (date.after(c.getTime())) {
				date = DateUtil.addDays(date, -1);
				Daily daily = dailyService.getDaily(date, placeService.getPlaceUser(user), ipAddress);
				if (daily.getFinalamount() == null) {
					model.setViewName("notdaily");
				} else {
					model.addObject("daily", daily);
					model.setViewName("daily");
					model.addObject("datedaily", date);
					existdaily = true;
				}
			} else {
				model.setViewName("notdaily");
				existdaily = true;
			}
		}
		return model;
	}

	@RequestMapping(value = "/employee/againday{date}")
	public ModelAndView againday(@PathVariable("date") String sdate, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
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
				Daily daily = dailyService.getDaily(date, placeService.getPlaceUser(user), null);
				if (daily.getFinalamount() == null) {
					model.setViewName("notdaily");
				} else {
					model.addObject("daily", daily);
					model.setViewName("daily");
					model.addObject("datedaily", date);
					existdaily = true;
				}
			} else {
				model.setViewName("notdaily");
				existdaily = true;
			}
		}
		return model;
	}

	@RequestMapping(value = "/employee/resultdaily")
	public ModelAndView resultdaily(@ModelAttribute("searchForm") SearchForm searchForm,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		log.info("ip remote address:", ipAddress);
		String sdate = searchForm.getDatefrom();
		Date date;
		if (Util.isEmpty(sdate)) {
			date = new Date();
		} else {
			Calendar c = Calendar.getInstance();
			c.set(2015, 4, 17);
			date = DateUtil.getDate(sdate);
			if (date.after(c.getTime())) {
				Daily daily = dailyService.getDaily(date, placeService.getPlaceUser(user), ipAddress);
				if (daily.getFinalamount() == null) {
					model.setViewName("notdaily");
				} else {
					model.addObject("daily", daily);
					model.setViewName("daily");
					model.addObject("datedaily", date);
				}
			} else {
				model.setViewName("notdaily");
			}
		}
		return model;
	}
}
