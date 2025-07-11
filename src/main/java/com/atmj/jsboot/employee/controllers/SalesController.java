package com.atmj.jsboot.employee.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.dbaccess.entities.JewelEntity;
import com.atmj.jsboot.dbaccess.entities.PaymentEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.employee.validators.PartialCancelSaleValidator;
import com.atmj.jsboot.employee.validators.RemoveSaleFormValidator;
import com.atmj.jsboot.forms.Sale;
import com.atmj.jsboot.services.categories.CategoriesService;
import com.atmj.jsboot.services.dailies.DailyService;
import com.atmj.jsboot.services.jewels.JewelService;
import com.atmj.jsboot.services.metal.MetalService;
import com.atmj.jsboot.services.payment.PaymentService;
import com.atmj.jsboot.services.places.PlaceService;
import com.atmj.jsboot.services.sales.SaleService;
import com.atmj.jsboot.services.salesrepeated.SearchSaleRepeatedService;
import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.date.DateUtil;
import com.atmj.jsboot.validators.SaleFormValidator;

import jakarta.servlet.http.HttpServletRequest;

/**
 * The Class SalesController.
 */
@Controller
public class SalesController {

	/** The categories service. */
	private CategoriesService categoriesService;
	private DailyService dailyService;

	/** The jewel service. */
	private JewelService jewelService;

	/** The material service. */
	private MetalService materialService;

	private PaymentService paymentService;

	private PlaceService placeService;

	/** The sale service. */
	private SaleService saleService;

	private SearchSaleRepeatedService searchSaleRepeatedService;

	private SaleFormValidator saleFormValidator;

	private PartialCancelSaleValidator partialCancelSaleValidator;

	private RemoveSaleFormValidator removeSaleFormValidator;

	private static final String VIEWNEWSALE = "employee/sales/newsale";
	private static final String VIEWREMOVEPARCIALSALE = "employee/sales/removeparcialsale";
	private static final String VIEWREMOVESALE = "employee/sales/removesale";
	private static final String FORMREMOVESALE = "removeSaleForm";
	private static final String VIEWCANCELPARCIALSALE = "employee/sales/cancelparcialsale";

	public SalesController(CategoriesService categoriesService, DailyService dailyService, JewelService jewelService,
			MetalService materialService, PaymentService paymentService, PlaceService placeService,
			SaleService saleService, SearchSaleRepeatedService searchSaleRepeatedService,
			SaleFormValidator saleFormValidator, PartialCancelSaleValidator partialCancelSaleValidator,
			RemoveSaleFormValidator removeSaleFormValidator) {
		this.categoriesService = categoriesService;
		this.dailyService = dailyService;
		this.jewelService = jewelService;
		this.materialService = materialService;
		this.paymentService = paymentService;
		this.placeService = placeService;
		this.saleService = saleService;
		this.searchSaleRepeatedService = searchSaleRepeatedService;
		this.saleFormValidator = saleFormValidator;
		this.partialCancelSaleValidator = partialCancelSaleValidator;
		this.removeSaleFormValidator = removeSaleFormValidator;
	}

