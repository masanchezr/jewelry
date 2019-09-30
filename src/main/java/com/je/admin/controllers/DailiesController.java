package com.je.admin.controllers;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
import com.je.services.dailies.DailyThread;
import com.je.services.places.PlaceService;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;
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

	private static final String VIEWSEARCHCALCULATEDAILIES = "admin/dailies/searchcalculatedailies";

	/**
	 * Daily place.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/dailyplace")
	public ModelAndView dailyPlace() {
		ModelAndView model = new ModelAndView("admin/dailies/searchdaily");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		model.addObject(ConstantsJsp.FORMSEARCHDAILY, new SearchDailyForm());
		return model;
	}

	@RequestMapping(value = "/searchcalculatedailies")
	public ModelAndView searchCalculateDailies() {
		ModelAndView model = new ModelAndView(VIEWSEARCHCALCULATEDAILIES);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		model.addObject(ConstantsJsp.FORMSEARCHDAILY, new SearchDailyForm());
		return model;
	}

	/**
	 * Search daily.
	 *
	 * @param sdf the sdf
	 * @return the model and view
	 */
	@RequestMapping(value = "/searchDaily")
	public ModelAndView searchDaily(@ModelAttribute(ConstantsJsp.FORMSEARCHDAILY) SearchDailyForm sdf,
			HttpServletRequest request, BindingResult arg1) {
		ModelAndView model;
		Date date = DateUtil.getDate(sdf.getDate());
		PlaceEntity place = sdf.getPlace();
		String ipAddress = request.getHeader(ConstantsJsp.XFORWARDEDFOR);
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		if (date == null) {
			return getDailyModel(ipAddress, DateUtil.getDateFormated(new Date()), place,
					ConstantsJsp.VIEWDAILYADMINARROW);
		} else {
			searchDailyFormValidator.validate(sdf, arg1);
			if (arg1.hasErrors()) {
				model = new ModelAndView();
				model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
				model.addObject(ConstantsJsp.FORMSEARCHDAILY, sdf);
				model.setViewName("admin/dailies/searchdaily");
				model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
				return model;
			} else {
				return getDailyModel(ipAddress, date, place, ConstantsJsp.VIEWDAILYADMINARROWS);
			}
		}
	}

	private ModelAndView getDailyModel(String ipAddress, Date date, PlaceEntity place, String view) {
		ModelAndView model = new ModelAndView();
		Daily daily = dailyService.getDaily(date, place, ipAddress);
		if (daily.getFinalamount() == null) {
			model.setViewName(ConstantsJsp.VIEWNOTDAILYADMIN);
		} else {
			model.addObject(ConstantsJsp.DAILY, daily);
			model.setViewName(view);
			model.addObject(ConstantsJsp.DATEDAILY, DateUtil.getStringDateddMMyyyy(date));
			model.addObject(Constants.PLACE, place.getIdplace());
		}
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	@RequestMapping(value = "/calculatedailies")
	public ModelAndView calculateDailies(@ModelAttribute(ConstantsJsp.FORMSEARCHDAILY) SearchDailyForm sdf,
			BindingResult arg1) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		searchDailyFormValidator.validate(sdf, arg1);
		if (arg1.hasErrors()) {
			model.setViewName(VIEWSEARCHCALCULATEDAILIES);
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		} else {
			String sdate = sdf.getDate();
			Date date = DateUtil.getDate(sdf.getDate());
			if (date != null) {
				Calendar c = Calendar.getInstance();
				c.set(2015, 2, 31);
				date = DateUtil.getDate(sdate);
				if (date.after(c.getTime())) {
					DailyThread dailythread = new DailyThread(dailyService, date, sdf.getPlace());
					dailythread.start();
					model.setViewName("admin/success");
				} else {
					model.setViewName(VIEWSEARCHCALCULATEDAILIES);
					model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
				}
			}
		}
		return model;
	}

	@RequestMapping(value = "/beforeday{date}-{place}")
	public ModelAndView beforeday(@PathVariable(ConstantsJsp.DATE) String sdate,
			@PathVariable(Constants.PLACE) long idplace, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String ipAddress = request.getHeader(ConstantsJsp.XFORWARDEDFOR);
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(idplace);
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		Calendar c = Calendar.getInstance();
		c.set(2015, 2, 31);
		Date date = DateUtil.getDate(sdate);
		boolean existdaily = false;
		while (!existdaily) {
			if (date.after(c.getTime())) {
				date = DateUtil.addDays(date, -1);
				Daily daily = dailyService.getDaily(date, place, ipAddress);
				if (daily.getFinalamount() == null) {
					model.setViewName(ConstantsJsp.VIEWNOTDAILYADMIN);
				} else {
					model.addObject(ConstantsJsp.DAILY, daily);
					model.setViewName("admin/dailies/dailyarrows");
					model.addObject(ConstantsJsp.DATEDAILY, date);
					model.addObject(Constants.PLACE, idplace);
					existdaily = true;
				}
			} else {
				model.setViewName(ConstantsJsp.VIEWNOTDAILYADMIN);
				existdaily = true;
			}
		}
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	@RequestMapping(value = "/againday{date}-{place}")
	public ModelAndView againday(@PathVariable(ConstantsJsp.DATE) String sdate,
			@PathVariable(Constants.PLACE) long idplace, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(idplace);
		String ipAddress = request.getHeader(ConstantsJsp.XFORWARDEDFOR);
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		Date date = DateUtil.getDate(sdate);
		boolean existdaily = false;
		while (!existdaily) {
			date = DateUtil.addDays(date, 1);
			if (date.compareTo(new Date()) < 0) {
				Daily daily = dailyService.getDaily(date, place, ipAddress);
				if (daily.getFinalamount() == null) {
					model.setViewName(ConstantsJsp.VIEWNOTDAILYADMIN);
				} else {
					String view;
					String stoday = DateUtil.getStringDateddMMyyyy(new Date());
					sdate = DateUtil.getStringDateddMMyyyy(date);
					if (stoday.compareTo(sdate) == 0) {
						view = ConstantsJsp.VIEWDAILYADMINARROW;
					} else {
						view = ConstantsJsp.VIEWDAILYADMINARROWS;
					}
					model.addObject(ConstantsJsp.DAILY, daily);
					model.setViewName(view);
					model.addObject(ConstantsJsp.DATEDAILY, date);
					model.addObject(Constants.PLACE, idplace);
					existdaily = true;
				}
			} else {
				model.setViewName(ConstantsJsp.VIEWNOTDAILYADMIN);
				existdaily = true;
			}
		}
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}
}
