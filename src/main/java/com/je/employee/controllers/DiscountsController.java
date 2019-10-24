package com.je.employee.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.je.dbaccess.entities.PlaceEntity;
import com.je.employee.validators.DiscountsValidator;
import com.je.services.dailies.DailyService;
import com.je.services.discounts.Discount;
import com.je.services.discounts.DiscountService;
import com.je.services.places.PlaceService;
import com.je.services.salesrepeated.SearchSaleRepeatedService;
import com.je.utils.constants.ConstantsViews;
import com.je.utils.date.DateUtil;

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

	@Autowired
	private DiscountsValidator discountsValidator;

	public static final String FORMDISCOUNT = "discountForm";
	public static final String VIEWNEWDISCOUNT = "employee/sales/newdiscount";

	@GetMapping("/employee/newdiscount")
	public ModelAndView newDiscount() {
		ModelAndView model = new ModelAndView(VIEWNEWDISCOUNT);
		model.addObject(FORMDISCOUNT, new Discount());
		return model;
	}

	@GetMapping("/employee/savediscount")
	public ModelAndView savediscount(@ModelAttribute("discountForm") Discount discount, HttpServletRequest request,
			BindingResult errors) {
		ModelAndView model = new ModelAndView();
		discountsValidator.validate(discount, errors);
		if (errors.hasErrors()) {
			model.setViewName(VIEWNEWDISCOUNT);
			model.addObject(FORMDISCOUNT, discount);
		} else if (searchSaleRepeatedService.isSaleRepeated(discount.getIddiscount())) {
			model.setViewName(VIEWNEWDISCOUNT);
			model.addObject(FORMDISCOUNT, discount);
			errors.rejectValue("iddiscount", "numrepeated");
		} else {
			Date today = new Date();
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
			model.addObject(ConstantsViews.DATEDAILY, DateUtil.getStringDateddMMyyyy(today));
			model.setViewName(ConstantsViews.VIEWDAILYARROW);
		}
		return model;
	}
}
