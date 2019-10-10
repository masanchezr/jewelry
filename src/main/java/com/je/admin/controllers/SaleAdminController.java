package com.je.admin.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.admin.forms.SearchMissingNumbers;
import com.je.admin.validators.SearchMissingNumbersValidator;
import com.je.admin.validators.SearchSalesValidator;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.forms.Sale;
import com.je.forms.SearchForm;
import com.je.services.categories.CategoriesService;
import com.je.services.jewels.JewelService;
import com.je.services.metal.MetalService;
import com.je.services.payment.PaymentService;
import com.je.services.places.PlaceService;
import com.je.services.sales.SaleService;
import com.je.services.sales.SearchSale;
import com.je.services.salescard.SalesCardService;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;
import com.je.utils.string.Util;
import com.je.validators.SaleFormValidator;
import com.je.validators.SearchFormValidator;

@Controller
public class SaleAdminController {

	/** The sale service. */
	@Autowired
	private SaleService saleService;

	/** The place service. */
	@Autowired
	private PlaceService placeService;

	@Autowired
	private SalesCardService salesCardService;

	/** The jewel service. */
	@Autowired
	private JewelService jewelService;

	/** The material service. */
	@Autowired
	private MetalService materialService;

	/** The categories service. */
	@Autowired
	private CategoriesService categoriesService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private SearchSalesValidator searchSalesValidator;

	@Autowired
	private SearchFormValidator adminsearchformvalidator;

	@Autowired
	private SearchMissingNumbersValidator searchMissingNumbersValidator;

	/** The sale form validator. */
	@Autowired
	private SaleFormValidator saleValidator;

	private static final String VIEWNEWSALEADMIN = "admin/sales/newsale";

	@GetMapping(value = "/resultSalesCard")
	public ModelAndView resultSalesCard(@ModelAttribute("searchSaleForm") SearchSale searchSaleForm,
			BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		searchSalesValidator.validate(searchSaleForm, bindingResult);
		if (bindingResult.hasErrors()) {
			model.setViewName("admin/salescard/searchsalescard");
			model.addObject("searchSaleForm", searchSaleForm);
		} else {
			PaymentEntity pay = new PaymentEntity();
			pay.setIdpayment(Constants.CARD);
			searchSaleForm.setPay(pay);
			model.addAllObjects(salesCardService.searchByDates(searchSaleForm));
			model.setViewName("admin/salescard/resultsalescard");
		}
		return model;
	}

	@GetMapping(value = "/searchSalesCard")
	public ModelAndView searchSalesCard() {
		ModelAndView model = new ModelAndView("admin/salescard/searchsalescard");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject("searchSaleForm", new SearchSale());
		return model;
	}

