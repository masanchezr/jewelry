package com.je.employee.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.employee.validators.PartialCancelSaleValidator;
import com.je.employee.validators.RemoveSaleFormValidator;
import com.je.forms.Sale;
import com.je.services.categories.CategoriesService;
import com.je.services.dailies.DailyService;
import com.je.services.jewels.JewelService;
import com.je.services.metal.MetalService;
import com.je.services.payment.PaymentService;
import com.je.services.places.PlaceService;
import com.je.services.sales.SaleService;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;
import com.je.validators.SaleFormValidator;

/**
 * The Class SalesController.
 */
@Controller
public class SalesController {

	/** The sale service. */
	@Autowired
	private SaleService saleService;

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
	private PlaceService placeService;

	@Autowired
	private DailyService dailyService;

	/** The sale form validator. */
	@Autowired
	private SaleFormValidator saleValidator;

	/** The remove sale validator. */
	@Autowired
	private RemoveSaleFormValidator removeSaleValidator;

	@Autowired
	private PartialCancelSaleValidator partialCancelSaleValidator;

	private static final String VIEWNEWSALE = "employee/sales/newsale";
	private static final String VIEWREMOVEPARCIALSALE = "employee/sales/removeparcialsale";
	private static final String VIEWREMOVESALE = "employee/sales/removesale";
	private static final String FORMREMOVESALE = "removeSaleForm";
	private static final String VIEWCANCELPARCIALSALE = "employee/sales/cancelparcialsale";

