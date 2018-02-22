package com.je.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.services.messages.MessageService;

/**
 * The Class EmployeeController.
 */
@Controller
public class EmployeeController {

	@Autowired
	private MessageService messageservice;

	/**
	 * Login.
	 *
	 * @return the string
	 */
	@RequestMapping("/employee/login")
	public String login() {
		return "loginemployee";
	}

	/**
	 * Admin.
	 *
	 * @return the string
	 */
	@RequestMapping("/employee/admin")
	public ModelAndView admin() {
		ModelAndView model = new ModelAndView("adminemployee");
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addObject("messages", messageservice.getMessagesActiveNow(user));
		return model;
	}

	/**
	 * Access denied page.
	 *
	 * @return the string
	 */
	@RequestMapping("/403")
	public String accessDeniedPage() {
		return "403";
	}
}
