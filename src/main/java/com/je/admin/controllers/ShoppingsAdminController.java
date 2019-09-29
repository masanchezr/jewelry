package com.je.admin.controllers;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.admin.forms.ShoppingSearch;
import com.je.admin.validators.NewShoppingFormValidator;
import com.je.admin.validators.ShoppingFormValidator;
import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.entities.ObjectShopEntity;
import com.je.forms.SearchForm;
import com.je.services.metal.MetalService;
import com.je.services.nations.NationService;
import com.je.services.places.PlaceService;
import com.je.services.shoppings.QuarterMetal;
import com.je.services.shoppings.Shopping;
import com.je.services.shoppings.ShoppingService;
import com.je.services.tracks.TrackService;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;
import com.je.utils.string.Util;
import com.je.validators.SearchFormValidator;

/**
 * The Class ShoppingsAdminController.
 */
@Controller
public class ShoppingsAdminController {

	/** The shopping service. */
	@Autowired
	private ShoppingService shoppingService;

	/** The place service. */
	@Autowired
	private PlaceService placeService;

	/** The place service. */
	@Autowired
	private MetalService materialService;

	@Autowired
	private NationService nationservice;

	@Autowired
	private TrackService trackservice;

	/** The shopping form validator. */
	@Autowired
	private ShoppingFormValidator shoppingFormValidator;

	@Autowired
	private NewShoppingFormValidator newshoppingFormValidator;

	@Autowired
	private SearchFormValidator adminSearchValidator;

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
	@RequestMapping(value = "/searchShoppings")
	public ModelAndView searchShoppings() {
		ModelAndView model = new ModelAndView(VIEWSEARCHSHOPPINGS);
		model.addObject(ConstantsJsp.SHOPPINGFORM, new ShoppingSearch());
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		return model;
	}

