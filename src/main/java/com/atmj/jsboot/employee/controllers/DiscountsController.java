package com.atmj.jsboot.employee.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.services.dailies.DailyService;
import com.atmj.jsboot.services.discounts.Discount;
import com.atmj.jsboot.services.discounts.DiscountService;
import com.atmj.jsboot.services.places.PlaceService;
import com.atmj.jsboot.services.salesrepeated.SearchSaleRepeatedService;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.date.DateUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class DiscountsController {

	@Autowired
	private DailyService dailyService;

	@Autowired
	private DiscountService discountService;

	@Autowired
	private PlaceService placeService;

	@Autowired
	private SearchSaleRepeatedService searchSaleRepeatedService;

	public static final String FORMDISCOUNT = "discountForm";
	public static final String VIEWNEWDISCOUNT = "employee/sales/newdiscount";

	@GetMapping("/employee/newdiscount")
	public ModelAndView newDiscount() {
		ModelAndView model = new ModelAndView(VIEWNEWDISCOUNT);
		model.addObject(FORMDISCOUNT, new Discount());
		return model;
	}

	@PostMapping("/employee/savediscount")
	public ModelAndView savediscount(@Valid Discount discount, BindingResult errors, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		Date today = new Date();
		if (errors.hasErrors()) {
			model.setViewName(VIEWNEWDISCOUNT);
			model.addObject(FORMDISCOUNT, discount);
		} else if (!searchSaleRepeatedService.isNotRepeatSale(discount.getNumsale(), DateUtil.getYear(today))) {
			model.setViewName(VIEWNEWDISCOUNT);
			model.addObject(FORMDISCOUNT, discount);
			errors.rejectValue("iddiscount", "numrepeated");
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			PlaceEntity place = placeService.getPlaceUser(user);
			String ipAddress = request.getHeader(ConstantsViews.XFORWARDEDFOR);
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
			discount.setPlace(place);
			discountService.save(discount);
			model.addObject(ConstantsViews.DAILY,
					dailyService.getDaily(DateUtil.getDateFormated(today), place, ipAddress));
			model.setViewName(ConstantsViews.VIEWDAILYARROW);
		}
		return model;
	}
}
