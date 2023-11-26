package com.atmj.jsboot.admin.controllers;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.admin.forms.AdminForm;
import com.atmj.jsboot.dbaccess.entities.CategoryEntity;
import com.atmj.jsboot.dbaccess.entities.JewelEntity;
import com.atmj.jsboot.dbaccess.entities.PaymentEntity;
import com.atmj.jsboot.services.connections.ConnectionService;
import com.atmj.jsboot.services.search.SearchService;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.web.forms.SearchJewelForm;

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
	@GetMapping("/login")
	public String login() {
		log.warn("probando login");
		return "admin/login";
	}

	/**
	 * Admin.
	 *
	 * @return the model and view
	 */
	@RequestMapping("/admin")
	public ModelAndView admin(HttpServletRequest request) {
		String ipAddress = request.getHeader(ConstantsViews.XFORWARDEDFOR);
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		connectionService.saveConnection(ipAddress);
		log.warn("probando admin");
		return new ModelAndView("admin/admin", ConstantsViews.ADMINFORM, new AdminForm());
	}

	/**
	 * New category.
	 *
	 * @return the model and view
	 */
	@RequestMapping("/newCategory")
	public ModelAndView newCategory() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/newCategory");
		model.addObject(ConstantsViews.CATEGORY, new CategoryEntity());
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * New payment.
	 *
	 * @return the model and view
	 */
	@RequestMapping("/newPayment")
	public ModelAndView newPayment() {
		ModelAndView model = new ModelAndView("admin/payments/newPayment");
		model.addObject(ConstantsViews.FORMPAYMENT, new PaymentEntity());
		model.addObject(ConstantsViews.FORMSEARCH, new SearchJewelForm());
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Search jewels.
	 *
	 * @param search the search
	 * @return the model and view
	 */
	@RequestMapping("/searchJewels")
	public ModelAndView searchJewels(@RequestParam String search) {
		ModelAndView model = new ModelAndView("admin/jewels/searchjewels/resultsearchbyreference");
		Page<JewelEntity> jewels = searchService.searchActives(search, 1);
		model.addObject(ConstantsViews.JEWELS, jewels.getContent());
		model.addObject("toUpdate", new JewelEntity());
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Access denied page.
	 *
	 * @return the string
	 */
	@RequestMapping("/403admin")
	public ModelAndView accessDeniedPage() {
		ModelAndView model = new ModelAndView("admin/403");
		log.warn("probando 403");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}
}