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

import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.StrapEntity;
import com.je.employee.validators.StrapFormValidator;
import com.je.forms.Sale;
import com.je.services.dailies.DailyService;
import com.je.services.payment.PaymentService;
import com.je.services.places.PlaceService;
import com.je.services.sales.SaleService;
import com.je.services.straps.StrapsService;
import com.je.utils.constants.ConstantsJsp;

@Controller
public class StrapsController {

	@Autowired
	private StrapsService strapsService;

	@Autowired
	private SaleService saleService;

	@Autowired
	private PlaceService placeService;

	@Autowired
	private DailyService dailyService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private StrapFormValidator strapFormValidator;

	private static final String VIEWSALESTRAP = "employee/sales/newsalestrap";
	private static final String FORMSTRAP = "strapForm";

	@RequestMapping(value = "/employee/newsalestrap")
	public ModelAndView newsalestrap() {
		ModelAndView model = new ModelAndView(VIEWSALESTRAP);
		model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
		model.addObject(FORMSTRAP, new StrapEntity());
		return model;
	}

	@RequestMapping(value = "/employee/savesalestrap")
	public ModelAndView savesalestrap(@ModelAttribute("strapForm") StrapEntity strap, HttpServletRequest request,
			BindingResult arg1) {
		ModelAndView model = new ModelAndView();
		strapFormValidator.validate(strap, arg1);
		if (arg1.hasErrors()) {
			model.setViewName(VIEWSALESTRAP);
			model.addObject(FORMSTRAP, strap);
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			PlaceEntity place = placeService.getPlaceUser(user);
			// comprobamos primero que no exista este número de venta
			Sale sale = saleService.searchByNumsaleAndPlace(strap.getNumsale(), place.getIdplace());
			if (sale != null) {
				model.setViewName(VIEWSALESTRAP);
				model.addObject(FORMSTRAP, strap);
				arg1.rejectValue(ConstantsJsp.NUMSALE, "numrepited");
			} else {
				String ipAddress = request.getHeader(ConstantsJsp.XFORWARDEDFOR);
				if (ipAddress == null) {
					ipAddress = request.getRemoteAddr();
				}
				Date today = new Date();
				model.setViewName(ConstantsJsp.VIEWDAILYARROW);
				strap.setPlace(place);
				strapsService.saveSaleStrap(strap);
				model.addObject(ConstantsJsp.DAILY, dailyService.getDaily(today, place, ipAddress));
				model.addObject(ConstantsJsp.DATEDAILY, today);
			}
		}
		return model;
	}
}
