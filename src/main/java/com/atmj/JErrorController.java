package com.atmj;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class JErrorController implements ErrorController {

	@RequestMapping("/error")
	@ResponseBody
	String error(HttpServletRequest request) {
		return "/error";
	}
}