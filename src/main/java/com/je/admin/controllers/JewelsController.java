package com.je.admin.controllers;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.admin.validators.CoinFormValidator;
import com.je.admin.validators.JewelFormValidator;
import com.je.admin.validators.SelectCategoryValidator;
import com.je.dbaccess.entities.CategoryEntity;
import com.je.dbaccess.entities.CoinEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.services.categories.CategoriesService;
import com.je.services.coins.CoinService;
import com.je.services.jewels.JewelService;
import com.je.services.metal.MetalService;
import com.je.services.places.PlaceService;
import com.je.services.sets.NewSet;
import com.je.services.sets.SetService;
import com.je.utils.constants.Constants;

/**
 * The Class JewelsController.
 */
@Controller
@SessionAttributes({ "placeselected" })
public class JewelsController {

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(JewelsController.class);

	/** The select category validator. */
	@Autowired
	private SelectCategoryValidator selectCategoryValidator;

	@Autowired
	private JewelFormValidator jewelFormValidator;

	/** The coin validator. */
	@Autowired
	private CoinFormValidator coinValidator;

	/** The jewel service. */
	@Autowired
	private JewelService jewelService;

	/** The place service. */
	@Autowired
	private PlaceService placeService;

	/** The metal service. */
	@Autowired
	private MetalService metalService;

	/** The categories service. */
	@Autowired
	private CategoriesService categoriesService;

	/** The set service. */
	@Autowired
	private SetService setService;

	/** The coin service. */
	@Autowired
	private CoinService coinService;

	/**
	 * Adds the category.
	 *
	 * @param category
	 *            the category
	 * @return the string
	 */
	@RequestMapping(value = "/saveCategory")
	public ModelAndView addCategory(@ModelAttribute("category") CategoryEntity category) {
		categoriesService.save(category);
		return new ModelAndView("success", "adminForm", new AdminForm());
	}

	/**
	 * Search by reference.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/searchByReference")
	public ModelAndView searchByReference() {
		ModelAndView model = new ModelAndView("searchbyreference");
		model.addObject("categories", categoriesService.getAllCategoriesOrderByName());
		model.addObject("places", placeService.getAllPlaces());
		model.addObject("metals", metalService.getAllMetals());
		model.addObject("jewelForm", new JewelEntity());
		model.addObject("adminForm", new AdminForm());
		return model;
	}

	/**
	 * Result search by reference.
	 *
	 * @param jewelForm
	 *            the jewel form
	 * @return the model and view
	 */
	@RequestMapping(value = "resultsearchbyreference")
	public ModelAndView resultSearchByReference(@ModelAttribute("jewelForm") JewelEntity jewelForm) {
		ModelAndView model = new ModelAndView();
		model.addObject("jewels", jewelService.search(jewelForm));
		model.setViewName("resultsearchbyreference");
		model.addObject("adminForm", new AdminForm());
		return model;
	}

	/**
	 * Search update jewels.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/searchUpdateJewels")
	public ModelAndView searchUpdateJewels() {
		ModelAndView model = new ModelAndView("searchtoupdatejewel");
		model.addObject("jewelForm", new JewelEntity());
		model.addObject("categories", categoriesService.getAllCategoriesOrderByName());
		model.addObject("adminForm", new AdminForm());
		return model;
	}

	/**
	 * Result search update jewels.
	 *
	 * @param jewelForm
	 *            the jewel form
	 * @return the model and view
	 */
	@RequestMapping(value = "/resultSearchUpdateJewels")
	public ModelAndView resultSearchUpdateJewels(@ModelAttribute("jewelForm") JewelEntity jewelForm) {
		ModelAndView model = new ModelAndView("resultsearchupdatejewels");
		model.addObject("jewels", jewelService.searchByReferenceAndCategory(jewelForm));
		model.addObject("adminForm", new AdminForm());
		model.addObject("toUpdateForm", new JewelEntity());
		return model;
	}

