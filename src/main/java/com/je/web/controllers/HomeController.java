package com.je.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.je.dbaccess.entities.CategoryEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.services.categories.CategoriesService;
import com.je.services.jewels.JewelService;
import com.je.utils.constants.Constants;
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

	@RequestMapping(value = "/index.html")
	public ModelAndView home() {
		// Recuperar toda la lista de categorias
		Iterable<CategoryEntity> categories = searchCategoriesService.getAllCategoriesOrderByName();
		PlaceEntity place = new PlaceEntity();
		Page<JewelEntity> page;
		ModelAndView model = new ModelAndView("home");
		place.setIdplace(Constants.WEB);
		// Recuperamos productos activos
		// page = jewelService.searchAllActive(1);
		page = jewelService.searchWithImg(1);
		// page = jewelService.searchPageableByPlace(1, place);
		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());
		model.addObject("categories", categories);
		model.addObject("jewels", page.getContent());
		model.addObject("breadcrumbs", "Home");
		model.addObject("searchForm", new SearchJewelForm());
		model.addObject("deploymentLog", page);
		model.addObject("beginIndex", begin);
		model.addObject("endIndex", end);
		model.addObject("currentIndex", current);
		model.addObject("totalprice", 0);
		model.addObject("search", new SearchJewelForm());
		return model;
	}

	@RequestMapping(value = "/page/{pageNumber}")
	public ModelAndView page(@PathVariable Integer pageNumber) {
		// Recuperar toda la lista de categorias
		Iterable<CategoryEntity> categories = searchCategoriesService.getAllCategoriesOrderByName();
		PlaceEntity place = new PlaceEntity();
		Page<JewelEntity> page;
		place.setIdplace(Constants.WEB);
		// Recuperamos productos activos
		page = jewelService.searchWithImg(pageNumber);
		// page = jewelService.searchPageableByPlace(pageNumber, place);
		if (page == null) {
			page = jewelService.searchWithImg(1);
		}
		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());
		ModelAndView model = new ModelAndView("home");
		model.addObject("categories", categories);
		model.addObject("jewels", page.getContent());
		model.addObject("breadcrumbs", "Home");
		model.addObject("searchForm", new SearchJewelForm());
		model.addObject("deploymentLog", page);
		model.addObject("beginIndex", begin);
		model.addObject("endIndex", end);
		model.addObject("currentIndex", current);
		model.addObject("search", new SearchJewelForm());
		return model;
	}

	/**
	 * Contact.
	 * 
	 * @return the string
	 */
	@RequestMapping(value = "/contacto")
	public ModelAndView contact() {
		ModelAndView model = new ModelAndView("contact");
		model.addObject("search", new SearchJewelForm());
		model.addObject("categories", searchCategoriesService.getAllCategoriesOrderByName());
		return model;
	}

	/**
	 * Faqs.
	 * 
	 * @return the string
	 */
	@RequestMapping(value = "/faqs")
	public ModelAndView faqs() {
		ModelAndView model = new ModelAndView("faqs");
		model.addObject("search", new SearchJewelForm());
		model.addObject("categories", searchCategoriesService.getAllCategoriesOrderByName());
		return model;
	}

	/**
	 * Terms.
	 * 
	 * @return the string
	 */
	@RequestMapping(value = "/terms")
	public ModelAndView terms() {
		ModelAndView model = new ModelAndView("termsconditions");
		model.addObject("search", new SearchJewelForm());
		return model;
	}

	@RequestMapping(value = "/{keyword}")
	public ModelAndView searchByKeyWord(@PathVariable String keyword) {
		Iterable<CategoryEntity> categories = searchCategoriesService.getAllCategoriesOrderByName();
		List<JewelEntity> jewels = jewelService.searchJewelsByKeyWordCategory(keyword);
		if (jewels == null || jewels.isEmpty()) {
			return home();
		} else {
			ModelAndView model = new ModelAndView();
			model.addObject("categories", categories);
			model.addObject("jewels", jewels);
			model.addObject("searchForm", new SearchJewelForm());
			model.setViewName("home");
			return model;
		}
	}

	/**
	 * Selected jewel.
	 * 
	 * @param id
	 *            the id
	 * @return the model and view
	 */
	@RequestMapping(value = "/productoSeleccionado{id}")
	public ModelAndView selectedJewelEntity(@PathVariable("id") long id, Model m) {
		JewelEntity jewel = jewelService.selectProduct(id);
		ModelAndView model = new ModelAndView();
		Iterable<CategoryEntity> categories = searchCategoriesService.getAllCategoriesOrderByName();
		model.addObject("categories", categories);
		model.addObject("jewel", jewel);
		model.addObject("breadcrumbs", "Home >> Producto seleccionado");
		model.addObject("search", new SearchJewelForm());
		model.addObject("url", System.getenv("OPENSHIFT_DATA_DIR"));
		model.setViewName("resultSearch");
		if (!m.containsAttribute("cart")) {
			m.addAttribute("cart", new ArrayList<JewelEntity>());
		}
		return model;
	}
}