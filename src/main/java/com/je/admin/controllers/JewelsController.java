package com.je.admin.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.je.admin.forms.AdminForm;
import com.je.admin.validators.CoinFormValidator;
import com.je.admin.validators.JewelFormValidator;
import com.je.admin.validators.SelectCategoryValidator;
import com.je.dbaccess.entities.CategoryEntity;
import com.je.dbaccess.entities.CoinEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.forms.Category;
import com.je.forms.Coin;
import com.je.forms.Jewel;
import com.je.services.categories.CategoriesService;
import com.je.services.coins.CoinService;
import com.je.services.jewels.JewelService;
import com.je.services.metal.MetalService;
import com.je.services.places.PlaceService;
import com.je.services.sets.NewSet;
import com.je.services.sets.SetService;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsViews;

/**
 * The Class JewelsController.
 */
@Controller
@SessionAttributes({ "placeselected" })
public class JewelsController {

	/** The categories service. */
	@Autowired
	private CategoriesService categoriesService;

	/** The coin service. */
	@Autowired
	private CoinService coinService;

	/** The jewel service. */
	@Autowired
	private JewelService jewelService;

	/** The material service. */
	@Autowired
	private MetalService materialService;

	/** The place service. */
	@Autowired
	private PlaceService placeService;

	/** The set service. */
	@Autowired
	private SetService setService;

	/** The coin validator. */
	@Autowired
	private CoinFormValidator coinValidator;

	@Autowired
	private JewelFormValidator jewelFormValidator;

	/** The select category validator. */
	@Autowired
	private SelectCategoryValidator selectCategoryValidator;

	@Autowired
	private Mapper mapper;

	private static final String VIEWSEARCHINVENTORY = "admin/jewels/revise/search";
	private static final String FORMJEWEL = "jewelForm";
	private static final String VIEWNEWJEWEL = "admin/jewels/newjewel/newJewel";

	/**
	 * Adds the category.
	 *
	 * @param category the category
	 * @return the string
	 */
	@PostMapping("/saveCategory")
	public ModelAndView addCategory(@ModelAttribute(ConstantsViews.CATEGORY) Category category) {
		categoriesService.save(mapper.map(category, CategoryEntity.class));
		return new ModelAndView("admin/success", ConstantsViews.ADMINFORM, new AdminForm());
	}

