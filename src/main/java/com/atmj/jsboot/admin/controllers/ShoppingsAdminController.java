package com.atmj.jsboot.admin.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.admin.forms.AdminForm;
import com.atmj.jsboot.admin.forms.ShoppingSearch;
import com.atmj.jsboot.admin.validators.NewShoppingFormValidator;
import com.atmj.jsboot.admin.validators.ShoppingFormValidator;
import com.atmj.jsboot.dbaccess.entities.MetalEntity;
import com.atmj.jsboot.dbaccess.entities.ObjectShopEntity;
import com.atmj.jsboot.forms.SearchForm;
import com.atmj.jsboot.services.metal.MetalService;
import com.atmj.jsboot.services.nations.NationService;
import com.atmj.jsboot.services.places.PlaceService;
import com.atmj.jsboot.services.shoppings.QuarterMetal;
import com.atmj.jsboot.services.shoppings.Shopping;
import com.atmj.jsboot.services.shoppings.ShoppingService;
import com.atmj.jsboot.services.tracks.TrackService;
import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.string.Util;
import com.atmj.jsboot.validators.SearchFormValidator;

/**
 * The Class ShoppingsAdminController.
 */
@Controller
public class ShoppingsAdminController {

	/** The place service. */
	@Autowired
	private MetalService materialService;

	@Autowired
	private NationService nationservice;

	/** The place service. */
	@Autowired
	private PlaceService placeService;

	/** The shopping service. */
	@Autowired
	private ShoppingService shoppingService;

	@Autowired
	private TrackService trackservice;

	@Autowired
	private ShoppingFormValidator shoppingFormValidator;

	@Autowired
	private NewShoppingFormValidator newShoppingFormValidator;

	@Autowired
	private SearchFormValidator searchFormValidator;

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(ShoppingsAdminController.class);

	private static final String VIEWSEARCHSHOPPINGS = "admin/shoppings/searchshoppings/searchshoppings";
	private static final String VIEWSEARCHCLIENTADMIN = "admin/shoppings/newshop/searchclient";
	private static final String VIEWNEWSHOP = "admin/shoppings/newshop/newshop";
	private static final String FORMSHOP = "shopform";

	/**
	 * Search shoppings.
	 *
	 * @return the model and view
	 */
	@GetMapping("/searchShoppings")
	public ModelAndView searchShoppings() {
		ModelAndView model = new ModelAndView(VIEWSEARCHSHOPPINGS);
		model.addObject(ConstantsViews.SHOPPINGFORM, new ShoppingSearch());
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		return model;
	}

