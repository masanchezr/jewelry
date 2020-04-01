package com.je.web.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.je.dbaccess.entities.CategoryEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.services.categories.CategoriesService;
import com.je.services.jewels.JewelService;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsViews;
import com.je.web.forms.SearchJewelForm;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({ "cart" })
public class HomeController {

	/** The search categories service. */
	@Autowired
	private CategoriesService searchCategoriesService;

	/** The jewel service. */
	@Autowired
	private JewelService jewelService;

	/**
	 * Home.
	 * 
	 * @return the string
	 */

	@GetMapping({ "/index", "/" })
	public ModelAndView home() {
		// Recuperar toda la lista de categorias
		Iterable<CategoryEntity> categories = searchCategoriesService.getAllCategoriesOrderByName();
		Page<JewelEntity> page;
		ModelAndView model = new ModelAndView("web/index");
		page = jewelService.searchActive(1);
		model.addObject(ConstantsViews.CATEGORIES, categories);
		model.addObject(ConstantsViews.JEWELS, page.getContent());
		model.addObject(ConstantsViews.BREADCRUMBS, "Home");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchJewelForm());
		model.addObject("page", page);
		return model;
	}

	@GetMapping("/shop")
	public ModelAndView shop() {
		// Recuperar toda la lista de categorias
		Iterable<CategoryEntity> categories = searchCategoriesService.getAllCategoriesOrderByName();
		PlaceEntity place = new PlaceEntity();
		Page<JewelEntity> page;
		ModelAndView model = new ModelAndView("web/shop");
		place.setIdplace(Constants.WEB);
		page = jewelService.searchActive(1);
		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());
		model.addObject(ConstantsViews.CATEGORIES, categories);
		model.addObject(ConstantsViews.JEWELS, page.getContent());
		model.addObject(ConstantsViews.BREADCRUMBS, "Home");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchJewelForm());
		model.addObject("page", page);
		model.addObject("beginIndex", begin);
		model.addObject("endIndex", end);
		model.addObject("currentIndex", current);
		model.addObject("totalprice", 0);
		model.addObject(ConstantsViews.FORMSEARCH, new SearchJewelForm());
		return model;

	}

	@GetMapping("/page/{pageNumber}")
	public ModelAndView page(@PathVariable Integer pageNumber) {
		// Recuperar toda la lista de categorias
		Iterable<CategoryEntity> categories = searchCategoriesService.getAllCategoriesOrderByName();
		PlaceEntity place = new PlaceEntity();
		Page<JewelEntity> page;
		place.setIdplace(Constants.WEB);
		// Recuperamos productos activos
		page = jewelService.searchActive(pageNumber);
		if (page == null) {
			page = jewelService.searchActive(1);
		}
		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());
		ModelAndView model = new ModelAndView("web/index");
		model.addObject(ConstantsViews.CATEGORIES, categories);
		model.addObject(ConstantsViews.JEWELS, page.getContent());
		model.addObject(ConstantsViews.BREADCRUMBS, "Home");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchJewelForm());
		model.addObject("page", page);
		model.addObject("beginIndex", begin);
		model.addObject("endIndex", end);
		model.addObject("currentIndex", current);
		return model;
	}

	/**
	 * Contact.
	 * 
	 * @return the string
	 */
	@GetMapping("/contacto")
	public ModelAndView contact() {
		ModelAndView model = new ModelAndView("web/contact");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchJewelForm());
		model.addObject(ConstantsViews.CATEGORIES, searchCategoriesService.getAllCategoriesOrderByName());
		return model;
	}

	/**
	 * Faqs.
	 * 
	 * @return the string
	 */
	@GetMapping("/faqs")
	public ModelAndView faqs() {
		ModelAndView model = new ModelAndView("web/faqs");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchJewelForm());
		model.addObject(ConstantsViews.CATEGORIES, searchCategoriesService.getAllCategoriesOrderByName());
		return model;
	}

	/**
	 * Terms.
	 * 
	 * @return the string
	 */
	@GetMapping("/terms")
	public ModelAndView terms() {
		ModelAndView model = new ModelAndView("web/terms");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchJewelForm());
		model.addObject(ConstantsViews.CATEGORIES, searchCategoriesService.getAllCategoriesOrderByName());
		return model;
	}

	@GetMapping("/{keyword}")
	public ModelAndView searchByKeyWord(@PathVariable String keyword) {
		return searchByKeyword(keyword, 1);
	}

	@GetMapping("/{keyword}/{pageNumber}")
	public ModelAndView searchByKeyWord(@PathVariable String keyword, @PathVariable Integer pageNumber) {
		return searchByKeyword(keyword, pageNumber);
	}

	private ModelAndView searchByKeyword(String keyword, int pagenumber) {
		Iterable<CategoryEntity> categories = searchCategoriesService.getAllCategoriesOrderByName();
		CategoryEntity category = searchCategoriesService.getCategoryByKeyword(keyword);
		Page<JewelEntity> jewels = jewelService.searchJewelsByCategory(category, pagenumber);
		if (jewels == null || jewels.isEmpty()) {
			return home();
		} else {
			ModelAndView model = new ModelAndView();
			model.addObject(ConstantsViews.CATEGORIES, categories);
			model.addObject(ConstantsViews.JEWELS, jewels.getContent());
			model.addObject(ConstantsViews.FORMSEARCH, new SearchJewelForm());
			model.addObject("page", jewels);
			model.addObject("keyword", keyword);
			model.setViewName("web/shop");
			return model;
		}
	}

	/**
	 * Selected jewel.
	 * 
	 * @param id the id
	 * @return the model and view
	 */
	@GetMapping("/productoSeleccionado{id}")
	public ModelAndView selectedJewelEntity(@PathVariable("id") long id, Model m) {
		JewelEntity jewel = jewelService.selectProduct(id);
		ModelAndView model = new ModelAndView();
		Iterable<CategoryEntity> categories = searchCategoriesService.getAllCategoriesOrderByName();
		model.addObject(ConstantsViews.CATEGORIES, categories);
		model.addObject("jewel", jewel);
		model.addObject(ConstantsViews.FORMSEARCH, new SearchJewelForm());
		model.addObject("url", System.getenv("OPENSHIFT_DATA_DIR"));
		model.setViewName("web/product-details");
		if (!m.containsAttribute("cart")) {
			m.addAttribute("cart", new ArrayList<JewelEntity>());
		}
		return model;
	}

	@GetMapping("/endcart")
	public String goodbye(SessionStatus status) {
		status.setComplete();
		return "goodbye";
	}
}