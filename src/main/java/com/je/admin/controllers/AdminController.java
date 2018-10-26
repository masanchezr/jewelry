package com.je.admin.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.dbaccess.entities.CategoryEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.services.connections.ConnectionService;
import com.je.services.search.SearchService;
import com.je.utils.constants.ConstantsJsp;
import com.je.web.forms.SearchJewelForm;

/**
 * The Class AdminController.
 */
@Controller
public class AdminController {

	/** The search service. */
	@Autowired
	private SearchService searchService;

	@Autowired
	private ConnectionService connectionService;

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(AdminController.class);

	/**
	 * Login.
	 * 
	 * @return the string
	 */
	@RequestMapping(value = "/login")
	public String login() {
		log.warn("probando login");
		return "login";
	}

	/**
	 * Admin.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/admin")
	public ModelAndView admin(HttpServletRequest request) {
		String ipAddress = request.getHeader(ConstantsJsp.XFORWARDEDFOR);
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		connectionService.saveConnection(ipAddress);
		return new ModelAndView("admin", ConstantsJsp.ADMINFORM, new AdminForm());
	}

	/**
	 * New category.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/newCategory")
	public ModelAndView newCategory() {
		ModelAndView model = new ModelAndView();
		model.setViewName("newCategory");
		model.addObject(ConstantsJsp.CATEGORY, new CategoryEntity());
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * New payment.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/newPayment")
	public ModelAndView newPayment() {
		ModelAndView model = new ModelAndView("newPayment");
		model.addObject(ConstantsJsp.FORMPAYMENT, new PaymentEntity());
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchJewelForm());
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Search jewels.
	 *
	 * @param search the search
	 * @return the model and view
	 */
	@RequestMapping(value = "/searchJewels")
	public ModelAndView searchJewels(@RequestParam String search) {
		ModelAndView model = new ModelAndView("resultsearchbyreference");
		List<JewelEntity> jewels = searchService.search(search);
		model.addObject(ConstantsJsp.JEWELS, jewels);
		model.addObject("toUpdate", new JewelEntity());
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Error.
	 * 
	 * @return the string
	 */
	@RequestMapping(value = "/error")
	public String error() {
		return "error";
	}

	/**
	 * Access denied page.
	 *
	 * @return the string
	 */
	@RequestMapping("/403admin")
	public ModelAndView accessDeniedPage() {
		ModelAndView model = new ModelAndView("403admin");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}
}