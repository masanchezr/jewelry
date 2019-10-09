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
import com.je.dbaccess.entities.RecordingEntity;
import com.je.employee.validators.RecordingValidator;
import com.je.services.dailies.DailyService;
import com.je.services.payment.PaymentService;
import com.je.services.places.PlaceService;
import com.je.services.recordings.RecordingService;
import com.je.services.salesrepeated.SearchSaleRepeatedService;
import com.je.utils.constants.ConstantsJsp;
import com.je.utils.date.DateUtil;

@Controller
public class RecordingController {

	@Autowired
	private DailyService dailyService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private PlaceService placeService;

	@Autowired
	private RecordingService recordingService;

	@Autowired
	private SearchSaleRepeatedService searchSaleRepeatedService;

	@Autowired
	private RecordingValidator recordingValidator;

	private static final String FORMRECORDING = "recording";
	private static final String VIEWNEWRECORDING = "employee/sales/newrecording";

	@RequestMapping("/employee/newrecording")
	public ModelAndView newrecording() {
		ModelAndView model = new ModelAndView(VIEWNEWRECORDING);
		model.addObject(FORMRECORDING, new RecordingEntity());
		model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
		return model;
	}

	@RequestMapping(value = "/employee/saverecording")
	public ModelAndView saveRecording(@ModelAttribute(FORMRECORDING) RecordingEntity recording,
			HttpServletRequest request, BindingResult errors) {
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		ModelAndView model = new ModelAndView();
		recordingValidator.validate(recording, errors);
		if (errors.hasErrors()) {
			model.setViewName(VIEWNEWRECORDING);
			model.addObject(FORMRECORDING, recording);
			model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
		} else if (searchSaleRepeatedService.isSaleRepeated(recording.getNumsale())) {
			model.setViewName(VIEWNEWRECORDING);
			model.addObject(FORMRECORDING, recording);
			model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
			errors.rejectValue("numsale", "numrepeated");
		} else {
			String ipAddress = request.getHeader(ConstantsJsp.XFORWARDEDFOR);
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
			Date today = DateUtil.getDateFormated(new Date());
			PlaceEntity place = placeService.getPlaceUser(user);
			recording.setPlace(place);
			recordingService.save(recording);
			model.setViewName(ConstantsJsp.VIEWDAILYARROW);
			model.addObject(ConstantsJsp.DAILY, dailyService.getDaily(today, place, ipAddress));
			model.addObject(ConstantsJsp.DATEDAILY, today);
		}
		return model;
	}
}