	/**
	 * Update jewels.
	 *
	 * @param toUpdate
	 *            the to update
	 * @return the model and view
	 */
	@RequestMapping(value = "/updatejewel")
	public ModelAndView updateJewels(@ModelAttribute("toUpdate") JewelEntity toUpdate) {
		ModelAndView model = new ModelAndView("newjewel");
		Long idj = toUpdate.getIdjewel();
		log.debug("idj:" + idj);
		model.addObject("adminForm", new AdminForm());
		if (idj != null) {
			JewelEntity jewel = jewelService.selectProduct(idj);
			Iterable<CategoryEntity> categories = categoriesService.getAllCategoriesOrderByName();
			model.addObject("categories", categories);
			model.addObject("places", placeService.getAllPlaces());
			model.addObject("metals", metalService.getAllMetals());
			model.addObject("jewelForm", jewel);
		} else {
			model.setViewName("errorupdatejewel");
		}
		return model;
	}

	/**
	 * New jewel.
	 *
	 * @return the model and view para crear nuevas joyas
	 */
	@RequestMapping(value = "/newJewel")
	public ModelAndView newJewelEntity() {
		ModelAndView model = new ModelAndView("newJewel");
		JewelEntity jewel = new JewelEntity();
		jewel.setActive(true);
		model.addObject("jewelForm", jewel);
		model.addObject("categories", categoriesService.getAllCategoriesOrderByName());
		model.addObject("places", placeService.getAllPlaces());
		model.addObject("metals", metalService.getAllMetals());
		model.addObject("adminForm", new AdminForm());
		return model;
	}

	/**
	 * Adds the jewels.
	 *
	 * @param jewelForm
	 *            the jewel form
	 * @param result
	 *            the result
	 * @return the string
	 */
	@RequestMapping(value = "/saveJewel")
	public ModelAndView addJewelEntity(@ModelAttribute("jewelForm") JewelEntity jewelForm, BindingResult result,
			Model m) {
		log.info("antes de validar");
		selectCategoryValidator.validate(jewelForm, result);
		ModelAndView model = new ModelAndView();
		model.addObject("adminForm", new AdminForm());
		if (result.hasErrors()) {
			log.info("en el if de hay errores");
			model.addObject("categories", categoriesService.getAllCategoriesOrderByName());
			model.addObject("places", placeService.getAllPlaces());
			model.addObject("metals", metalService.getAllMetals());
			model.setViewName("newJewel");
		} else {
			// primero miro a ver si existe ya esa joya
			JewelEntity jewel = jewelService.searchByReferenceCategoryMetalPlace(jewelForm);
			if (jewel != null && !jewel.getIdjewel().equals(jewelForm.getIdjewel())) {
				model.addObject("places", placeService.getAllPlaces());
				model.addObject("metals", metalService.getAllMetals());
				model.addObject("categories", categoriesService.getAllCategoriesOrderByName());
				model.setViewName("newJewel");
				result.rejectValue("reference", "selectotherreference");
			} else {
				jewelForm.setPlace(placeService.getPlace(jewelForm.getPlace().getIdplace()));
				jewelForm = jewelService.addObject(jewelForm);
				model.setViewName("successjewel");
				model.addObject("jewel", jewelForm);
				m.addAttribute("placeselected", jewelForm.getPlace());
			}
		}
		return model;
	}

	/**
	 * New set.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/newSet")
	public ModelAndView newSet() {
		ModelAndView model = new ModelAndView();
		model.setViewName("newset");
		model.addObject("metals", metalService.getAllMetals());
		model.addObject("places", placeService.getAllPlaces());
		model.addObject("setForm", new NewSet());
		model.addObject("adminForm", new AdminForm());
		return model;
	}

	/**
	 * Adds the set.
	 *
	 * @param set
	 *            the set
	 * @return the model and view
	 */
	@RequestMapping(value = "/addset")
	public ModelAndView addSet(@ModelAttribute("setForm") NewSet set) {
		ModelAndView model = new ModelAndView("success");
		setService.saveSet(set);
		model.addObject("adminForm", new AdminForm());
		return model;
	}

