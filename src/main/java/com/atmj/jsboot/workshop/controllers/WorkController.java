package com.atmj.jsboot.workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.services.messages.MessageService;
import com.atmj.jsboot.services.registers.RegisterService;
import com.atmj.jsboot.utils.constants.ConstantsViews;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class WorkController {

	@Autowired
	private RegisterService registerService;

	@Autowired
	private MessageService messageservice;

	/**
	 * Login.
	 *
	 * @return the string
	 */
	@GetMapping("/work/login")
	public String login() {
		return "workshop/login";
	}

	/**
	 * Admin.
	 *
	 * @return the string
	 */
	@GetMapping("/work/admin")
	public ModelAndView admin(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("workshop/admin");
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		String ipAddress = request.getHeader(ConstantsViews.XFORWARDEDFOR);
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
	@RequestMapping("/work/403")
	public String accessDeniedPage() {
		return "workshop/403";
	}
}
