package com.je.jsboot.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.jsboot.admin.forms.AdminForm;
import com.je.jsboot.admin.validators.UserValidator;
import com.je.jsboot.services.places.PlaceService;
import com.je.jsboot.services.users.User;
import com.je.jsboot.services.users.UserService;
import com.je.jsboot.utils.constants.ConstantsViews;

@Controller
public class UsersController {

	/** The place service. */
	@Autowired
	private PlaceService placeService;

	@Autowired
	private UserService userService;

	@GetMapping("/newuser")
	public ModelAndView newUser() {
		ModelAndView model = new ModelAndView("admin/users/saveuser/newuser");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.USER, new User());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		return model;
	}

	@PostMapping("/saveuser")
	public ModelAndView saveUser(@Validated(UserValidator.class) @ModelAttribute(ConstantsViews.USER) User user,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		if (result.hasErrors()) {
			model.setViewName("admin/users/saveuser/newuser");
			model.addObject(ConstantsViews.USER, new User());
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		} else {
			model.setViewName("admin/users/saveuser/resultuser");
			model.addObject(ConstantsViews.USER, user);
			userService.newUser(user);
		}
		return model;
	}
}
