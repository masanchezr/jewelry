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
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;

@Controller
public class UsersController {

	@Autowired
	private UserService userService;

	/** The place service. */
	@Autowired
	private PlaceService placeService;

	@Autowired
	private UserValidator userValidator;

	private static final String VIEWENABLEDISABLEUSER = "enabledisableuser";

	@RequestMapping(value = "/enabledisableuser")
	public ModelAndView enabledisableuser() {
		ModelAndView model = new ModelAndView(VIEWENABLEDISABLEUSER);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.USER, new User());
		return model;
	}

	@RequestMapping(value = "/resultenabledisableuser")
	public ModelAndView resultenabledisableuser(@ModelAttribute(ConstantsJsp.USER) User user, BindingResult result) {
		ModelAndView model = new ModelAndView("resultenabledisableuser");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWENABLEDISABLEUSER);
			model.addObject(ConstantsJsp.USER, new User());
		} else {
			user = userService.disableEnableUser(user.getUsername());
			if (user == null) {
				model.setViewName(VIEWENABLEDISABLEUSER);
				model.addObject(ConstantsJsp.USER, new User());
				result.rejectValue(Constants.USERNAME, "usernoexist");
			} else {
				model.addObject(ConstantsJsp.USER, user);
				model.setViewName("resultenabledisableuser");
			}
		}
		return model;
	}

	@RequestMapping(value = "/newuser")
	public ModelAndView newUser() {
		ModelAndView model = new ModelAndView("newuser");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.USER, new User());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlaces());
		return model;
	}

	@RequestMapping(value = "/saveuser")
	public ModelAndView saveUser(@ModelAttribute(ConstantsJsp.USER) User user, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			model.setViewName("newuser");
			model.addObject(ConstantsJsp.USER, new User());
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlaces());
		} else {
			model.setViewName("resultuser");
			model.addObject(ConstantsJsp.USER, user);
			userService.newUser(user);
		}
		return model;
	}
}