	/**
	 * Sale.
	 *
	 * @param sale   the sale form
	 * @param result the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/resultsale")
	public ModelAndView sale(@ModelAttribute(ConstantsJsp.FORMSALE) Sale sale, BindingResult result) {
		ModelAndView model = new ModelAndView();
		saleValidator.validate(sale, result);
		if (!result.hasErrors()) {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			List<JewelEntity> jewels = sale.getJewels();
			PlaceEntity place = placeService.getPlaceUser(user);
			List<JewelEntity> newjewels = jewelService.searchJewels(jewels, place);
			if (!jewels.isEmpty() && newjewels != null) {
				sale.setJewels(newjewels);
				sale.setPlace(place);
				// comprobamos si ya existe la venta
				boolean exists = saleService.exists(sale.getNumsale());
				if (!exists) {
					saleService.buy(sale);
					model.setViewName("employee/sales/finishsale");
					model.addObject(ConstantsJsp.FORMSALE, sale);
				} else {
					model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
					model.addObject(ConstantsJsp.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
					model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
					result.rejectValue(ConstantsJsp.NUMSALE, ConstantsJsp.ERRORNUMSALEREPEATED);
					model.addObject(ConstantsJsp.FORMSALE, sale);
					model.setViewName(VIEWNEWSALE);
				}
			} else {
				model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
				model.addObject(ConstantsJsp.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
				model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
				result.rejectValue(ConstantsJsp.NUMSALE, "jewelnoexist");
				model.addObject(ConstantsJsp.FORMSALE, sale);
				model.setViewName(VIEWNEWSALE);
			}
		} else {
			model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
			model.addObject(ConstantsJsp.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
			model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsJsp.FORMSALE, sale);
			model.setViewName(VIEWNEWSALE);
		}
		return model;
	}

	/**
	 * Removes the parcial sale.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/removeparcialsale")
	public ModelAndView removeParcialSale() {
		ModelAndView model = new ModelAndView(VIEWREMOVEPARCIALSALE);
		model.addObject(ConstantsJsp.FORMSALE, new Sale());
		return model;
	}

	/**
	 * Cancel parcial sale.
	 *
	 * @param sale   the sale form
	 * @param result the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/cancelparcialsale")
	public ModelAndView cancelParcialSale(@ModelAttribute(ConstantsJsp.FORMSALE) Sale sale, HttpServletRequest request,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		Long numsale = sale.getNumsale();
		if (numsale == null || numsale.equals(0L)) {
			model.setViewName(VIEWREMOVEPARCIALSALE);
			model.addObject(ConstantsJsp.FORMSALE, sale);
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			PlaceEntity place = placeService.getPlaceUser(user);
			Sale entitysale = saleService.searchByNumsaleAndPlace(sale.getNumsale(), place.getIdplace());
			if (entitysale != null) {
				model.setViewName(VIEWCANCELPARCIALSALE);
				model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
				model.addObject(ConstantsJsp.FORMSALE, entitysale);
			} else {
				model.setViewName(VIEWREMOVEPARCIALSALE);
				model.addObject(ConstantsJsp.FORMSALE, sale);
				result.rejectValue(ConstantsJsp.NUMSALE, ConstantsJsp.ERRORSALENOTEXIST);
			}
		}
		return model;
	}

	@RequestMapping(value = "/employee/savecancelparcial")
	public ModelAndView savecancelparcial(@ModelAttribute(ConstantsJsp.FORMSALE) Sale sale, BindingResult result) {
		ModelAndView model = new ModelAndView();
		partialCancelSaleValidator.validate(sale, result);
		if (result.hasErrors()) {
			Sale entitysale = saleService.searchByPK(sale.getIdsale());
			model.setViewName(VIEWCANCELPARCIALSALE);
			model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsJsp.FORMSALE, entitysale);
		} else {
			if (saleService.removeSaleParcial(sale)) {
				model.setViewName("successemployee");
			} else {
				Sale entitysale = saleService.searchByPK(sale.getIdsale());
				model.setViewName(VIEWCANCELPARCIALSALE);
				model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
				model.addObject(ConstantsJsp.FORMSALE, entitysale);
				result.rejectValue(ConstantsJsp.IDSALE, "notcancelparcial");
			}
		}
		return model;
	}

	/**
	 * Removes the sale.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/removesale")
	public ModelAndView removeSale() {
		ModelAndView model = new ModelAndView(VIEWREMOVESALE);
		List<PaymentEntity> payments = paymentService.findAllActive();
		PaymentEntity pve = new PaymentEntity();
		PaymentEntity psame = new PaymentEntity();
		psame.setIdpayment(ConstantsJsp.SAME);
		psame.setName("El mismo que se realizó en la compra");
		pve.setIdpayment(ConstantsJsp.DISCOUNTANDCASH);
		pve.setName("Vale descuento y efectivo");
		payments.add(psame);
		payments.add(pve);
		model.addObject(FORMREMOVESALE, new Sale());
		model.addObject(ConstantsJsp.PAYMENTS, payments);
		return model;
	}

	/**
	 * Delete sale.
	 *
	 * @param removeSaleForm the remove sale form
	 * @param result         the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/deletesale")
	public ModelAndView deleteSale(Sale removeSaleForm, HttpServletRequest request, BindingResult result) {
		ModelAndView model = new ModelAndView();
		removeSaleValidator.validate(removeSaleForm, result);
		if (result.hasErrors()) {
			model.addObject(FORMREMOVESALE, new Sale());
			model.setViewName(VIEWREMOVESALE);
			model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
		} else {
			// ahora hay que mirar si existe la venta para ese lugar
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			PlaceEntity place = placeService.getPlaceUser(user);
			Sale searchSale = saleService.searchByNumsaleAndPlace(removeSaleForm.getNumsale(), place.getIdplace());
			if (searchSale == null) {
				model.addObject(FORMREMOVESALE, new Sale());
				model.setViewName(VIEWREMOVESALE);
				model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
				result.rejectValue(ConstantsJsp.IDSALE, "notfoundsale");
			} else {
				String ipAddress = request.getHeader(ConstantsJsp.XFORWARDEDFOR);
				Date today = new Date();
				if (ipAddress == null) {
					ipAddress = request.getRemoteAddr();
				}
				model.setViewName(ConstantsJsp.VIEWDAILYARROW);
				searchSale.setPlace(place);
				searchSale.setIddiscount(removeSaleForm.getIddiscount());
				searchSale.setPayment(removeSaleForm.getPayment());
				saleService.removeSale(searchSale);
				model.addObject(ConstantsJsp.DAILY, dailyService.getDaily(today, place, ipAddress));
				model.addObject(ConstantsJsp.DATEDAILY, today);
			}
		}
		return model;
	}

	/**
	 * New sale.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/newsale")
	public ModelAndView newSale() {
		ModelAndView model = new ModelAndView(VIEWNEWSALE);
		Sale sale = new Sale();
		List<JewelEntity> jewels = new ArrayList<>();
		for (int i = 0; i < Constants.MAXJEWELS; i++) {
			jewels.add(new JewelEntity());
		}
		sale.setJewels(jewels);
		model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
		model.addObject(ConstantsJsp.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
		model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
		model.addObject(ConstantsJsp.FORMSALE, sale);
		return model;
	}
}
