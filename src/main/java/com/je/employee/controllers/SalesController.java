package com.je.employee.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.je.services.salesrepeated.SearchSaleRepeatedService;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsViews;
import com.je.utils.date.DateUtil;
import com.je.validators.SaleFormValidator;

/**
 * The Class SalesController.
 */
@Controller
public class SalesController {

	/** The categories service. */
	@Autowired
	private CategoriesService categoriesService;

	@Autowired
	private DailyService dailyService;

	/** The jewel service. */
	@Autowired
	private JewelService jewelService;

	/** The material service. */
	@Autowired
	private MetalService materialService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private PlaceService placeService;

	/** The sale service. */
	@Autowired
	private SaleService saleService;

	@Autowired
	private SearchSaleRepeatedService searchSaleRepeatedService;

	@Autowired
	private PartialCancelSaleValidator partialCancelSaleValidator;

	/** The remove sale validator. */
	@Autowired
	private RemoveSaleFormValidator removeSaleValidator;

	/** The sale form validator. */
	@Autowired
	private SaleFormValidator saleValidator;

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
	@PostMapping("/employee/resultsale")
	public ModelAndView sale(@ModelAttribute(ConstantsViews.FORMSALE) Sale sale, BindingResult result) {
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
				if (!searchSaleRepeatedService.isSaleRepeated(sale.getNumsale(), DateUtil.getYear(new Date()))) {
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
	public ModelAndView cancelParcialSale(@ModelAttribute(ConstantsViews.FORMSALE) Sale sale,
			HttpServletRequest request, BindingResult result) {
		ModelAndView model = new ModelAndView();
		Long numsale = sale.getNumsale();
		if (numsale == null || numsale.equals(0L)) {
			model.setViewName(VIEWREMOVEPARCIALSALE);
			model.addObject(ConstantsViews.FORMSALE, sale);
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			PlaceEntity place = placeService.getPlaceUser(user);
			Sale entitysale = saleService.searchByNumsaleAndPlace(sale.getNumsale(), place.getIdplace());
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
	public ModelAndView deleteSale(Sale removeSaleForm, HttpServletRequest request, BindingResult result) {
		ModelAndView model = new ModelAndView();
		removeSaleValidator.validate(removeSaleForm, result);
		if (result.hasErrors()) {
			model.addObject(FORMREMOVESALE, new Sale());
			model.setViewName(VIEWREMOVESALE);
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
		} else {
			// ahora hay que mirar si existe la venta para ese lugar
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			PlaceEntity place = placeService.getPlaceUser(user);
			Sale searchSale = saleService.searchByNumsaleAndPlace(removeSaleForm.getNumsale(), place.getIdplace());
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
				model.addObject(ConstantsViews.DATEDAILY, DateUtil.getStringDateFormatddMMyyyy(today));
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
