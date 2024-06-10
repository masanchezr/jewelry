package com.atmj.jsboot.employee.controllers;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.dbaccess.entities.OtherSaleEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.employee.forms.OtherSale;
import com.atmj.jsboot.services.dailies.DailyService;
import com.atmj.jsboot.services.othersale.OtherSaleService;
import com.atmj.jsboot.services.payment.PaymentService;
import com.atmj.jsboot.services.places.PlaceService;
import com.atmj.jsboot.services.salesrepeated.SearchSaleRepeatedService;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.date.DateUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class OtherSaleController {

	@Autowired
	private DailyService dailyService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private PlaceService placeService;

	@Autowired
	private OtherSaleService otherSaleService;

	@Autowired
	private SearchSaleRepeatedService searchSaleRepeatedService;

	@Autowired
	private ModelMapper mapper;

	private static final String FORMRECORDING = "recording";
	private static final String TYPES = "types";
	private static final String VIEWNEWRECORDING = "employee/sales/newothersale";

	@GetMapping("/employee/othersales")
	public ModelAndView newrecording() {
		ModelAndView model = new ModelAndView(VIEWNEWRECORDING);
		model.addObject(FORMRECORDING, new OtherSaleEntity());
		model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
		model.addObject(TYPES, otherSaleService.findAllTypes());
		return model;
	}

	@PostMapping("/employee/saverecording")
	public ModelAndView saveRecording(@Valid OtherSale recording, BindingResult errors, HttpServletRequest request) {
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		ModelAndView model = new ModelAndView();
		Long numsale = recording.getNumsale();
		Date today = new Date();
		if (errors.hasErrors()) {
			model.setViewName(VIEWNEWRECORDING);
			model.addObject(FORMRECORDING, recording);
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
			model.addObject(TYPES, otherSaleService.findAllTypes());
		} else if (numsale != null && !searchSaleRepeatedService.isNotRepeatSale(numsale, DateUtil.getYear(today))) {
			model.setViewName(VIEWNEWRECORDING);
			model.addObject(FORMRECORDING, recording);
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
			model.addObject(TYPES, otherSaleService.findAllTypes());
			errors.rejectValue("numsale", "numrepeated");
		} else {
			String ipAddress = request.getHeader(ConstantsViews.XFORWARDEDFOR);
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
			today = DateUtil.getDateFormated(today);
			PlaceEntity place = placeService.getPlaceUser(user);
			recording.setPlace(place);
			otherSaleService.save(mapper.map(recording, OtherSaleEntity.class));
			model.setViewName(ConstantsViews.VIEWDAILYARROW);
			model.addObject(ConstantsViews.DAILY, dailyService.getDaily(today, place, ipAddress));
		}
		return model;
	}
}
