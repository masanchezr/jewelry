package com.je.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.je.utils.constants.ConstantsJsp;
import com.je.web.forms.SearchJewelForm;

/**
 * The Class ExceptionHandlingController.
 */
@ControllerAdvice
public class ExceptionHandlingController {

	/**
	 * Handle error.
	 *
	 * @param req
	 *            the req
	 * @param exception
	 *            the exception
	 * @return the model and view
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest req, Exception exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject(ConstantsJsp.FORMSEARCH, new SearchJewelForm());
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("generic_error");
		return mav;
	}
}
