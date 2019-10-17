package com.je.employee.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.dbaccess.entities.BatteryEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.employee.validators.BatteryFormValidator;
import com.je.forms.Battery;
import com.je.services.batteries.BatteriesService;
import com.je.services.dailies.DailyService;
import com.je.services.payment.PaymentService;
import com.je.services.places.PlaceService;
import com.je.services.salesrepeated.SearchSaleRepeatedService;
import com.je.utils.constants.ConstantsJsp;
import com.je.utils.date.DateUtil;

@Controller
public class BatteriesController {
	@Autowired
	private BatteriesService batteriesService;

	@Autowired
	private DailyService dailyService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private PlaceService placeService;

	@Autowired
	private SearchSaleRepeatedService searchSaleRepeatedService;

	@Autowired
	private BatteryFormValidator batteryFormValidator;

	@Autowired
	private Mapper mapper;

	private static final String BATTERYFORM = "batteryForm";
	private static final String VIEWNEWSALEBATTERY = "employee/sales/newsalebattery";

	@GetMapping("/employee/newsalebattery")
	public ModelAndView newsalebattery() {
		ModelAndView model = new ModelAndView(VIEWNEWSALEBATTERY);
		model.addObject(BATTERYFORM, new BatteryEntity());
		model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
		return model;
	}

	@PostMapping("/employee/savesalebattery")
	public ModelAndView savesalebattery(@ModelAttribute("batteryForm") Battery battery, HttpServletRequest request,
			BindingResult arg1) {
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
			// comprobamos primero que no exista este número de venta en otra venta
			Long numsale = battery.getNumsale();
			if (numsale != null && searchSaleRepeatedService.isSaleRepeated(numsale)) {
				arg1.rejectValue(ConstantsJsp.NUMSALE, "numrepeated");
				model.setViewName(VIEWNEWSALEBATTERY);
				model.addObject(BATTERYFORM, battery);
			} else {
				model.setViewName(ConstantsJsp.VIEWDAILYARROW);
				battery.setPlace(place);
				batteriesService.saveSaleBattery(mapper.map(battery, BatteryEntity.class));
				model.addObject(ConstantsJsp.DAILY,
						dailyService.getDaily(DateUtil.getDateFormated(new Date()), place, ipAddress));
				model.addObject(ConstantsJsp.DATEDAILY, DateUtil.getStringDateddMMyyyy(new Date()));
			}
		}
		return model;
	}
}
