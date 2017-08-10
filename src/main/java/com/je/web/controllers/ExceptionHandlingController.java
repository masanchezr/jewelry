package com.je.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Class ExceptionHandlingController.
 */
public class ExceptionHandlingController {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);

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
		logger.error("Request: " + req.getRequestURL() + " raised " + exception);

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("generic_error");
		return mav;
	}
}
