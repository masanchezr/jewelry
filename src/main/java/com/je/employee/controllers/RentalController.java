package com.je.employee.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.employee.validators.RentalValidator;
import com.je.services.rentals.Rental;
import com.je.services.rentals.RentalService;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;
import com.je.utils.date.DateUtil;

@Controller
public class RentalController {

	@Autowired
	private RentalService rentalService;

	@Autowired
	private RentalValidator rentalValidator;

	private static final String VIEWLOCALRENTAL = "employee/otherconcepts/localrental";
	private static final String FORMRENTAL = "rentalForm";

	@GetMapping(value = "/employee/localrental")
	public ModelAndView localrental() {
		ModelAndView model = new ModelAndView(VIEWLOCALRENTAL);
		model.addObject(FORMRENTAL, new Rental());
		return model;
	}

	@GetMapping(value = "/employee/savelocalrental")
	public ModelAndView saveLocalRental(@ModelAttribute(FORMRENTAL) Rental rental, BindingResult result) {
		ModelAndView model = new ModelAndView();
		rentalValidator.validate(rental, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWLOCALRENTAL);
			model.addObject(FORMRENTAL, rental);
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			// compruebo si ya existe
			rental.setUser(user);
			if (!rentalService.existsLocalRental(rental)) {
				model.addObject(ConstantsJsp.DAILY, rentalService.saveRental(rental));
				model.setViewName(ConstantsJsp.VIEWDAILYARROW);
				model.addObject(ConstantsJsp.DATEDAILY, DateUtil.getStringDateddMMyyyy(new Date()));
			} else {
				model.setViewName(VIEWLOCALRENTAL);
				model.addObject(FORMRENTAL, rental);
				result.rejectValue(Constants.AMOUNT, "localrentalexists");
			}
		}
		return model;
	}
}