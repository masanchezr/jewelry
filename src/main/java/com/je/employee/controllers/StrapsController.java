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

import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.StrapEntity;
import com.je.employee.validators.StrapFormValidator;
import com.je.forms.Strap;
import com.je.services.dailies.DailyService;
import com.je.services.payment.PaymentService;
import com.je.services.places.PlaceService;
import com.je.services.salesrepeated.SearchSaleRepeatedService;
import com.je.services.straps.StrapsService;
import com.je.utils.constants.ConstantsJsp;
import com.je.utils.date.DateUtil;

@Controller
public class StrapsController {

	@Autowired
	private DailyService dailyService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private PlaceService placeService;

	@Autowired
	private SearchSaleRepeatedService searchSaleRepeatedService;

	@Autowired
	private StrapsService strapsService;

	@Autowired
	private StrapFormValidator strapFormValidator;

	@Autowired
	private Mapper mapper;

	private static final String FORMSTRAP = "strapForm";
	private static final String VIEWSALESTRAP = "employee/sales/newsalestrap";

	@GetMapping("/employee/newsalestrap")
	public ModelAndView newsalestrap() {
		ModelAndView model = new ModelAndView(VIEWSALESTRAP);
		model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
		model.addObject(FORMSTRAP, new StrapEntity());
		return model;
	}

	@PostMapping("/employee/savesalestrap")
	public ModelAndView savesalestrap(@ModelAttribute("strapForm") Strap strap, HttpServletRequest request,
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
			Long numsale = strap.getNumsale();
			if (numsale != null && searchSaleRepeatedService.isSaleRepeated(numsale)) {
				model.setViewName(VIEWSALESTRAP);
				model.addObject(FORMSTRAP, strap);
				arg1.rejectValue(ConstantsJsp.NUMSALE, "numrepeated");
			} else {
				String ipAddress = request.getHeader(ConstantsJsp.XFORWARDEDFOR);
				if (ipAddress == null) {
					ipAddress = request.getRemoteAddr();
				}
				Date today = DateUtil.getDateFormated(new Date());
				model.setViewName(ConstantsJsp.VIEWDAILYARROW);
				strap.setPlace(place);
				strapsService.saveSaleStrap(mapper.map(strap, StrapEntity.class));
				model.addObject(ConstantsJsp.DAILY, dailyService.getDaily(today, place, ipAddress));
				model.addObject(ConstantsJsp.DATEDAILY, DateUtil.getStringDateddMMyyyy(new Date()));
			}
		}
		return model;
	}
}
