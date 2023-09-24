package com.atmj.jsboot.admin.controllers;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.admin.forms.AdminForm;
import com.atmj.jsboot.admin.forms.SearchDailyForm;
import com.atmj.jsboot.admin.validators.SearchDailyFormValidator;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.services.dailies.Daily;
import com.atmj.jsboot.services.dailies.DailyService;
import com.atmj.jsboot.services.dailies.DailyThread;
import com.atmj.jsboot.services.places.PlaceService;
import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.date.DateUtil;

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
	private SearchDailyFormValidator validator;

	private static final String VIEWSEARCHCALCULATEDAILIES = "admin/dailies/searchcalculatedailies";

	/**
	 * Daily place.
	 *
	 * @return the model and view
	 */
	@GetMapping("/dailyplace")
	public ModelAndView dailyPlace() {
		ModelAndView model = new ModelAndView("admin/dailies/searchdaily");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		model.addObject(ConstantsViews.FORMSEARCHDAILY, new SearchDailyForm());
		return model;
	}

	@GetMapping("/searchcalculatedailies")
	public ModelAndView searchCalculateDailies() {
		ModelAndView model = new ModelAndView(VIEWSEARCHCALCULATEDAILIES);
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		model.addObject(ConstantsViews.FORMSEARCHDAILY, new SearchDailyForm());
		return model;
	}

	/**
	 * Search daily.
	 *
	 * @param sdf the sdf
	 * @return the model and view
	 */
	@PostMapping("/searchDaily")
	public ModelAndView searchDaily(@ModelAttribute(ConstantsViews.FORMSEARCHDAILY) SearchDailyForm sdf,
			HttpServletRequest request, BindingResult arg1) {
		ModelAndView model;
		Date date = DateUtil.getDate(sdf.getDate());
		PlaceEntity place = sdf.getPlace();
		String ipAddress = request.getHeader(ConstantsViews.XFORWARDEDFOR);
		validator.validate(sdf, arg1);
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		if (date == null) {
			return getDailyModel(ipAddress, DateUtil.getDateFormated(new Date()), place,
					ConstantsViews.VIEWDAILYADMINARROW);
		} else if (arg1.hasErrors()) {
			model = new ModelAndView();
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
			model.addObject(ConstantsViews.FORMSEARCHDAILY, sdf);
			model.setViewName("admin/dailies/searchdaily");
			model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
			return model;
		} else {
			return getDailyModel(ipAddress, date, place, ConstantsViews.VIEWDAILYADMINARROWS);
		}
	}

	private ModelAndView getDailyModel(String ipAddress, Date date, PlaceEntity place, String view) {
		ModelAndView model = new ModelAndView();
		Daily daily = dailyService.getDaily(date, place, ipAddress);
		if (daily.getFinalamount() == null) {
			model.setViewName(ConstantsViews.VIEWNOTDAILYADMIN);
		} else {
			model.addObject(ConstantsViews.DAILY, daily);
			model.setViewName(view);
			model.addObject(ConstantsViews.DATEDAILY, DateUtil.getStringDateddMMyyyy(date));
			model.addObject(Constants.PLACE, place.getIdplace());
		}
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	@PostMapping("/calculatedailies")
	public ModelAndView calculateDailies(@ModelAttribute(ConstantsViews.FORMSEARCHDAILY) SearchDailyForm sdf,
			BindingResult arg1) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		validator.validate(sdf, arg1);
		if (arg1.hasErrors()) {
			model.setViewName(VIEWSEARCHCALCULATEDAILIES);
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
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
					model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
				}
			}
		}
		return model;
	}

	@GetMapping("/beforeday{date}/{place}")
	public ModelAndView beforeday(@PathVariable(ConstantsViews.DATE) String sdate,
			@PathVariable(Constants.PLACE) long idplace, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String ipAddress = request.getHeader(ConstantsViews.XFORWARDEDFOR);
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
					model.setViewName(ConstantsViews.VIEWNOTDAILYADMIN);
				} else {
					model.addObject(ConstantsViews.DAILY, daily);
					model.setViewName("admin/dailies/dailyarrows");
					model.addObject(ConstantsViews.DATEDAILY, DateUtil.getStringDateddMMyyyy(date));
					model.addObject(Constants.PLACE, idplace);
					existdaily = true;
				}
			} else {
				model.setViewName(ConstantsViews.VIEWNOTDAILYADMIN);
				existdaily = true;
			}
		}
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	@GetMapping("/againday{date}/{place}")
	public ModelAndView againday(@PathVariable(ConstantsViews.DATE) String sdate,
			@PathVariable(Constants.PLACE) long idplace, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(idplace);
		String ipAddress = request.getHeader(ConstantsViews.XFORWARDEDFOR);
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
					model.setViewName(ConstantsViews.VIEWNOTDAILYADMIN);
				} else {
					String view;
					String stoday = DateUtil.getStringDateddMMyyyy(new Date());
					sdate = DateUtil.getStringDateddMMyyyy(date);
					if (stoday.compareTo(sdate) == 0) {
						view = ConstantsViews.VIEWDAILYADMINARROW;
					} else {
						view = ConstantsViews.VIEWDAILYADMINARROWS;
					}
					model.addObject(ConstantsViews.DAILY, daily);
					model.setViewName(view);
					model.addObject(ConstantsViews.DATEDAILY, sdate);
					model.addObject(Constants.PLACE, idplace);
					existdaily = true;
				}
			} else {
				model.setViewName(ConstantsViews.VIEWNOTDAILYADMIN);
				existdaily = true;
			}
		}
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}
}
