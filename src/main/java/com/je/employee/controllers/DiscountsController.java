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
import com.je.employee.validators.DiscountsValidator;
import com.je.services.dailies.DailyService;
import com.je.services.discounts.Discount;
import com.je.services.discounts.DiscountService;
import com.je.services.places.PlaceService;

@Controller
public class DiscountsController {

	@Autowired
	private DiscountService discountService;

	@Autowired
	private PlaceService placeService;

	@Autowired
	private DailyService dailyService;

	@Autowired
	private DiscountsValidator discountsValidator;

	@RequestMapping("/employee/newdiscount")
	public ModelAndView newDiscount() {
		ModelAndView model = new ModelAndView("newdiscount");
		model.addObject("discountForm", new Discount());
		return model;
	}

	@RequestMapping("/employee/savediscount")
	public ModelAndView savediscount(@ModelAttribute("discountForm") Discount discount, HttpServletRequest request,
			BindingResult errors) {
		ModelAndView model = new ModelAndView();
		discountsValidator.validate(discount, errors);
		if (errors.hasErrors()) {
			model.setViewName("newdiscount");
			model.addObject("discountForm", discount);
		} else {
			Date today = new Date();
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			PlaceEntity place = placeService.getPlaceUser(user);
			String ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
			discount.setPlace(place);
			discountService.save(discount);
			model.addObject("daily", dailyService.getDaily(today, place, ipAddress));
			model.addObject("datedaily", today);
			model.setViewName("dailyarrow");
		}
		return model;
	}
}
