package com.je.jsboot.workshop.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.jsboot.services.metal.MetalService;
import com.je.jsboot.services.workshop.Workshop;
import com.je.jsboot.services.workshop.WorkshopService;
import com.je.jsboot.utils.constants.ConstantsViews;

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

	/**
	 * Login.
	 *
	 * @return the string
	 */
	@GetMapping("/workshop/login")
	public String login() {
		return "workshop/login";
	}

	/**
	 * Admin.
	 *
	 * @return the string
	 */
	@GetMapping("/workshop/admin")
	public String admin() {
		return "workshop/admin";
	}

	/**
	 * New workshop.
	 *
	 * @return the model and view
	 */
	@GetMapping("/workshop/newWorkshop")
	public ModelAndView newWorkshop() {
		ModelAndView model = new ModelAndView("workshop/newworkshop");
		model.addObject("workshop", new Workshop());
		model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
		return model;
	}

	/**
	 * Access denied page.
	 *
	 * @return the string
	 */
	@RequestMapping("/403wks")
	public String accessDeniedPage() {
		return "workshop/403";
	}

	/**
	 * Saveworkshop.
	 *
	 * @param workshop the workshop
	 * @param result   the result
	 * @return the model and view
	 */
	@PostMapping("/workshop/saveworkshop")
	public ModelAndView saveworkshop(@Valid Workshop workshop, BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.setViewName("workshop/newworkshop");
			model.addObject("worshop", workshop);
		} else {
			workshopService.save(workshop);
			model.setViewName("workshop/success");
		}
		return model;
	}
}
