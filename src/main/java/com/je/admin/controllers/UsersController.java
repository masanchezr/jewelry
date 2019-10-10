package com.je.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.admin.validators.UserValidator;
import com.je.services.places.PlaceService;
import com.je.services.users.User;
import com.je.services.users.UserService;
import com.je.utils.constants.ConstantsJsp;

@Controller
public class UsersController {

	/** The place service. */
	@Autowired
	private PlaceService placeService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@GetMapping("/newuser")
	public ModelAndView newUser() {
		ModelAndView model = new ModelAndView("admin/users/saveuser/newuser");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.USER, new User());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		return model;
	}

	@PostMapping("/saveuser")
	public ModelAndView saveUser(@ModelAttribute(ConstantsJsp.USER) User user, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			model.setViewName("admin/users/saveuser/newuser");
			model.addObject(ConstantsJsp.USER, new User());
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		} else {
			model.setViewName("admin/users/saveuser/resultuser");
			model.addObject(ConstantsJsp.USER, user);
			userService.newUser(user);
		}
		return model;
	}
}
