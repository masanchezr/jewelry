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
import com.je.forms.Sale;
import com.je.services.batteries.BatteriesService;
import com.je.services.dailies.DailyService;
import com.je.services.payment.PaymentService;
import com.je.services.places.PlaceService;
import com.je.services.sales.SaleService;
import com.je.utils.constants.ConstantsJsp;
import com.je.utils.date.DateUtil;

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

	private static final String BATTERYFORM = "batteryForm";
	private static final String VIEWNEWSALEBATTERY = "employee/sales/newsalebattery";

	@RequestMapping(value = "/employee/newsalebattery")
	public ModelAndView newsalebattery() {
		ModelAndView model = new ModelAndView(VIEWNEWSALEBATTERY);
		model.addObject(BATTERYFORM, new BatteryEntity());
		model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
		return model;
	}

	@RequestMapping(value = "/employee/savesalebattery")
	public ModelAndView savesalebattery(@ModelAttribute("batteryForm") BatteryEntity battery,
			HttpServletRequest request, BindingResult arg1) {
		ModelAndView model = new ModelAndView();
		batteryFormValidator.validate(battery, arg1);
		if (arg1.hasErrors()) {
			model.setViewName(VIEWNEWSALEBATTERY);
			model.addObject(BATTERYFORM, battery);
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			String ipAddress = request.getHeader(ConstantsJsp.XFORWARDEDFOR);
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
			PlaceEntity place = placeService.getPlaceUser(user);
			// comprobamos primero que no exista este número de venta
			Sale sale = saleService.searchByNumsaleAndPlace(battery.getNumsale(), place.getIdplace());
			if (sale != null) {
				arg1.rejectValue(ConstantsJsp.NUMSALE, "numrepited");
				model.setViewName(VIEWNEWSALEBATTERY);
				model.addObject(BATTERYFORM, battery);
			} else {
				model.setViewName(ConstantsJsp.VIEWDAILYARROW);
				battery.setPlace(place);
				batteriesService.saveSaleBattery(battery);
				model.addObject(ConstantsJsp.DAILY,
						dailyService.getDaily(DateUtil.getDateFormated(new Date()), place, ipAddress));
				model.addObject(ConstantsJsp.DATEDAILY, DateUtil.getStringDateddMMyyyy(new Date()));
			}
		}
		return model;
	}
}
