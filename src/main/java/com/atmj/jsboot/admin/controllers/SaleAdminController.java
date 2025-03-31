package com.atmj.jsboot.admin.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.admin.forms.AdminForm;
import com.atmj.jsboot.admin.forms.SearchMissingNumbers;
import com.atmj.jsboot.admin.validators.SearchMissingNumbersValidator;
import com.atmj.jsboot.admin.validators.SearchSalesValidator;
import com.atmj.jsboot.dbaccess.entities.JewelEntity;
import com.atmj.jsboot.dbaccess.entities.PaymentEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.forms.Sale;
import com.atmj.jsboot.forms.SearchForm;
import com.atmj.jsboot.services.categories.CategoriesService;
import com.atmj.jsboot.services.jewels.JewelService;
import com.atmj.jsboot.services.metal.MetalService;
import com.atmj.jsboot.services.payment.PaymentService;
import com.atmj.jsboot.services.places.PlaceService;
import com.atmj.jsboot.services.sales.SaleService;
import com.atmj.jsboot.services.sales.SearchSale;
import com.atmj.jsboot.services.salescard.SalesCardService;
import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.date.DateUtil;
import com.atmj.jsboot.utils.string.Util;
import com.atmj.jsboot.validators.SaleFormValidator;
import com.atmj.jsboot.validators.SearchFormValidator;

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
	private SearchMissingNumbersValidator searchMissingNumbersValidator;

	@Autowired
	private SearchFormValidator searchFormValidator;

	@Autowired
	private SaleFormValidator saleFormValidator;

	private static final String VIEWNEWSALEADMIN = "admin/sales/newsale";

	@PostMapping("/resultSalesCard")
	public ModelAndView resultSalesCard(@ModelAttribute("searchSaleForm") SearchSale searchSaleForm,
			BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
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

	@GetMapping("/searchSalesCard")
	public ModelAndView searchSalesCard() {
		ModelAndView model = new ModelAndView("admin/salescard/searchsalescard");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject("searchSaleForm", new SearchSale());
		return model;
	}

	@GetMapping("/searchSales")
	public ModelAndView searchsales() {
		ModelAndView model = new ModelAndView("admin/sales/searchsales/searchsales");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.FORMSEARCH, new SearchForm());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		return model;
	}

	@GetMapping("/searchNumMissing")
	public ModelAndView searchNumMissing() {
		ModelAndView model = new ModelAndView("searchnummissing");
		SearchMissingNumbers smn = new SearchMissingNumbers();
		smn.setNumfrom(49L);
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.FORMSEARCH, smn);
		return model;
	}

	@PostMapping("/resultNumMissing")
	public ModelAndView resultNumMissing(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchMissingNumbers searchForm,
			BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		searchMissingNumbersValidator.validate(searchForm, bindingResult);
		if (bindingResult.hasErrors()) {
			model.setViewName("searchnummissing");
			model.addObject(ConstantsViews.FORMSEARCH, searchForm);
		} else {
			model.addObject("nummissing", saleService.calculateMissingSales(searchForm));
			model.setViewName("resultnummissing");
		}
		return model;
	}

	@PostMapping("/resultSales")
	public ModelAndView resultsales(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchForm searchSaleForm,
			BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		searchFormValidator.validate(searchSaleForm, bindingResult);
		if (bindingResult.hasErrors()) {
			model.setViewName("admin/sales/searchsales/searchsales");
			model.addObject(ConstantsViews.FORMSEARCH, searchSaleForm);
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		} else {
			model.addAllObjects(saleService.searchByDatesAndPlace(searchSaleForm.getDatefrom(),
					searchSaleForm.getDateuntil(), searchSaleForm.getPlace()));
			model.setViewName("admin/sales/searchsales/resultsales");
		}
		return model;
	}

	@GetMapping("/showsale{id}")
	public ModelAndView showsale(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("admin/sales/finishsale");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		Sale sale = saleService.searchByPK(id);
		model.addObject(ConstantsViews.FORMSALE, sale);
		return model;
	}

	/**
	 * Sale.
	 *
	 * @param sale   the sale form
	 * @param result the result
	 * @return the model and view
	 */
	@PostMapping("/resultsale")
	public ModelAndView sale(@ModelAttribute(ConstantsViews.FORMSALE) Sale sale, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		saleFormValidator.validate(sale, result);
		if (!result.hasErrors()) {
			List<JewelEntity> jewels = sale.getJewels();
			PlaceEntity place = sale.getPlace();
			List<JewelEntity> newjewels = jewelService.searchJewels(jewels, place);
			if (!jewels.isEmpty() && existsJewels(jewels, place)) {
				sale.setJewels(newjewels);
				// comprobamos si ya existe la venta
				String sdate = sale.getSaledate();
				int year;
				if (sdate == null) {
					year = DateUtil.getYear(new Date());
				} else {
					year = DateUtil.getYear(sdate);
				}
				Sale entitySale = saleService.searchByNumsaleAndYear(sale.getNumsale(), year);
				if (entitySale == null) {
					saleService.buy(sale);
					model.setViewName("admin/sales/finishsale");
					model.addObject(ConstantsViews.FORMSALE, sale);
				} else {
					model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
					model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
					model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
					model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
					result.rejectValue(ConstantsViews.NUMSALE, ConstantsViews.ERRORNUMSALEREPEATED);
					model.addObject(ConstantsViews.FORMSALE, sale);
					model.setViewName(VIEWNEWSALEADMIN);
				}
			} else {
				model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
				model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
				model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
				model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
				result.rejectValue(ConstantsViews.NUMSALE, "jewelnoexist");
				model.addObject(ConstantsViews.FORMSALE, sale);
				model.setViewName(VIEWNEWSALEADMIN);
			}
		} else {
			model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
			model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
			model.addObject(ConstantsViews.FORMSALE, sale);
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
	@GetMapping("/newsale")
	public ModelAndView newSale() {
		ModelAndView model = new ModelAndView(VIEWNEWSALEADMIN);
		Sale sale = new Sale();
		List<JewelEntity> jewels = new ArrayList<>();
		for (int i = 0; i < Constants.MAXJEWELS; i++) {
			jewels.add(new JewelEntity());
		}
		sale.setJewels(jewels);
		model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
		model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
		model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.FORMSALE, sale);
		return model;
	}
}