	@GetMapping(value = "/searchSales")
	public ModelAndView searchsales() {
		ModelAndView model = new ModelAndView("admin/sales/searchsales/searchsales");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchForm());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		return model;
	}

	@GetMapping(value = "/searchNumMissing")
	public ModelAndView searchNumMissing() {
		ModelAndView model = new ModelAndView("searchnummissing");
		SearchMissingNumbers smn = new SearchMissingNumbers();
		smn.setNumfrom(49L);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.FORMSEARCH, smn);
		return model;
	}

	@GetMapping(value = "/resultNumMissing")
	public ModelAndView resultNumMissing(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchMissingNumbers searchForm,
			BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		searchMissingNumbersValidator.validate(searchForm, bindingResult);
		if (bindingResult.hasErrors()) {
			model.setViewName("searchnummissing");
			model.addObject(ConstantsJsp.FORMSEARCH, searchForm);
		} else {
			model.addObject("nummissing", saleService.calculateMissingSales(searchForm));
			model.setViewName("resultnummissing");
		}
		return model;
	}

	@GetMapping(value = "/resultSales")
	public ModelAndView resultsales(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchForm searchSaleForm,
			BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		adminsearchformvalidator.validate(searchSaleForm, bindingResult);
		if (bindingResult.hasErrors()) {
			model.setViewName("admin/sales/searchsales/searchsales");
			model.addObject(ConstantsJsp.FORMSEARCH, searchSaleForm);
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		} else {
			model.addAllObjects(saleService.searchByDatesAndPlace(searchSaleForm.getDatefrom(),
					searchSaleForm.getDateuntil(), searchSaleForm.getPlace()));
			model.setViewName("admin/sales/searchsales/resultsales");
		}
		return model;
	}

	@GetMapping(value = "/showsale{id}")
	public ModelAndView showsale(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("admin/sales/finishsale");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		Sale sale = saleService.searchByPK(id);
		model.addObject(ConstantsJsp.FORMSALE, sale);
		return model;
	}

	/**
	 * Sale.
	 *
	 * @param sale   the sale form
	 * @param result the result
	 * @return the model and view
	 */
	@GetMapping(value = "/resultsale")
	public ModelAndView sale(@ModelAttribute(ConstantsJsp.FORMSALE) Sale sale, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		saleValidator.validate(sale, result);
		if (!result.hasErrors()) {
			List<JewelEntity> jewels = sale.getJewels();
			PlaceEntity place = sale.getPlace();
			List<JewelEntity> newjewels = jewelService.searchJewels(jewels, place);
			if (!jewels.isEmpty() && existsJewels(jewels, place)) {
				sale.setJewels(newjewels);
				// comprobamos si ya existe la venta
				Sale entitySale = saleService.searchByNumsaleAndPlace(sale.getNumsale(), place.getIdplace());
				if (entitySale == null) {
					saleService.buy(sale);
					model.setViewName("admin/sales/searchsales/finishsale");
					model.addObject(ConstantsJsp.FORMSALE, sale);
				} else {
					model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
					model.addObject(ConstantsJsp.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
					model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
					model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
					result.rejectValue(ConstantsJsp.NUMSALE, ConstantsJsp.ERRORNUMSALEREPEATED);
					model.addObject(ConstantsJsp.FORMSALE, sale);
					model.setViewName(VIEWNEWSALEADMIN);
				}
			} else {
				model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
				model.addObject(ConstantsJsp.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
				model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
				model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
				result.rejectValue(ConstantsJsp.NUMSALE, "jewelnoexist");
				model.addObject(ConstantsJsp.FORMSALE, sale);
				model.setViewName(VIEWNEWSALEADMIN);
			}
		} else {
			model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
			model.addObject(ConstantsJsp.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
			model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
			model.addObject(ConstantsJsp.FORMSALE, sale);
			model.setViewName(VIEWNEWSALEADMIN);
		}
		return model;
	}

	private boolean existsJewels(List<JewelEntity> jewels, PlaceEntity place) {
		JewelEntity jewel;
		boolean exists = true;
		Iterator<JewelEntity> ijewels = jewels.iterator();
		while (ijewels.hasNext() && exists) {
			jewel = ijewels.next();
			if (!Util.isEmpty(jewel.getReference())) {
				jewel.setPlace(place);
				jewel.setActive(true);
				jewel = jewelService.searchByReferenceCategoryMetalPlaceActive(jewel);
				if (jewel == null || jewel.getIdjewel() == null) {
					exists = false;
				}
			}
		}
		return exists;
	}

	/**
	 * New sale.
	 *
	 * @return the model and view
	 */
	@GetMapping(value = "/newsale")
	public ModelAndView newSale() {
		ModelAndView model = new ModelAndView(VIEWNEWSALEADMIN);
		Sale sale = new Sale();
		List<JewelEntity> jewels = new ArrayList<>();
		for (int i = 0; i < Constants.MAXJEWELS; i++) {
			jewels.add(new JewelEntity());
		}
		sale.setJewels(jewels);
		model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
		model.addObject(ConstantsJsp.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
		model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.FORMSALE, sale);
		return model;
	}
}