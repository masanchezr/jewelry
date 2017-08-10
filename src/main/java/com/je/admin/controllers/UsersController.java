package com.je.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.admin.validators.UserValidator;
import com.je.services.places.PlaceService;
import com.je.services.users.User;
import com.je.services.users.UserService;

@Controller
public class UsersController {

	@Autowired
	private UserService userService;

	/** The place service. */
	@Autowired
	private PlaceService placeService;

	@Autowired
	private UserValidator userValidator;

	@RequestMapping(value = "/enabledisableuser")
	public ModelAndView enabledisableuser() {
		ModelAndView model = new ModelAndView("enabledisableuser");
		model.addObject("adminForm", new AdminForm());
		model.addObject("user", new User());
		return model;
	}

	@RequestMapping(value = "/resultenabledisableuser")
	public ModelAndView resultenabledisableuser(@ModelAttribute("user") User user, BindingResult result) {
		ModelAndView model = new ModelAndView("resultenabledisableuser");
		model.addObject("adminForm", new AdminForm());
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			model.setViewName("enabledisableuser");
			model.addObject("user", new User());
		} else {
			user = userService.disableEnableUser(user.getUsername());
			if (user == null) {
				model.setViewName("enabledisableuser");
				model.addObject("user", new User());
				result.rejectValue("username", "usernoexist");
			} else {
				model.addObject("user", user);
				model.setViewName("resultenabledisableuser");
			}
		}
		return model;
	}

	@RequestMapping(value = "/newuser")
	public ModelAndView newUser() {
		ModelAndView model = new ModelAndView("newuser");
		model.addObject("adminForm", new AdminForm());
		model.addObject("user", new User());
		model.addObject("places", placeService.getAllPlaces());
		return model;
	}

	@RequestMapping(value = "/saveuser")
	public ModelAndView saveUser(@ModelAttribute("user") User user, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject("adminForm", new AdminForm());
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			model.setViewName("newuser");
			model.addObject("user", new User());
			model.addObject("places", placeService.getAllPlaces());
		} else {
			model.setViewName("resultuser");
			model.addObject("user", user);
			userService.newUser(user);
		}
		return model;
	}
}
