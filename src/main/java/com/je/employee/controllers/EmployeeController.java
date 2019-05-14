package com.je.employee.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.services.messages.MessageService;
import com.je.services.registers.RegisterService;
import com.je.utils.constants.ConstantsJsp;

/**
 * The Class EmployeeController.
 */
@Controller
public class EmployeeController {

	@Autowired
	private RegisterService registerService;

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
	public ModelAndView admin(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("adminemployee");
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		String ipAddress = request.getHeader(ConstantsJsp.XFORWARDEDFOR);
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		registerService.register(user, ipAddress);
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
