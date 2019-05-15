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
import com.je.utils.constants.ConstantsJsp;
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
		String ipAddress = request.getHeader(ConstantsJsp.XFORWARDEDFOR);
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		Daily daily = dailyService.getDaily(DateUtil.getDateFormated(new Date()), placeService.getPlaceUser(user),
				ipAddress);
		if (daily.getFinalamount() == null) {
			model.setViewName(ConstantsJsp.VIEWNOTDAILY);
		} else {
			model.addObject(ConstantsJsp.DAILY, daily);
			model.setViewName(ConstantsJsp.VIEWDAILYARROW);
			model.addObject(ConstantsJsp.DATEDAILY, new Date());
		}
		return model;
	}

	@RequestMapping(value = "/employee/searchdaily")
	public ModelAndView searchdaily() {
		ModelAndView model = new ModelAndView("searchdailyem");
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchForm());
		return model;
	}

	@RequestMapping(value = "/employee/beforeday{date}")
	public ModelAndView beforeday(@PathVariable(ConstantsJsp.DATE) String sdate, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		String ipAddress = request.getHeader(ConstantsJsp.XFORWARDEDFOR);
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		Calendar c = Calendar.getInstance();
		c.set(2015, 4, 17);
		Date date = DateUtil.getDate(sdate);
		boolean existdaily = false;
		while (!existdaily) {
			if (date.after(c.getTime())) {
				date = DateUtil.addDays(date, -1);
				Daily daily = dailyService.getDaily(date, placeService.getPlaceUser(user), ipAddress);
				if (daily.getFinalamount() == null) {
					model.setViewName(ConstantsJsp.VIEWNOTDAILY);
				} else {
					model.addObject(ConstantsJsp.DAILY, daily);
					model.setViewName(ConstantsJsp.VIEWDAILYARROWS);
					model.addObject(ConstantsJsp.DATEDAILY, date);
					existdaily = true;
				}
			} else {
				model.setViewName(ConstantsJsp.VIEWNOTDAILY);
				existdaily = true;
			}
		}
		return model;
	}

	@RequestMapping(value = "/employee/againday{date}")
	public ModelAndView againday(@PathVariable(ConstantsJsp.DATE) String sdate, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		Date date = DateUtil.getDate(sdate);
		boolean existdaily = false;
		while (!existdaily) {
			date = DateUtil.addDays(date, 1);
			if (date.compareTo(new Date()) < 0) {
				Daily daily = dailyService.getDaily(date, placeService.getPlaceUser(user), null);
				if (daily.getFinalamount() == null) {
					model.setViewName(ConstantsJsp.VIEWNOTDAILY);
				} else {
					String view;
					String stoday = DateUtil.getStringDateFormatddMMyyyy(new Date());
					sdate = DateUtil.getStringDateFormatddMMyyyy(date);
					if (stoday.compareTo(sdate) == 0) {
						view = ConstantsJsp.VIEWDAILYARROW;
					} else {
						view = ConstantsJsp.VIEWDAILYARROWS;
					}
					model.addObject(ConstantsJsp.DAILY, daily);
					model.setViewName(view);
					model.addObject(ConstantsJsp.DATEDAILY, date);
					existdaily = true;
				}
			} else {
				model.setViewName(ConstantsJsp.VIEWNOTDAILY);
				existdaily = true;
			}
		}
		return model;
	}

	@RequestMapping(value = "/employee/resultdaily")
	public ModelAndView resultdaily(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchForm searchForm,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		String ipAddress = request.getHeader(ConstantsJsp.XFORWARDEDFOR);
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		log.info("ip remote address:", ipAddress);
		String sdate = searchForm.getDatefrom();
		Date date;
		String view;
		Calendar c = Calendar.getInstance();
		if (Util.isEmpty(sdate)) {
			date = new Date();
			view = ConstantsJsp.VIEWDAILYARROW;
		} else {
			view = "dailyarrows";
			date = DateUtil.getDate(sdate);
		}
		if (date.before(c.getTime())) {
			Daily daily = dailyService.getDaily(date, placeService.getPlaceUser(user), ipAddress);
			if (daily.getFinalamount() == null) {
				model.setViewName(ConstantsJsp.VIEWNOTDAILY);
			} else {
				model.addObject(ConstantsJsp.DAILY, daily);
				model.setViewName(view);
				model.addObject(ConstantsJsp.DATEDAILY, date);
			}
		} else {
			model.setViewName(ConstantsJsp.VIEWNOTDAILY);
		}
		return model;
	}
}