	/**
	 * Search by reference.
	 *
	 * @return the model and view
	 */
	@GetMapping("/searchByReference")
	public ModelAndView searchByReference() {
		ModelAndView model = new ModelAndView("admin/jewels/searchjewels/searchByReference");
		model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
		model.addObject(FORMJEWEL, new JewelEntity());
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Result search by reference.
	 *
	 * @param jewelForm the jewel form
	 * @return the model and view
	 */
	@PostMapping("resultsearchbyreference")
	public ModelAndView resultSearchByReference(@ModelAttribute(FORMJEWEL) Jewel jewelForm) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.JEWELS, jewelService.search(mapper.map(jewelForm, JewelEntity.class)));
		model.setViewName("admin/jewels/searchjewels/resultsearchbyreference");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Search update jewels.
	 *
	 * @return the model and view
	 */
	@GetMapping("/searchUpdateJewels")
	public ModelAndView searchUpdateJewels() {
		ModelAndView model = new ModelAndView("admin/jewels/updatejewel/searchtoupdatejewel");
		model.addObject(FORMJEWEL, new JewelEntity());
		model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Result search update jewels.
	 *
	 * @param jewelForm the jewel form
	 * @return the model and view
	 */
	@PostMapping("/resultSearchUpdateJewels")
	public ModelAndView resultSearchUpdateJewels(@ModelAttribute(FORMJEWEL) Jewel jewelForm) {
		ModelAndView model = new ModelAndView("admin/jewels/updatejewel/jewelsToUpdate");
		model.addObject(ConstantsViews.JEWELS,
				jewelService.searchByReferenceAndCategory(mapper.map(jewelForm, JewelEntity.class)));
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject("toUpdateForm", new JewelEntity());
		return model;
	}

	/**
	 * Update jewels.
	 *
	 * @param toUpdate the to update
	 * @return the model and view
	 */
	@PostMapping("/updatejewel")
	public ModelAndView updateJewels(@ModelAttribute("toUpdate") Jewel toUpdate) {
		ModelAndView model = new ModelAndView("admin/jewels/updatejewel/newJewel");
		Long idj = toUpdate.getIdjewel();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		if (idj != null) {
			JewelEntity jewel = jewelService.selectProduct(idj);
			Iterable<CategoryEntity> categories = categoriesService.getAllCategoriesOrderByName();
			model.addObject(ConstantsViews.CATEGORIES, categories);
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
			model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
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
	@GetMapping("/newJewel")
	public ModelAndView newJewelEntity() {
		ModelAndView model = new ModelAndView(VIEWNEWJEWEL);
		JewelEntity jewel = new JewelEntity();
		jewel.setActive(true);
		model.addObject(FORMJEWEL, jewel);
		model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Adds the jewels.
	 *
	 * @param jewelForm the jewel form
	 * @param result    the result
	 * @return the string
	 */
	@PostMapping("/saveJewel")
	public ModelAndView addJewelEntity(@ModelAttribute(FORMJEWEL) Jewel jewelForm, BindingResult result, Model m) {
		selectCategoryValidator.validate(jewelForm, result);
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		if (result.hasErrors()) {
			model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
			model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
			model.setViewName(VIEWNEWJEWEL);
		} else {
			// primero miro a ver si existe ya esa joya,
			JewelEntity jewelf = mapper.map(jewelForm, JewelEntity.class);
			JewelEntity jewel = jewelService.searchByReferenceCategoryMetalPlace(jewelf);
			if (jewel != null && !jewel.getIdjewel().equals(jewelForm.getIdjewel())) {
				model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
				model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
				model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
				model.setViewName(VIEWNEWJEWEL);
				result.rejectValue(ConstantsViews.REFERENCE, "selectotherreference");
			} else {
				jewelf.setPlace(placeService.getPlace(jewelForm.getPlace().getIdplace()));
				jewelf = jewelService.addObject(jewelf);
				model.setViewName("admin/jewels/newjewel/successjewel");
				model.addObject("jewel", jewelf);
				m.addAttribute("placeselected", jewelf.getPlace());
			}
		}
		return model;
	}

	/**
	 * New set.
	 *
	 * @return the model and view
	 */
	@GetMapping("/newSet")
	public ModelAndView newSet() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/jewels/sets/newset");
		model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		model.addObject("setForm", new NewSet());
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Adds the set.
	 *
	 * @param set the set
	 * @return the model and view
	 */
	@GetMapping("/addset")
	public ModelAndView addSet(@ModelAttribute("setForm") NewSet set) {
		setService.saveSet(set);
		return allsets();
	}

	/**
	 * Allsets.
	 *
	 * @return the model and view
	 */
	@GetMapping("/allSets")
	public ModelAndView allsets() {
		ModelAndView model = new ModelAndView("admin/jewels/sets/allsets");
		model.addObject("sets", setService.allSets());
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Newcoin.
	 *
	 * @return the model and view
	 */
	@GetMapping("/newcoin")
	public ModelAndView newcoin() {
		ModelAndView model = new ModelAndView("admin/coins/newcoin");
		model.addObject("coinForm", new CoinEntity());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Adds the coin.
	 *
	 * @param coin   the coin
	 * @param result the result
	 * @return the model and view
	 */
	@GetMapping("/addcoin")
	public ModelAndView addCoin(@ModelAttribute("coinForm") Coin coin, BindingResult result) {
		ModelAndView model = new ModelAndView();
		coinValidator.validate(coin, result);
		if (result.hasErrors()) {
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
			model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
			model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
			model.setViewName("admin/coins/newcoin");
		} else {
			coinService.save(mapper.map(coin, CoinEntity.class));
			model.setViewName("admin/success");
			model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		}
		return model;
	}

	/**
	 * Allcoins.
	 *
	 * @return the model and view
	 */
	@GetMapping("/allcoins")
	public ModelAndView allcoins() {
		ModelAndView model = new ModelAndView("admin/coins/allcoins");
		model.addObject("coins", coinService.allCoins());
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	@GetMapping("/searchjewelsactive")
	public ModelAndView searchjewelsactive() {
		ModelAndView model = new ModelAndView("admin/jewels/searchjewelsactive");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		model.addObject(FORMJEWEL, new JewelEntity());
		return model;
	}

	@PostMapping("/resultjewelsactive")
	public ModelAndView resultjewelsactive(@ModelAttribute(FORMJEWEL) Jewel jewelForm) {
		ModelAndView model = new ModelAndView("admin/jewels/searchjewels/resultsearchbyreference");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		jewelForm.setActive(true);
		model.addObject(ConstantsViews.JEWELS,
				jewelService.searchAllActiveByPlace(mapper.map(jewelForm, JewelEntity.class)));
		return model;
	}

	@GetMapping("/checkinventory")
	public ModelAndView checkInventory() {
		ModelAndView model = new ModelAndView(VIEWSEARCHINVENTORY);
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(FORMJEWEL, new Jewel());
		return model;
	}

	@PostMapping("/resultSearchRevise")
	public ModelAndView resultSearchRevise(@ModelAttribute(FORMJEWEL) Jewel jewel, BindingResult e) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		jewelFormValidator.validate(jewel, e);
		if (e.hasErrors()) {
			model.addObject(FORMJEWEL, new Jewel());
			model.setViewName(VIEWSEARCHINVENTORY);
		} else {
			model.addObject("toUpdateForm", new Jewel());
			model.addObject(ConstantsViews.JEWELS, jewelService.searchByReference(mapper.map(jewel, JewelEntity.class)));
			model.setViewName("admin/jewels/revise/resultsearch");
		}
		return model;
	}

	@GetMapping("/revised")
	public ModelAndView revise(@ModelAttribute("toUpdateForm") Jewel jewel) {
		if (jewel.getIdjewel() == null) {
			ModelAndView model = new ModelAndView();
			model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
			model.addObject(FORMJEWEL, new Jewel());
			model.setViewName(VIEWSEARCHINVENTORY);
		} else {
			jewelService.revise(mapper.map(jewel, JewelEntity.class));
		}
		return checkInventory();
	}

	@GetMapping("/image/{fileName}")
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

	@GetMapping("/endnewjewel")
	public String goodbye(SessionStatus status) {
		status.setComplete();
		return "goodbye";
	}
}