	/**
	 * Result shoppings.
	 *
	 * @param shopping the shopping
	 * @param result   the result
	 * @return the model and view
	 */
	@PostMapping("/resultShoppings")
	public ModelAndView resultShoppings(@ModelAttribute(ConstantsViews.SHOPPINGFORM) ShoppingSearch shopping,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		shoppingFormValidator.validate(shopping, result);
		if (result.hasErrors()) {
			model.addObject("shopping", shopping);
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
			model.setViewName(VIEWSEARCHSHOPPINGS);
		} else {
			List<Shopping> shoppings = shoppingService.searchShoppings(shopping.getDatefrom(), shopping.getDateuntil(),
					shopping.getPlace(), shopping.getNumshop());
			model.addObject("shoppings", shoppings);
			model.addObject(ConstantsViews.SHOPPINGFORM, new Shopping());
			model.setViewName("admin/shoppings/searchshoppings/resultshoppings");
		}
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Muestra la compra seleccionada
	 * 
	 * @param shopping the shopping
	 * @return the model and view
	 */
	@PostMapping("/updateShoppings")
	public ModelAndView updateShoppings(@ModelAttribute(ConstantsViews.SHOPPINGFORM) Shopping shopping) {
		ModelAndView model = new ModelAndView();
		Long idshop = shopping.getId();
		if (idshop == null) {
			model.addObject(ConstantsViews.SHOPPINGFORM, new ShoppingSearch());
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
			model.setViewName(VIEWSEARCHSHOPPINGS);
		} else {
			shopping = shoppingService.findShopByPK(idshop);
			model.addObject(ConstantsViews.SHOPPINGFORM, shopping);
			model.setViewName("admin/shoppings/searchshoppings/updateshopping");
			model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
		}
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	@GetMapping("/updateShopping{id}")
	public ModelAndView updateShopping(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("admin/shoppings/searchshoppings/updateshopping");
		Shopping shopping = shoppingService.findShopByPK(id);
		model.addObject(ConstantsViews.SHOPPINGFORM, shopping);
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
		return model;
	}

	/**
	 * Update shopping.
	 *
	 * @param shoppingForm the shopping form
	 * @param result       the result
	 * @return the model and view
	 */
	@PostMapping("/saveShopping")
	public ModelAndView saveShopping(@ModelAttribute(ConstantsViews.SHOPPINGFORM) Shopping shoppingForm) {
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		shoppingForm.setUser(user);
		shoppingService.update(shoppingForm);
		return searchShoppings();
	}

	/**
	 * Save shopping from admin
	 *
	 * @param shoppingForm the shopping form
	 * @param result       the result
	 * @return the model and view
	 */
	@PostMapping("/saveshop")
	public ModelAndView saveShop(@ModelAttribute(FORMSHOP) Shopping shoppingForm, BindingResult result) {
		ModelAndView model;
		newShoppingFormValidator.validate(shoppingForm, result);
		if (result.hasErrors()) {
			List<MetalEntity> materials = materialService.getAllMetalsActive();
			Iterator<MetalEntity> imaterials = materials.iterator();
			List<ObjectShopEntity> lop = new ArrayList<>();
			while (imaterials.hasNext()) {
				ObjectShopEntity op = new ObjectShopEntity();
				op.setMetal(imaterials.next());
				lop.add(op);
			}
			shoppingForm.setObjects(lop);
			model = new ModelAndView();
			model.addObject(FORMSHOP, shoppingForm);
			model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
			model.addObject(Constants.TRACKS, trackservice.getTracks());
			model.addObject(Constants.NATIONS, nationservice.getNations());
			model.setViewName(VIEWNEWSHOP);
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			shoppingForm.setUser(user);
			shoppingService.saveAdmin(shoppingForm);
			model = searchClient();
		}
		return model;
	}

	@GetMapping("/searchquartermaterial")
	public ModelAndView searchQuarterMetal() {
		ModelAndView model = new ModelAndView("admin/shoppings/quartersmetal/searchquarter");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.SHOPPINGFORM, new SearchForm());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		return model;
	}

	@PostMapping("/quartermaterial")
	public ModelAndView quarterMetal(@ModelAttribute(ConstantsViews.SHOPPINGFORM) SearchForm shopping,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		searchFormValidator.validate(shopping, result);
		if (result.hasErrors()) {
			model.setViewName("admin/shoppings/quartersmetal/searchquarter");
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		} else {
			List<QuarterMetal> quarters = shoppingService.searchGramsByMetal(shopping.getDatefrom(),
					shopping.getDateuntil(), shopping.getPlace());
			model.addObject("quarters", quarters);
			model.setViewName("admin/shoppings/quartersmetal/quarter");
			shopping.setPlace(placeService.getPlace(shopping.getPlace().getIdplace()));
		}
		model.addObject(ConstantsViews.SHOPPINGFORM, shopping);
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	@GetMapping("/searchgramsnull")
	public ModelAndView searchGramsNull() {
		ModelAndView model = new ModelAndView("admin/shoppings/searchshoppings/searchgramsnull");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.FORMSEARCH, new SearchForm());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		return model;
	}

	@PostMapping("/resultgramsnull")
	public ModelAndView resultGramsNull(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchForm form,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		searchFormValidator.validate(form, result);
		if (result.hasErrors()) {
			model.setViewName("admin/shoppings/searchshoppings/searchgramsnull");
			model.addObject(ConstantsViews.FORMSEARCH, form);
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		} else {
			List<Long> numshops = shoppingService.searchGramsNull(form.getDatefrom(), form.getDateuntil(),
					form.getPlace());
			model.addObject("nummissing", numshops);
			model.setViewName(ConstantsViews.VIEWSEARCHMISSINGSHOPPINGS);
		}
		return model;
	}

	@GetMapping("/tomelloso")
	public ModelAndView searchClient() {
		ModelAndView model = new ModelAndView(VIEWSEARCHCLIENTADMIN);
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		Shopping pawn = new Shopping();
		pawn.setNumshop(shoppingService.getNextNumber(user));
		pawn.setUser(user);
		model.addObject(FORMSHOP, pawn);
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * New pawn.
	 *
	 * @return the model and view
	 */
	@PostMapping("/newshop")
	public ModelAndView newshop(@ModelAttribute(FORMSHOP) Shopping pawn, BindingResult errors) {
		String dni = pawn.getNif();
		ModelAndView model = new ModelAndView();
		if (dni != null && dni.length() > 12) {
			errors.rejectValue(ConstantsViews.NIF, "niftoolong");
			model.setViewName(VIEWSEARCHCLIENTADMIN);
		} else if (!Util.isNifNie(dni)) {
			errors.rejectValue(ConstantsViews.NIF, "nifnotvalid");
			model.setViewName(VIEWSEARCHCLIENTADMIN);
		} else {
			Shopping client = shoppingService.searchClient(Util.refactorNIF(pawn.getNif()));
			List<MetalEntity> materials = materialService.getAllMetalsActive();
			Iterator<MetalEntity> imaterials = materials.iterator();
			List<ObjectShopEntity> lop = new ArrayList<>();
			while (imaterials.hasNext()) {
				ObjectShopEntity op = new ObjectShopEntity();
				op.setMetal(imaterials.next());
				lop.add(op);
			}
			pawn.setAddress(client.getAddress());
			pawn.setNif(client.getNif());
			pawn.setSurname(client.getSurname());
			pawn.setTown(client.getTown());
			pawn.setName(client.getName());
			pawn.setNation(client.getNation());
			pawn.setTrack(client.getTrack());
			pawn.setObjects(lop);
			model.setViewName(VIEWNEWSHOP);
			model.addObject(Constants.TRACKS, trackservice.getTracks());
			model.addObject(Constants.NATIONS, nationservice.getNations());
		}
		model.addObject(FORMSHOP, pawn);
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	@GetMapping("/exceltomelloso")
	public ModelAndView excelTomelloso() {
		ModelAndView model = new ModelAndView("admin/shoppings/exceltomelloso");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.FORMSEARCH, new SearchForm());
		return model;
	}

	@PostMapping("/downloadexcel")
	public void downloadexcel(
			@Validated(SearchFormValidator.class) @ModelAttribute(ConstantsViews.FORMSEARCH) SearchForm form,
			HttpServletResponse response) {
		File file = shoppingService.generateExcel(form.getDatefrom(), form.getDateuntil());
		try (InputStream inputStream = new FileInputStream(file)) {
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment; filename=workbook.xlsx");
			IOUtils.copy(inputStream, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			logger.error(java.util.logging.Level.SEVERE.getName());
		}
	}
}
