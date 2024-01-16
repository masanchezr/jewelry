package com.atmj;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class JErrorController implements ErrorController {

	@RequestMapping("/error")
	@ResponseBody
	ModelAndView error(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName("error");
		return model;
	}
}