	/**
	 * Sale.
	 *
	 * @param sale   the sale form
	 * @param result the result
	 * @return the model and view
	 */
	@PostMapping("/employee/resultsale")
	public ModelAndView sale(@ModelAttribute(ConstantsViews.FORMSALE) Sale sale, BindingResult result) {
		ModelAndView model = new ModelAndView();
		saleFormValidator.validate(sale, result);
		if (!result.hasErrors()) {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			List<JewelEntity> jewels = sale.getJewels();
			PlaceEntity place = placeService.getPlaceUser(user);
			List<JewelEntity> newjewels = jewelService.searchJewels(jewels, place);
			if (!jewels.isEmpty() && newjewels != null) {
				sale.setJewels(newjewels);
				sale.setPlace(place);
				// comprobamos si ya existe la venta
				if (searchSaleRepeatedService.isNotRepeatSale(sale.getNumsale(), DateUtil.getYear(new Date()))) {
					saleService.buy(sale);
					model.setViewName("employee/sales/finishsale");
					model.addObject(ConstantsViews.FORMSALE, sale);
				} else {
					model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
					model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
					model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
					result.rejectValue(ConstantsViews.NUMSALE, ConstantsViews.ERRORNUMSALEREPEATED);
					model.addObject(ConstantsViews.FORMSALE, sale);
					model.setViewName(VIEWNEWSALE);
				}
			} else {
				model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
				model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
				model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
				result.rejectValue(ConstantsViews.NUMSALE, "jewelnoexist");
				model.addObject(ConstantsViews.FORMSALE, sale);
				model.setViewName(VIEWNEWSALE);
			}
		} else {
			model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
			model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsViews.FORMSALE, sale);
			model.setViewName(VIEWNEWSALE);
		}
		return model;
	}

	/**
	 * Removes the parcial sale.
	 *
	 * @return the model and view
	 */
	@GetMapping("/employee/removeparcialsale")
	public ModelAndView removeParcialSale() {
		ModelAndView model = new ModelAndView(VIEWREMOVEPARCIALSALE);
		model.addObject(ConstantsViews.FORMSALE, new Sale());
		return model;
	}

	/**
	 * Cancel parcial sale.
	 *
	 * @param sale   the sale form
	 * @param result the result
	 * @return the model and view
	 */
	@PostMapping("/employee/cancelparcialsale")
	public ModelAndView cancelParcialSale(@ModelAttribute(ConstantsViews.FORMSALE) Sale sale, BindingResult result) {
		ModelAndView model = new ModelAndView();
		Long numsale = sale.getNumsale();
		if (numsale == null || numsale.equals(0L)) {
			model.setViewName(VIEWREMOVEPARCIALSALE);
			model.addObject(ConstantsViews.FORMSALE, sale);
		} else {
			Sale entitysale = saleService.searchByNumsaleAndYear(sale.getNumsale(), DateUtil.getYear(new Date()));
			if (entitysale != null) {
				entitysale.setJewelstocancel(new ArrayList<>());
				model.setViewName(VIEWCANCELPARCIALSALE);
				model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
				model.addObject(ConstantsViews.FORMSALE, entitysale);
			} else {
				model.setViewName(VIEWREMOVEPARCIALSALE);
				model.addObject(ConstantsViews.FORMSALE, sale);
				result.rejectValue(ConstantsViews.NUMSALE, ConstantsViews.ERRORSALENOTEXIST);
			}
		}
		return model;
	}

	@PostMapping("/employee/savecancelparcial")
	public ModelAndView savecancelparcial(@ModelAttribute(ConstantsViews.FORMSALE) Sale sale, BindingResult result) {
		ModelAndView model = new ModelAndView();
		partialCancelSaleValidator.validate(sale, result);
		if (result.hasErrors()) {
			Sale entitysale = saleService.searchByPK(sale.getIdsale());
			model.setViewName(VIEWCANCELPARCIALSALE);
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsViews.FORMSALE, entitysale);
		} else {
			if (saleService.removeSaleParcial(sale)) {
				model.setViewName("employee/success");
			} else {
				Sale entitysale = saleService.searchByPK(sale.getIdsale());
				model.setViewName(VIEWCANCELPARCIALSALE);
				model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
				model.addObject(ConstantsViews.FORMSALE, entitysale);
				result.rejectValue(ConstantsViews.IDSALE, "notcancelparcial");
			}
		}
		return model;
	}

	/**
	 * Removes the sale.
	 *
	 * @return the model and view
	 */
	@GetMapping("/employee/removesale")
	public ModelAndView removeSale() {
		ModelAndView model = new ModelAndView(VIEWREMOVESALE);
		List<PaymentEntity> payments = paymentService.findAllActive();
		PaymentEntity pve = new PaymentEntity();
		PaymentEntity psame = new PaymentEntity();
		psame.setIdpayment(ConstantsViews.SAME);
		psame.setName("El mismo que se realizó en la compra");
		pve.setIdpayment(ConstantsViews.DISCOUNTANDCASH);
		pve.setName("Vale descuento y efectivo");
		payments.add(psame);
		payments.add(pve);
		model.addObject(FORMREMOVESALE, new Sale());
		model.addObject(ConstantsViews.PAYMENTS, payments);
		return model;
	}

	/**
	 * Delete sale.
	 *
	 * @param removeSaleForm the remove sale form
	 * @param result         the result
	 * @return the model and view
	 */
	@PostMapping("/employee/deletesale")
	public ModelAndView deleteSale(Sale removeSaleForm, BindingResult result, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		removeSaleFormValidator.validate(removeSaleForm, result);
		if (result.hasErrors()) {
			model.addObject(FORMREMOVESALE, new Sale());
			model.setViewName(VIEWREMOVESALE);
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
		} else {
			// ahora hay que mirar si existe la venta para ese lugar
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			PlaceEntity place = placeService.getPlaceUser(user);
			Sale searchSale = saleService.searchByNumsaleAndYear(removeSaleForm.getNumsale(), removeSaleForm.getYear());
			if (searchSale == null) {
				model.addObject(FORMREMOVESALE, new Sale());
				model.setViewName(VIEWREMOVESALE);
				model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
				result.rejectValue(ConstantsViews.IDSALE, "notfoundsale");
			} else {
				String ipAddress = request.getHeader(ConstantsViews.XFORWARDEDFOR);
				Date today = DateUtil.getDateFormated(new Date());
				if (ipAddress == null) {
					ipAddress = request.getRemoteAddr();
				}
				model.setViewName(ConstantsViews.VIEWDAILYARROW);
				searchSale.setPlace(place);
				searchSale.setIddiscount(removeSaleForm.getIddiscount());
				searchSale.setPayment(removeSaleForm.getPayment());
				saleService.removeSale(searchSale);
				model.addObject(ConstantsViews.DAILY, dailyService.getDaily(today, place, ipAddress));
			}
		}
		return model;
	}

	/**
	 * New sale.
	 *
	 * @return the model and view
	 */
	@GetMapping("/employee/newsale")
	public ModelAndView newSale() {
		ModelAndView model = new ModelAndView(VIEWNEWSALE);
		Sale sale = new Sale();
		List<JewelEntity> jewels = new ArrayList<>();
		for (int i = 0; i < Constants.MAXJEWELS; i++) {
			jewels.add(new JewelEntity());
		}
		sale.setJewels(jewels);
		model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
		model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
		model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
		model.addObject(ConstantsViews.FORMSALE, sale);
		return model;
	}
}
