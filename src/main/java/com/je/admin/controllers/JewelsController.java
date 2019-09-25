package com.je.admin.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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
import com.je.utils.constants.ConstantsJsp;

/**
 * The Class JewelsController.
 */
@Controller
@SessionAttributes({ "placeselected" })
public class JewelsController {

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

	/** The material service. */
	@Autowired
	private MetalService materialService;

	/** The categories service. */
	@Autowired
	private CategoriesService categoriesService;

	/** The set service. */
	@Autowired
	private SetService setService;

	/** The coin service. */
	@Autowired
	private CoinService coinService;

	private static final String VIEWSEARCHINVENTORY = "admin/jewels/revise/search";
	private static final String FORMJEWEL = "jewelForm";
	private static final String VIEWNEWJEWEL = "admin/jewels/newjewel/newJewel";

	/**
	 * Adds the category.
	 *
	 * @param category the category
	 * @return the string
	 */
	@RequestMapping(value = "/saveCategory")
	public ModelAndView addCategory(@ModelAttribute(ConstantsJsp.CATEGORY) CategoryEntity category) {
		categoriesService.save(category);
		return new ModelAndView(ConstantsJsp.SUCCESS, ConstantsJsp.ADMINFORM, new AdminForm());
	}

	/**
	 * Search by reference.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/searchByReference")
	public ModelAndView searchByReference() {
		ModelAndView model = new ModelAndView("admin/jewels/searchjewels/searchByReference");
		model.addObject(ConstantsJsp.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
		model.addObject(FORMJEWEL, new JewelEntity());
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Result search by reference.
	 *
	 * @param jewelForm the jewel form
	 * @return the model and view
	 */
	@RequestMapping(value = "resultsearchbyreference")
	public ModelAndView resultSearchByReference(@ModelAttribute(FORMJEWEL) JewelEntity jewelForm) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.JEWELS, jewelService.search(jewelForm));
		model.setViewName("admin/jewels/searchjewels/resultsearchbyreference");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Search update jewels.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/searchUpdateJewels")
	public ModelAndView searchUpdateJewels() {
		ModelAndView model = new ModelAndView("admin/jewels/updatejewel/searchtoupdatejewel");
		model.addObject(FORMJEWEL, new JewelEntity());
		model.addObject(ConstantsJsp.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Result search update jewels.
	 *
	 * @param jewelForm the jewel form
	 * @return the model and view
	 */
	@RequestMapping(value = "/resultSearchUpdateJewels")
	public ModelAndView resultSearchUpdateJewels(@ModelAttribute(FORMJEWEL) JewelEntity jewelForm) {
		ModelAndView model = new ModelAndView("admin/jewels/updatejewel/jewelsToUpdate");
		model.addObject(ConstantsJsp.JEWELS, jewelService.searchByReferenceAndCategory(jewelForm));
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject("toUpdateForm", new JewelEntity());
		return model;
	}

	/**
	 * Update jewels.
	 *
	 * @param toUpdate the to update
	 * @return the model and view
	 */
	@RequestMapping(value = "/updatejewel")
	public ModelAndView updateJewels(@ModelAttribute("toUpdate") JewelEntity toUpdate) {
		ModelAndView model = new ModelAndView("admin/jewels/updatejewel/newjewel");
		Long idj = toUpdate.getIdjewel();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		if (idj != null) {
			JewelEntity jewel = jewelService.selectProduct(idj);
			Iterable<CategoryEntity> categories = categoriesService.getAllCategoriesOrderByName();
			model.addObject(ConstantsJsp.CATEGORIES, categories);
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
			model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
			model.addObject(FORMJEWEL, jewel);
		} else {
			model.setViewName("admin/jewels/updatejewel/errorupdatejewel");
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
		ModelAndView model = new ModelAndView(VIEWNEWJEWEL);
		JewelEntity jewel = new JewelEntity();
		jewel.setActive(true);
		model.addObject(FORMJEWEL, jewel);
		model.addObject(ConstantsJsp.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Adds the jewels.
	 *
	 * @param jewelForm the jewel form
	 * @param result    the result
	 * @return the string
	 */
	@RequestMapping(value = "/saveJewel")
	public ModelAndView addJewelEntity(@ModelAttribute(FORMJEWEL) JewelEntity jewelForm, BindingResult result,
			Model m) {
		selectCategoryValidator.validate(jewelForm, result);
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		if (result.hasErrors()) {
			model.addObject(ConstantsJsp.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
			model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
			model.setViewName(VIEWNEWJEWEL);
		} else {
			// primero miro a ver si existe ya esa joya
			JewelEntity jewel = jewelService.searchByReferenceCategoryMetalPlace(jewelForm);
			if (jewel != null && !jewel.getIdjewel().equals(jewelForm.getIdjewel())) {
				model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
				model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
				model.addObject(ConstantsJsp.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
				model.setViewName(VIEWNEWJEWEL);
				result.rejectValue(ConstantsJsp.REFERENCE, "selectotherreference");
			} else {
				jewelForm.setPlace(placeService.getPlace(jewelForm.getPlace().getIdplace()));
				jewelForm = jewelService.addObject(jewelForm);
				model.setViewName("admin/jewels/newjewel/successjewel");
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
		model.setViewName("admin/jewels/sets/newset");
		model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		model.addObject("setForm", new NewSet());
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Adds the set.
	 *
	 * @param set the set
	 * @return the model and view
	 */
	@RequestMapping(value = "/addset")
	public ModelAndView addSet(@ModelAttribute("setForm") NewSet set) {
		setService.saveSet(set);
		return allsets();
	}

	/**
	 * Allsets.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/allSets")
	public ModelAndView allsets() {
		ModelAndView model = new ModelAndView("admin/jewels/sets/allsets");
		model.addObject("sets", setService.allSets());
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Newcoin.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/newcoin")
	public ModelAndView newcoin() {
		ModelAndView model = new ModelAndView("admin/coins/newcoin");
		model.addObject("coinForm", new CoinEntity());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Adds the coin.
	 *
	 * @param coin   the coin
	 * @param result the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/addcoin")
	public ModelAndView addCoin(@ModelAttribute("coinForm") CoinEntity coin, BindingResult result) {
		ModelAndView model = new ModelAndView();
		coinValidator.validate(coin, result);
		if (result.hasErrors()) {
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
			model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
			model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
			model.setViewName("admin/coins/newcoin");
		} else {
			coinService.save(coin);
			model.setViewName(ConstantsJsp.SUCCESS);
			model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
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
		ModelAndView model = new ModelAndView("admin/coins/allcoins");
		model.addObject("coins", coinService.allCoins());
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	@RequestMapping(value = "/searchjewelsactive")
	public ModelAndView searchjewelsactive() {
		ModelAndView model = new ModelAndView("admin/jewels/searchjewelsactive");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		model.addObject(FORMJEWEL, new JewelEntity());
		return model;
	}

	@RequestMapping(value = "/resultjewelsactive")
	public ModelAndView resultjewelsactive(@ModelAttribute(FORMJEWEL) JewelEntity jewelForm) {
		ModelAndView model = new ModelAndView("admin/jewels/searchjewels/resultsearchbyreference");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		jewelForm.setActive(true);
		model.addObject(ConstantsJsp.JEWELS, jewelService.searchAllActiveByPlace(jewelForm));
		return model;
	}

	@RequestMapping(value = "/checkinventory")
	public ModelAndView checkInventory() {
		ModelAndView model = new ModelAndView(VIEWSEARCHINVENTORY);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(FORMJEWEL, new JewelEntity());
		return model;
	}

	@RequestMapping(value = "/resultSearchRevise")
	public ModelAndView resultSearchRevise(@ModelAttribute(FORMJEWEL) JewelEntity jewel, BindingResult e) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		jewelFormValidator.validate(jewel, e);
		if (e.hasErrors()) {
			model.addObject(FORMJEWEL, new JewelEntity());
			model.setViewName(VIEWSEARCHINVENTORY);
		} else {
			model.addObject("toUpdateForm", new JewelEntity());
			model.addObject(ConstantsJsp.JEWELS, jewelService.searchByReference(jewel));
			model.setViewName("admin/jewels/revise/resultsearch");
		}
		return model;
	}

	@RequestMapping(value = "/revised")
	public ModelAndView revise(@ModelAttribute("toUpdateForm") JewelEntity jewel) {
		if (jewel.getIdjewel() == null) {
			ModelAndView model = new ModelAndView();
			model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
			model.addObject(FORMJEWEL, new JewelEntity());
			model.setViewName(VIEWSEARCHINVENTORY);
		} else {
			jewelService.revise(jewel);
		}
		return checkInventory();
	}

	@RequestMapping(value = "/image/{fileName}", method = RequestMethod.GET)
	public void getImage(@PathVariable String fileName, HttpServletRequest req, HttpServletResponse res)
			throws IOException {
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
}
