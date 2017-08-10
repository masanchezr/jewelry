package com.je.admin.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.admin.validators.SearchMissingNumbersValidator;
import com.je.admin.validators.SearchSalesValidator;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.forms.SearchForm;
import com.je.services.categories.CategoriesService;
import com.je.services.jewels.JewelService;
import com.je.services.metal.MetalService;
import com.je.services.payment.PaymentService;
import com.je.services.places.PlaceService;
import com.je.services.sales.Sale;
import com.je.services.sales.SaleService;
import com.je.services.sales.SalesCardService;
import com.je.services.sales.SearchSale;
import com.je.services.searchmissingnumbers.SearchMissingNumberService;
import com.je.services.searchmissingnumbers.SearchMissingNumbers;
import com.je.utils.constants.Constants;
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
	private SearchMissingNumberService searchMissingNumberService;

	@Autowired
	private SalesCardService salesCardService;

	/** The jewel service. */
	@Autowired
	private JewelService jewelService;

	/** The metal service. */
	@Autowired
	private MetalService metalService;

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
	private SaleFormValidator saleFormValidator;

	@RequestMapping(value = "/resultSalesCard")
	public ModelAndView resultSalesCard(@ModelAttribute("searchSaleForm") SearchSale searchSaleForm,
			BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		model.addObject("adminForm", new AdminForm());
		searchSalesValidator.validate(searchSaleForm, bindingResult);
		if (bindingResult.hasErrors()) {
			model.setViewName("searchsalescard");
			model.addObject("searchSaleForm", searchSaleForm);
		} else {
			PaymentEntity pay = new PaymentEntity();
			pay.setIdpayment(Constants.CARD);
			searchSaleForm.setPay(pay);
			model.addAllObjects(salesCardService.searchByDates(searchSaleForm));
			model.setViewName("resultsalescard");
		}
		return model;
	}

	@RequestMapping(value = "/searchSalesCard")
	public ModelAndView searchSalesCard() {
		ModelAndView model = new ModelAndView("searchsalescard");
		model.addObject("adminForm", new AdminForm());
		model.addObject("searchSaleForm", new SearchSale());
		return model;
	}

	@RequestMapping(value = "/searchSales")
	public ModelAndView searchsales() {
		ModelAndView model = new ModelAndView("searchsales");
		model.addObject("adminForm", new AdminForm());
		model.addObject("searchForm", new SearchForm());
		model.addObject("places", placeService.getAllPlaces());
		return model;
	}

	@RequestMapping(value = "/searchNumMissing")
	public ModelAndView searchNumMissing() {
		ModelAndView model = new ModelAndView("searchnummissing");
		model.addObject("adminForm", new AdminForm());
		model.addObject("searchForm", new SearchMissingNumbers());
		model.addObject("places", placeService.getAllPlaces());
		return model;
	}

	@RequestMapping(value = "/resultNumMissing")
	public ModelAndView resultNumMissing(@ModelAttribute("searchForm") SearchMissingNumbers searchForm,
			BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		model.addObject("adminForm", new AdminForm());
		searchMissingNumbersValidator.validate(searchForm, bindingResult);
		if (bindingResult.hasErrors()) {
			model.setViewName("searchnummissing");
			model.addObject("searchForm", searchForm);
			model.addObject("places", placeService.getAllPlaces());
		} else {
			model.addObject("nummissing", searchMissingNumberService.calculateMissingSales(searchForm));
			model.setViewName("resultnummissing");
		}
		return model;
	}

	@RequestMapping(value = "/resultSales")
	public ModelAndView resultsales(@ModelAttribute("searchForm") SearchForm searchSaleForm,
			BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		model.addObject("adminForm", new AdminForm());
		adminsearchformvalidator.validate(searchSaleForm, bindingResult);
		if (bindingResult.hasErrors()) {
			model.setViewName("searchsales");
			model.addObject("searchForm", searchSaleForm);
			model.addObject("places", placeService.getAllPlaces());
		} else {
			model.addAllObjects(saleService.searchByDatesAndPlace(searchSaleForm.getDatefrom(),
					searchSaleForm.getDateuntil(), searchSaleForm.getPlace()));
			model.setViewName("resultsales");
		}
		return model;
	}

	@RequestMapping(value = "/showsale{id}")
	public ModelAndView showsale(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("showsale");
		model.addObject("adminForm", new AdminForm());
		Sale sale = saleService.searchByPK(id);
		model.addObject("sale", sale);
		return model;
	}

	/**
	 * Sale.
	 *
	 * @param saleForm
	 *            the sale form
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/resultsale")
	public ModelAndView sale(@ModelAttribute("saleForm") Sale saleForm, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject("adminForm", new AdminForm());
		saleFormValidator.validate(saleForm, result);
		if (!result.hasErrors()) {
			List<JewelEntity> jewels = saleForm.getJewels();
			List<JewelEntity> newjewels = new ArrayList<JewelEntity>();
			JewelEntity jewel;
			boolean exists = true;
			PlaceEntity place = saleForm.getPlace();
			Iterator<JewelEntity> ijewels = jewels.iterator();
			while (ijewels.hasNext() && exists) {
				jewel = ijewels.next();
				if (!Util.isEmpty(jewel.getReference())) {
					jewel.setPlace(place);
					jewel.setActive(true);
					jewel = jewelService.searchByReferenceCategoryMetalPlaceActive(jewel);
					if (jewel != null && jewel.getIdjewel() != null) {
						newjewels.add(jewel);
					} else {
						exists = false;
					}
				}
			}
			if (!jewels.isEmpty() && exists) {
				saleForm.setJewels(newjewels);
				saleForm.setPlace(place);
				// comprobamos si ya existe la venta
				Sale sale = saleService.searchByNumsaleAndPlace(saleForm.getNumsale(), place.getIdplace());
				if (sale == null) {
					saleService.buy(saleForm);
					model.setViewName("showsale");
					model.addObject("sale", saleForm);
				} else {
					model.addObject("metals", metalService.getAllMetals());
					model.addObject("categories", categoriesService.getAllCategoriesOrderByName());
					model.addObject("payments", paymentService.findAllActive());
					model.addObject("places", placeService.getAllPlaces());
					result.rejectValue("idsale", "numsalerepeated");
					model.addObject("saleForm", saleForm);
					model.setViewName("newsaleadmin");
				}
			} else {
				model.addObject("metals", metalService.getAllMetals());
				model.addObject("categories", categoriesService.getAllCategoriesOrderByName());
				model.addObject("payments", paymentService.findAllActive());
				model.addObject("places", placeService.getAllPlaces());
				result.rejectValue("numsale", "jewelnoexist");
				model.addObject("saleForm", saleForm);
				model.setViewName("newsaleadmin");
			}
		} else {
			model.addObject("metals", metalService.getAllMetals());
			model.addObject("categories", categoriesService.getAllCategoriesOrderByName());
			model.addObject("payments", paymentService.findAllActive());
			model.addObject("places", placeService.getAllPlaces());
			model.addObject("saleForm", saleForm);
			model.setViewName("newsaleadmin");
		}
		return model;
	}

	/**
	 * New sale.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/newsale")
	public ModelAndView newSale() {
		ModelAndView model = new ModelAndView("newsaleadmin");
		Sale sale = new Sale();
		List<JewelEntity> jewels = new ArrayList<JewelEntity>();
		for (int i = 0; i < Constants.MAXJEWELS; i++) {
			jewels.add(new JewelEntity());
		}
		sale.setJewels(jewels);
		model.addObject("metals", metalService.getAllMetals());
		model.addObject("categories", categoriesService.getAllCategoriesOrderByName());
		model.addObject("payments", paymentService.findAllActive());
		model.addObject("places", placeService.getAllPlaces());
		model.addObject("adminForm", new AdminForm());
		model.addObject("saleForm", sale);
		return model;
	}

	@RequestMapping(value = "/searchsalepostponed")
	public ModelAndView searchsalepostponed() {
		ModelAndView model = new ModelAndView("searchsalepostponed");
		model.addObject("places", placeService.getAllPlaces());
		model.addObject("adminForm", new AdminForm());
		model.addObject("searchForm", new SearchForm());
		return model;
	}

	@RequestMapping(value = "/salepostponed")
	public ModelAndView salepostponed(@ModelAttribute("searchForm") SearchForm searchForm) {
		ModelAndView model = new ModelAndView("salepostponed");
		model.addObject("adminForm", new AdminForm());
		model.addObject("numsale", searchMissingNumberService.getSalePostponedMissing(searchForm.getPlace()));
		return model;
	}

}
