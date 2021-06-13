package com.je.jsboot.employee.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.jsboot.employee.validators.RentalValidator;
import com.je.jsboot.services.rentals.Rental;
import com.je.jsboot.services.rentals.RentalService;
import com.je.jsboot.utils.constants.Constants;
import com.je.jsboot.utils.constants.ConstantsViews;
import com.je.jsboot.utils.date.DateUtil;

@Controller
public class RentalController {

	@Autowired
	private RentalService rentalService;

	private static final String VIEWLOCALRENTAL = "employee/otherconcepts/localrental";
	private static final String FORMRENTAL = "rentalForm";

	@GetMapping("/employee/localrental")
	public ModelAndView localrental() {
		ModelAndView model = new ModelAndView(VIEWLOCALRENTAL);
		model.addObject(FORMRENTAL, new Rental());
		return model;
	}

	@PostMapping("/employee/savelocalrental")
	public ModelAndView saveLocalRental(@Validated(RentalValidator.class) @ModelAttribute(FORMRENTAL) Rental rental,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.setViewName(VIEWLOCALRENTAL);
			model.addObject(FORMRENTAL, rental);
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			// compruebo si ya existe
			rental.setUser(user);
			if (!rentalService.existsLocalRental(rental)) {
				model.addObject(ConstantsViews.DAILY, rentalService.saveRental(rental));
				model.setViewName(ConstantsViews.VIEWDAILYARROW);
				model.addObject(ConstantsViews.DATEDAILY, DateUtil.getStringDateddMMyyyy(new Date()));
			} else {
				model.setViewName(VIEWLOCALRENTAL);
				model.addObject(FORMRENTAL, rental);
				result.rejectValue(Constants.AMOUNT, "localrentalexists");
			}
		}
		return model;
	}
}