	/**
	 * Allsets.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/allSets")
	public ModelAndView allsets() {
		ModelAndView model = new ModelAndView("allsets");
		model.addObject("sets", setService.allSets());
		model.addObject("adminForm", new AdminForm());
		return model;
	}

	/**
	 * Newcoin.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/newcoin")
	public ModelAndView newcoin() {
		ModelAndView model = new ModelAndView("newcoin");
		model.addObject("coinForm", new CoinEntity());
		model.addObject("places", placeService.getAllPlaces());
		model.addObject("metals", metalService.getAllMetals());
		model.addObject("adminForm", new AdminForm());
		return model;
	}

	/**
	 * Adds the coin.
	 *
	 * @param coin
	 *            the coin
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/addcoin")
	public ModelAndView addCoin(@ModelAttribute("coinForm") CoinEntity coin, BindingResult result) {
		ModelAndView model = new ModelAndView();
		coinValidator.validate(coin, result);
		if (result.hasErrors()) {
			model.addObject("places", placeService.getAllPlaces());
			model.addObject("metals", metalService.getAllMetals());
			model.addObject("adminForm", new AdminForm());
			model.setViewName("newcoin");
		} else {
			coinService.save(coin);
			model.setViewName("success");
			model.addObject("adminForm", new AdminForm());
		}
		return model;
	}

	/**
	 * Allcoins.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/allcoins")
	public ModelAndView allcoins() {
		ModelAndView model = new ModelAndView("allcoins");
		model.addObject("coins", coinService.allCoins());
		model.addObject("adminForm", new AdminForm());
		return model;
	}

	@RequestMapping(value = "/searchjewelsactive")
	public ModelAndView searchjewelsactive() {
		ModelAndView model = new ModelAndView("searchjewelsactive");
		model.addObject("adminForm", new AdminForm());
		model.addObject("places", placeService.getAllPlaces());
		model.addObject("jewelForm", new JewelEntity());
		return model;
	}

	@RequestMapping(value = "/resultjewelsactive")
	public ModelAndView resultjewelsactive(@ModelAttribute("jewelForm") JewelEntity jewelForm) {
		ModelAndView model = new ModelAndView("resultsearchbyreference");
		model.addObject("adminForm", new AdminForm());
		jewelForm.setActive(true);
		model.addObject("jewels", jewelService.searchAllActiveByPlace(jewelForm));
		return model;
	}

	@RequestMapping(value = "/checkinventory")
	public ModelAndView checkInventory() {
		ModelAndView model = new ModelAndView("searchinventory");
		model.addObject("adminForm", new AdminForm());
		model.addObject("jewelForm", new JewelEntity());
		return model;
	}

	@RequestMapping(value = "/resultSearchRevise")
	public ModelAndView resultSearchRevise(@ModelAttribute("jewelForm") JewelEntity jewel, BindingResult e) {
		ModelAndView model = new ModelAndView();
		model.addObject("adminForm", new AdminForm());
		jewelFormValidator.validate(jewel, e);
		if (e.hasErrors()) {
			model.addObject("jewelForm", new JewelEntity());
			model.setViewName("searchinventory");
		} else {
			model.addObject("toUpdateForm", new JewelEntity());
			model.addObject("jewels", jewelService.searchByReference(jewel));
			model.setViewName("resultsearchrevise");
		}
		return model;
	}

	@RequestMapping(value = "/revised")
	public ModelAndView revise(@ModelAttribute("toUpdateForm") JewelEntity jewel) {
		if (jewel.getIdjewel() == null) {
			ModelAndView model = new ModelAndView();
			model.addObject("adminForm", new AdminForm());
			model.addObject("jewelForm", new JewelEntity());
			model.setViewName("searchinventory");
		} else {
			jewelService.revise(jewel);
		}
		return checkInventory();
	}

	@RequestMapping(value = "/image/{fileName}", method = RequestMethod.GET)
	public void getImage(@PathVariable String fileName, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		File file = new File(System.getenv(Constants.OPENSHIFT_DATA_DIR).concat(fileName).concat(Constants.JPG));
		res.setHeader("Cache-Control", "no-store");
		res.setHeader("Pragma", "no-cache");
		res.setDateHeader("Expires", 0);
		res.setContentType("image/x-png");
		ServletOutputStream ostream = res.getOutputStream();
		IOUtils.copy(new FileInputStream(file), ostream);
		ostream.flush();
		ostream.close();
	}

	/*
	 * @RequestMapping(value = "/setGrams") public ModelAndView setGrams() {
	 * ModelAndView model = new ModelAndView(); model.addObject("adminForm", new
	 * AdminForm()); jewelService.setGramsGold(); model.setViewName("success");
	 * return model; }
	 */
}