	/**
	 * Result shoppings.
	 *
	 * @param shopping the shopping
	 * @param result   the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/resultShoppings")
	public ModelAndView resultShoppings(@ModelAttribute(ConstantsJsp.SHOPPINGFORM) ShoppingSearch shopping,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		shoppingFormValidator.validate(shopping, result);
		if (result.hasErrors()) {
			model.addObject("shopping", shopping);
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
			model.setViewName(VIEWSEARCHSHOPPINGS);
		} else {
			List<Shopping> shoppings = shoppingService.searchShoppings(shopping.getDatefrom(), shopping.getDateuntil(),
					shopping.getPlace(), shopping.getNumshop());
			model.addObject("shoppings", shoppings);
			model.addObject(ConstantsJsp.SHOPPINGFORM, new Shopping());
			model.setViewName("admin/shoppings/searchshoppings/resultshoppings");
		}
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 *
	 * @param shopping the shopping
	 * @return the model and view
	 */
	@RequestMapping(value = "/updateShoppings")
	public ModelAndView updateShoppings(@ModelAttribute(ConstantsJsp.SHOPPINGFORM) Shopping shopping) {
		ModelAndView model = new ModelAndView();
		Long idshop = shopping.getId();
		if (idshop == null) {
			model.addObject(ConstantsJsp.SHOPPINGFORM, new ShoppingSearch());
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
			model.setViewName(VIEWSEARCHSHOPPINGS);
		} else {
			shopping = shoppingService.findShopByPK(idshop);
			model.addObject(ConstantsJsp.SHOPPINGFORM, shopping);
			model.setViewName("admin/shoppings/searchshoppings/updateshopping");
			model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
		}
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	@RequestMapping(value = "/updateShopping{id}")
	public ModelAndView updateShopping(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("admin/shoppings/searchshoppings/updateshopping");
		Shopping shopping = shoppingService.findShopByPK(id);
		model.addObject(ConstantsJsp.SHOPPINGFORM, shopping);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
		return model;
	}

	/**
	 * Save shopping.
	 *
	 * @param shoppingForm the shopping form
	 * @param result       the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/saveShopping")
	public ModelAndView saveShopping(@ModelAttribute(ConstantsJsp.SHOPPINGFORM) Shopping shoppingForm) {
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		shoppingForm.setUser(user);
		shoppingService.update(shoppingForm);
		return searchShoppings();
	}

	/**
	 * Save shopping.
	 *
	 * @param shoppingForm the shopping form
	 * @param result       the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/saveshop")
	public ModelAndView saveShop(@ModelAttribute(FORMSHOP) Shopping shoppingForm, BindingResult result) {
		ModelAndView model;
		newshoppingFormValidator.validate(shoppingForm, result);
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
			model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
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

	@RequestMapping(value = "/searchquartermaterial")
	public ModelAndView searchQuarterMetal() {
		ModelAndView model = new ModelAndView("admin/shoppings/quartersmetal/searchquarter");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.SHOPPINGFORM, new SearchForm());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		return model;
	}

	@RequestMapping(value = "/quartermaterial")
	public ModelAndView quarterMetal(@ModelAttribute(ConstantsJsp.SHOPPINGFORM) SearchForm shopping,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		adminSearchValidator.validate(shopping, result);
		if (result.hasErrors()) {
			model.setViewName("admin/shoppings/quartersmetal/searchquarter");
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		} else {
			List<QuarterMetal> quarters = shoppingService.searchGramsByMetal(shopping.getDatefrom(),
					shopping.getDateuntil(), shopping.getPlace());
			model.addObject("quarters", quarters);
			model.setViewName("quartermaterial");
			shopping.setPlace(placeService.getPlace(shopping.getPlace().getIdplace()));
		}
		model.addObject(ConstantsJsp.SHOPPINGFORM, shopping);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	@RequestMapping(value = "/searchgramsnull")
	public ModelAndView searchGramsNull() {
		ModelAndView model = new ModelAndView("admin/shoppings/searchshoppings/searchgramsnull");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchForm());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		return model;
	}

	@RequestMapping(value = "/resultgramsnull")
	public ModelAndView resultGramsNull(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchForm form,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		adminSearchValidator.validate(form, result);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		if (result.hasErrors()) {
			model.setViewName("admin/shoppings/searchshoppings/searchgramsnull");
			model.addObject(ConstantsJsp.FORMSEARCH, form);
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		} else {
			List<Long> numshops = shoppingService.searchGramsNull(form.getDatefrom(), form.getDateuntil(),
					form.getPlace());
			model.addObject("nummissing", numshops);
			model.setViewName(ConstantsJsp.VIEWSEARCHMISSINGSHOPPINGS);
		}
		return model;
	}

	@RequestMapping(value = "/tomelloso")
	public ModelAndView searchClient() {
		ModelAndView model = new ModelAndView(VIEWSEARCHCLIENTADMIN);
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		Shopping pawn = new Shopping();
		pawn.setNumshop(shoppingService.getNextNumber(user));
		pawn.setUser(user);
		model.addObject(FORMSHOP, pawn);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * New pawn.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/newshop")
	public ModelAndView newshop(@ModelAttribute(FORMSHOP) Shopping pawn, BindingResult errors) {
		String dni = pawn.getNif();
		ModelAndView model = new ModelAndView();
		if (dni != null && dni.length() > 12) {
			errors.rejectValue(ConstantsJsp.NIF, "niftoolong");
			model.setViewName(VIEWSEARCHCLIENTADMIN);
		} else if (!Util.isNifNie(dni)) {
			errors.rejectValue(ConstantsJsp.NIF, "nifnotvalid");
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
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	@RequestMapping(value = "/exceltomelloso")
	public ModelAndView excelTomelloso() {
		ModelAndView model = new ModelAndView("admin/shoppings/exceltomelloso");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchForm());
		return model;
	}

	@RequestMapping(value = "/downloadexcel")
	public ModelAndView downloadexcel(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchForm form, BindingResult result,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("admin/shoppings/exceltomelloso");
		adminSearchValidator.validate(form, result);
		if (result.hasErrors()) {
			model.addObject(ConstantsJsp.FORMSEARCH, new SearchForm());
		} else {
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
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}
}
