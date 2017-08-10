package com.je.employee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Class EmployeeController.
 */
@Controller
public class EmployeeController {

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
	public String admin() {
		return "adminemployee";
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
