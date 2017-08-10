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

@Controller
public class RecordingController {

	@Autowired
	private RecordingService recordingService;

	@Autowired
	private PlaceService placeService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private DailyService dailyService;

	@Autowired
	private RecordingValidator recordingValidator;

	@RequestMapping("/employee/newrecording")
	public ModelAndView newrecording() {
		ModelAndView model = new ModelAndView("newrecording");
		model.addObject("recording", new RecordingEntity());
		model.addObject("payments", paymentService.findAllActive());
		return model;
	}

	@RequestMapping(value = "/employee/saverecording")
	public ModelAndView saveRecording(@ModelAttribute("recording") RecordingEntity recording,
			HttpServletRequest request, BindingResult errors) {
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		ModelAndView model = new ModelAndView();
		recordingValidator.validate(recording, errors);
		if (errors.hasErrors()) {
			model.setViewName("newrecording");
			model.addObject("recording", recording);
			model.addObject("payments", paymentService.findAllActive());
		} else {
			String ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
			Date today = new Date();
			PlaceEntity place = placeService.getPlaceUser(user);
			recording.setPlace(place);
			recordingService.save(recording);
			model.setViewName("daily");
			model.addObject("daily", dailyService.getDaily(today, place, ipAddress));
			model.addObject("datedaily", today);
		}
		return model;
	}
}
