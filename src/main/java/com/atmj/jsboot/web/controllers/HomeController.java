package com.atmj.jsboot.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atmj.jsboot.dbaccess.entities.CategoryEntity;
import com.atmj.jsboot.dbaccess.entities.JewelEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.services.categories.CategoriesService;
import com.atmj.jsboot.services.jewels.JewelService;
import com.atmj.jsboot.services.mails.EmailService;
import com.atmj.jsboot.services.search.SearchService;
import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.web.forms.DataClientForm;
import com.atmj.jsboot.web.forms.MessageForm;
import com.atmj.jsboot.web.forms.SearchJewelForm;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({ "cart" })
public class HomeController {

	/** The search categories service. */
	@Autowired
	private CategoriesService searchCategoriesService;

	@Autowired
	private EmailService emailService;

	/** The jewel service. */
	@Autowired
	private JewelService jewelService;

	/** The search service. */
	@Autowired
	private SearchService searchService;

	@PostMapping("/sendMessage")
	public ModelAndView sendMessage(@Valid MessageForm message, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.FORMSEARCH, new SearchJewelForm());
		model.addObject(ConstantsViews.CATEGORIES, searchCategoriesService.getAllCategoriesOrderByName());
		if (result.hasErrors()) {
			model.setViewName("web/messagesent");
			model.addObject("messageForm", new MessageForm());
		} else {
			emailService.sendSimpleMessage(null, null, null);
			model.setViewName("web/messagesent");
		}
		return model;
	}

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

	@PostMapping("/busqueda")
	public ModelAndView search(@ModelAttribute("searchDateForm") SearchJewelForm searchForm) {
		Page<JewelEntity> page = searchService.searchActives(searchForm.getSearchname(), 1);
		Iterable<CategoryEntity> categories = searchCategoriesService.getAllCategoriesOrderByName();
		ModelAndView model = new ModelAndView("web/indexsearch");
		model.addObject(ConstantsViews.CATEGORIES, categories);
		model.addObject(ConstantsViews.JEWELS, page.getContent());
		model.addObject(ConstantsViews.BREADCRUMBS, "Home");
		model.addObject(ConstantsViews.FORMSEARCH, searchForm);
		model.addObject("page", page);
		return model;
	}

	@GetMapping("/page/{search}/{pageNumber}")
	public ModelAndView search(@PathVariable String search, @PathVariable Integer pageNumber) {
		Page<JewelEntity> page = searchService.searchActives(search, pageNumber);
		Iterable<CategoryEntity> categories = searchCategoriesService.getAllCategoriesOrderByName();
		ModelAndView model = new ModelAndView("web/indexsearch");
		model.addObject(ConstantsViews.CATEGORIES, categories);
		model.addObject(ConstantsViews.JEWELS, page.getContent());
		model.addObject(ConstantsViews.BREADCRUMBS, "Home");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchJewelForm());
		model.addObject("page", page);
		return model;
	}

	@GetMapping("/page/{pageNumber}")
	public ModelAndView page(@PathVariable Integer pageNumber) {
		// Recuperar toda la lista de categorias
		Iterable<CategoryEntity> categories = searchCategoriesService.getAllCategoriesOrderByName();
		Page<JewelEntity> page;
		// Recuperamos productos activos
		page = jewelService.searchActive(pageNumber);
		if (page == null) {
			page = jewelService.searchActive(1);
		}
		ModelAndView model = new ModelAndView("web/index");
		model.addObject(ConstantsViews.CATEGORIES, categories);
		model.addObject(ConstantsViews.JEWELS, page.getContent());
		model.addObject(ConstantsViews.BREADCRUMBS, "Home");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchJewelForm());
		model.addObject("page", page);
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
		model.addObject("messageForm", new MessageForm());
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
		return searchByKeywordPagination(keyword, 1);
	}

	@GetMapping("/{keyword}/{pageNumber}")
	public ModelAndView searchByKeyWord(@PathVariable String keyword, @PathVariable Integer pageNumber) {
		return searchByKeywordPagination(keyword, pageNumber);
	}

	private ModelAndView searchByKeywordPagination(String keyword, int pagenumber) {
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

	/**
	 * The shopping cart (list of products) is stored in session. Simply inject it
	 * using method argument
	 */
	@GetMapping("/addProduct/{id}")
	public String addProduct(@PathVariable Long id, @ModelAttribute("cart") List<JewelEntity> cart,
			RedirectAttributes attributes) {
		cart.add(jewelService.selectProduct(id));
		attributes.addFlashAttribute("cart", cart);
		return "redirect:/productoSeleccionado" + id;
	}

	@GetMapping("/cart")
	public ModelAndView cart() {
		ModelAndView model = new ModelAndView("web/cart");
		Iterable<CategoryEntity> categories = searchCategoriesService.getAllCategoriesOrderByName();
		model.addObject(ConstantsViews.CATEGORIES, categories);
		model.addObject("dataForm", new DataClientForm());
		model.addObject(ConstantsViews.FORMSEARCH, new SearchJewelForm());
		return model;
	}

	@PostMapping("/endcart")
	public String goodbye(SessionStatus status) {
		status.setComplete();
		return "goodbye";
	}

	@ModelAttribute("cart")
	public List<JewelEntity> todos() {
		return new ArrayList<>();
	}
}