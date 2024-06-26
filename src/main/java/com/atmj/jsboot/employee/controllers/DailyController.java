package com.atmj.jsboot.employee.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.forms.SearchForm;
import com.atmj.jsboot.services.dailies.Daily;
import com.atmj.jsboot.services.dailies.DailyService;
import com.atmj.jsboot.services.places.PlaceService;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.date.DateUtil;
import com.atmj.jsboot.utils.string.Util;

import jakarta.servlet.http.HttpServletRequest;

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

	/**
	 * Daily.
	 *
	 * @return the model and view
	 */
	@GetMapping("/employee/daily")
	public ModelAndView daily(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		String ipAddress = request.getHeader(ConstantsViews.XFORWARDEDFOR);
		Date now = DateUtil.getDateFormated(new Date());
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		Daily daily = dailyService.getDaily(now, placeService.getPlaceUser(user), ipAddress);
		if (daily.getFinalamount() == null) {
			model.setViewName(ConstantsViews.VIEWNOTDAILY);
		} else {
			model.addObject(ConstantsViews.DAILY, daily);
			model.setViewName(ConstantsViews.VIEWDAILYARROW);
		}
		return model;
	}

	@GetMapping("/employee/searchdaily")
	public ModelAndView searchdaily() {
		ModelAndView model = new ModelAndView("employee/daily/searchdaily");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchForm());
		return model;
	}

	@GetMapping("/employee/beforeday{date}")
	public ModelAndView beforeday(@PathVariable(ConstantsViews.DATE) String sdate, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		String ipAddress = request.getHeader(ConstantsViews.XFORWARDEDFOR);
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
					model.setViewName(ConstantsViews.VIEWNOTDAILY);
				} else {
					model.addObject(ConstantsViews.DAILY, daily);
					model.setViewName(ConstantsViews.VIEWDAILYARROWS);
					existdaily = true;
				}
			} else {
				model.setViewName(ConstantsViews.VIEWNOTDAILY);
				existdaily = true;
			}
		}
		return model;
	}

	@GetMapping("/employee/againday{date}")
	public ModelAndView againday(@PathVariable(ConstantsViews.DATE) String sdate, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		Date date = DateUtil.getDate(sdate);
		boolean existdaily = false;
		while (!existdaily) {
			date = DateUtil.addDays(date, 1);
			if (date.compareTo(new Date()) < 0) {
				Daily daily = dailyService.getDaily(date, placeService.getPlaceUser(user), null);
				if (daily.getFinalamount() == null) {
					model.setViewName(ConstantsViews.VIEWNOTDAILY);
				} else {
					String view;
					String stoday = DateUtil.getStringDateddMMyyyy(new Date());
					sdate = DateUtil.getStringDateddMMyyyy(date);
					if (stoday.compareTo(sdate) == 0) {
						view = ConstantsViews.VIEWDAILYARROW;
					} else {
						view = ConstantsViews.VIEWDAILYARROWS;
					}
					model.addObject(ConstantsViews.DAILY, daily);
					model.setViewName(view);
					existdaily = true;
				}
			} else {
				model.setViewName(ConstantsViews.VIEWNOTDAILY);
				existdaily = true;
			}
		}
		return model;
	}

	@PostMapping("/employee/resultdaily")
	public ModelAndView resultdaily(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchForm searchForm,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		String ipAddress = request.getHeader(ConstantsViews.XFORWARDEDFOR);
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		String sdate = searchForm.getDatefrom();
		Date date;
		String view;
		Calendar c = Calendar.getInstance();
		if (Util.isEmpty(sdate)) {
			date = new Date();
			view = ConstantsViews.VIEWDAILYARROW;
		} else {
			view = ConstantsViews.VIEWDAILYARROWS;
			date = DateUtil.getDate(sdate);
		}
		if (date.before(c.getTime())) {
			Daily daily = dailyService.getDaily(date, placeService.getPlaceUser(user), ipAddress);
			if (daily.getFinalamount() == null) {
				model.setViewName(ConstantsViews.VIEWNOTDAILY);
			} else {
				model.addObject(ConstantsViews.DAILY, daily);
				model.setViewName(view);
			}
		} else {
			model.setViewName(ConstantsViews.VIEWNOTDAILY);
		}
		return model;
	}
}
