package com.je.employee.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.dbaccess.entities.BatteryEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.employee.validators.BatteryFormValidator;
import com.je.services.batteries.BatteriesService;
import com.je.services.dailies.DailyService;
import com.je.services.payment.PaymentService;
import com.je.services.places.PlaceService;
import com.je.services.sales.Sale;
import com.je.services.sales.SaleService;

@Controller
public class BatteriesController {

	@Autowired
	private BatteryFormValidator batteryFormValidator;

	@Autowired
	private BatteriesService batteriesService;

	@Autowired
	private SaleService saleService;

	@Autowired
	private PlaceService placeService;

	@Autowired
	private DailyService dailyService;

	@Autowired
	private PaymentService paymentService;

	@RequestMapping(value = "/employee/newsalebattery")
	public ModelAndView newsalebattery() {
		ModelAndView model = new ModelAndView("newsalebattery");
		model.addObject("batteryForm", new BatteryEntity());
		model.addObject("payments", paymentService.findAllActive());
		return model;
	}

	@RequestMapping(value = "/employee/savesalebattery")
	public ModelAndView savesalebattery(@ModelAttribute("batteryForm") BatteryEntity battery,
			HttpServletRequest request, BindingResult arg1) {
		ModelAndView model = new ModelAndView();
		batteryFormValidator.validate(battery, arg1);
		if (arg1.hasErrors()) {
			model.setViewName("newsalebattery");
			model.addObject("batteryForm", battery);
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			String ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
			PlaceEntity place = placeService.getPlaceUser(user);
			// comprobamos primero que no exista este número de venta
			Sale sale = saleService.searchByNumsaleAndPlace(battery.getNumsale(), place.getIdplace());
			if (sale != null) {
				arg1.rejectValue("numsale", "numrepited");
				model.setViewName("newsalebattery");
				model.addObject("batteryForm", battery);
			} else {
				Date today = new Date();
				model.setViewName("daily");
				battery.setPlace(place);
				batteriesService.saveSaleBattery(battery);
				model.addObject("daily", dailyService.getDaily(today, place, ipAddress));
				model.addObject("datedaily", today);
			}
		}
		return model;
	}
}
