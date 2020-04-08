package com.je.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.je.dbaccess.entities.CategoryEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.services.categories.CategoriesService;
import com.je.services.search.SearchService;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsViews;
import com.je.web.forms.SearchJewelForm;

/**
 * The Class SearchController.
 */
@Controller
@RequestMapping("/busqueda")
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
	@GetMapping
	public ModelAndView search(@RequestParam String searchname) {
		Page<JewelEntity> page = searchService.searchActives(searchname, 1);
		Iterable<CategoryEntity> categories = searchCategoriesService.getAllCategoriesOrderByName();
		PlaceEntity place = new PlaceEntity();
		ModelAndView model = new ModelAndView("web/index");
		place.setIdplace(Constants.WEB);
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
		model.addObject("url", System.getenv("OPENSHIFT_DATA_DIR"));
		model.addObject(ConstantsViews.FORMSEARCH, new SearchJewelForm());
		return model;
	}
}