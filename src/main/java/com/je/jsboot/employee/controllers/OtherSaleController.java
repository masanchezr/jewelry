package com.je.jsboot.employee.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.jsboot.dbaccess.entities.OtherSaleEntity;
import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.employee.forms.OtherSale;
import com.je.jsboot.employee.validators.OtherSaleValidator;
import com.je.jsboot.services.dailies.DailyService;
import com.je.jsboot.services.othersale.OtherSaleService;
import com.je.jsboot.services.payment.PaymentService;
import com.je.jsboot.services.places.PlaceService;
import com.je.jsboot.services.salesrepeated.SearchSaleRepeatedService;
import com.je.jsboot.utils.constants.ConstantsViews;
import com.je.jsboot.utils.date.DateUtil;

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
	private Mapper mapper;

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
	public ModelAndView saveRecording(
			@Validated(OtherSaleValidator.class) @ModelAttribute(FORMRECORDING) OtherSale recording,
			HttpServletRequest request, BindingResult errors) {
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
			model.addObject(ConstantsViews.DATEDAILY, DateUtil.getStringDateddMMyyyy(today));
		}
		return model;
	}
}
