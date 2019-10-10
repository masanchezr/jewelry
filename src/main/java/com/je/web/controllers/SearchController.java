package com.je.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.dbaccess.entities.CategoryEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.services.categories.CategoriesService;
import com.je.services.search.SearchService;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;
import com.je.web.forms.SearchJewelForm;

/**
 * The Class SearchController.
 */
@Controller
@RequestMapping(value = "/busqueda")
public class SearchController {

	/** The search service. */
	@Autowired
	private SearchService searchService;

	/** The search categories service. */
	@Autowired
	private CategoriesService searchCategoriesService;

	/**
	 * Search.
	 * 
	 * @param search the search, los campos del formulario pueden ser nulos
	 * @param model  the model
	 * @return the string
	 */
	@PostMapping
	public ModelAndView search(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchJewelForm search) {
		Page<JewelEntity> page = searchService.searchActivesWithImg(search.getSearchname(), null, 1);
		Iterable<CategoryEntity> categories = searchCategoriesService.getAllCategoriesOrderByName();
		PlaceEntity place = new PlaceEntity();
		ModelAndView model = new ModelAndView("home");
		place.setIdplace(Constants.WEB);
		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());
		model.addObject(ConstantsJsp.CATEGORIES, categories);
		model.addObject(ConstantsJsp.JEWELS, page.getContent());
		model.addObject(ConstantsJsp.BREADCRUMBS, "Home");
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchJewelForm());
		model.addObject("deploymentLog", page);
		model.addObject("beginIndex", begin);
		model.addObject("endIndex", end);
		model.addObject("currentIndex", current);
		model.addObject("url", System.getenv("OPENSHIFT_DATA_DIR"));
		model.addObject("totalprice", 0);
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchJewelForm());
		return model;
	}
}