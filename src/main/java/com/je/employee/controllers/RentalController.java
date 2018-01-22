package com.je.employee.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.employee.validators.RentalValidator;
import com.je.services.rentals.Rental;
import com.je.services.rentals.RentalService;

@Controller
public class RentalController {

	@Autowired
	private RentalService rentalService;

	@Autowired
	private RentalValidator rentalValidator;

	@RequestMapping(value = "/employee/localrental")
	public ModelAndView localrental() {
		ModelAndView model = new ModelAndView("localrental");
		model.addObject("rentalForm", new Rental());
		return model;
	}

	@RequestMapping(value = "/employee/savelocalrental")
	public ModelAndView saveLocalRental(@ModelAttribute("rentalForm") Rental rental, BindingResult result) {
		ModelAndView model = new ModelAndView();
		rentalValidator.validate(rental, result);
		if (result.hasErrors()) {
			model.setViewName("localrental");
			model.addObject("rentalForm", rental);
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			// compruebo si ya existe
			rental.setUser(user);
			if (!rentalService.existsLocalRental(rental)) {
				model.addObject("daily", rentalService.saveRental(rental));
				model.setViewName("dailyarrow");
				model.addObject("datedaily", new Date());
			} else {
				model.setViewName("localrental");
				model.addObject("rentalForm", rental);
				result.rejectValue("amount", "localrentalexists");
			}
		}
		return model;
	}
}