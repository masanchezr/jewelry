package com.je.workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.services.metal.MetalService;
import com.je.services.workshop.Workshop;
import com.je.services.workshop.WorkshopService;
import com.je.utils.constants.ConstantsJsp;
import com.je.workshop.validators.WorkshopValidator;

/**
 * The Class WorkshopController.
 */
@Controller
public class WorkshopController {

	/** The workshop service. */
	@Autowired
	private WorkshopService workshopService;

	@Autowired
	private MetalService materialService;

	/** The work shop validator. */
	@Autowired
	private WorkshopValidator workShopValidator;

	/**
	 * Login.
	 *
	 * @return the string
	 */
	@RequestMapping("/workshop/login")
	public String login() {
		return "loginworkshop";
	}

	/**
	 * Admin.
	 *
	 * @return the string
	 */
	@RequestMapping("/workshop/admin")
	public String admin() {
		return "adminworkshop";
	}

	/**
	 * New workshop.
	 *
	 * @return the model and view
	 */
	@RequestMapping("/workshop/newWorkshop")
	public ModelAndView newWorkshop() {
		ModelAndView model = new ModelAndView("newWorkshop");
		model.addObject("workshop", new Workshop());
		model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
		return model;
	}

	/**
	 * Access denied page.
	 *
	 * @return the string
	 */
	@RequestMapping("/403wks")
	public String accessDeniedPage() {
		return "403wks";
	}

	/**
	 * Saveworkshop.
	 *
	 * @param workshop
	 *            the workshop
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping("/workshop/saveworkshop")
	public ModelAndView saveworkshop(@ModelAttribute("workshop") Workshop workshop, BindingResult result) {
		ModelAndView model = new ModelAndView();
		workShopValidator.validate(workshop, result);
		if (result.hasErrors()) {
			model.setViewName("newWorkshop");
			model.addObject("worshop", workshop);
		} else {
			workshopService.save(workshop);
			model.setViewName("successworkshop");
		}
		return model;
	}
}
