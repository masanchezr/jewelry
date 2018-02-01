package com.je.employee.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import com.je.services.categories.CategoriesService;
import com.je.services.dailies.DailyService;
import com.je.services.jewels.JewelService;
import com.je.services.material.MetalService;
import com.je.services.payment.PaymentService;
import com.je.services.places.PlaceService;
import com.je.services.sales.Sale;
import com.je.services.sales.SaleService;
import com.je.utils.constants.Constants;
import com.je.utils.string.Util;
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
	private SaleFormValidator saleFormValidator;

	/** The remove sale validator. */
	@Autowired
	private RemoveSaleFormValidator removeSaleValidator;

	@Autowired
	private PartialCancelSaleValidator partialCancelSaleValidator;

	/**
	 * Sale.
	 *
	 * @param saleForm
	 *            the sale form
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/resultsale")
	public ModelAndView sale(@ModelAttribute("saleForm") Sale saleForm, BindingResult result) {
		ModelAndView model = new ModelAndView();
		saleFormValidator.validate(saleForm, result);
		if (!result.hasErrors()) {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			List<JewelEntity> jewels = saleForm.getJewels();
			List<JewelEntity> newjewels = new ArrayList<JewelEntity>();
			JewelEntity jewel;
			boolean exists = true;
			PlaceEntity place = placeService.getPlaceUser(user);
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
					model.setViewName("finishsale");
					model.addObject("sale", saleForm);
				} else {
					model.addObject("materials", materialService.getAllMetals());
					model.addObject("categories", categoriesService.getAllCategoriesOrderByName());
					model.addObject("payments", paymentService.findAllActive());
					result.rejectValue("idsale", "numsalerepeated");
					model.addObject("saleForm", saleForm);
					model.setViewName("newsale");
				}
			} else {
				model.addObject("materials", materialService.getAllMetals());
				model.addObject("categories", categoriesService.getAllCategoriesOrderByName());
				model.addObject("payments", paymentService.findAllActive());
				result.rejectValue("numsale", "jewelnoexist");
				model.addObject("saleForm", saleForm);
				model.setViewName("newsale");
			}
		} else {
			model.addObject("materials", materialService.getAllMetals());
			model.addObject("categories", categoriesService.getAllCategoriesOrderByName());
			model.addObject("payments", paymentService.findAllActive());
			model.addObject("saleForm", saleForm);
			model.setViewName("newsale");
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
		ModelAndView model = new ModelAndView("removeparcialsale");
		model.addObject("saleForm", new Sale());
		return model;
	}

	/**
	 * Cancel parcial sale.
	 *
	 * @param saleForm
	 *            the sale form
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/cancelparcialsale")
	public ModelAndView cancelParcialSale(@ModelAttribute("saleForm") Sale saleForm, HttpServletRequest request,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		Long numsale = saleForm.getNumsale();
		if (numsale == null || numsale.equals(0L)) {
			model.setViewName("removeparcialsale");
			model.addObject("saleForm", saleForm);
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			PlaceEntity place = placeService.getPlaceUser(user);
			Sale sale = saleService.searchByNumsaleAndPlace(saleForm.getNumsale(), place.getIdplace());
			if (sale != null) {
				model.setViewName("cancelparcialsale");
				model.addObject("payments", paymentService.findAllActive());
				model.addObject("saleForm", sale);
			} else {
				model.setViewName("removeparcialsale");
				model.addObject("saleForm", saleForm);
				result.rejectValue("numsale", "salenotexit");
			}
		}
		return model;
	}

	@RequestMapping(value = "/employee/savecancelparcial")
	public ModelAndView savecancelparcial(@ModelAttribute("saleForm") Sale saleForm, BindingResult result) {
		ModelAndView model = new ModelAndView();
		partialCancelSaleValidator.validate(saleForm, result);
		if (result.hasErrors()) {
			Sale sale = saleService.searchByPK(saleForm.getIdsale());
			model.setViewName("cancelparcialsale");
			model.addObject("payments", paymentService.findAllActive());
			model.addObject("saleForm", sale);
		} else {
			if (saleService.removeSaleParcial(saleForm)) {
				model.setViewName("successemployee");
			} else {
				Sale sale = saleService.searchByPK(saleForm.getIdsale());
				model.setViewName("cancelparcialsale");
				model.addObject("payments", paymentService.findAllActive());
				model.addObject("saleForm", sale);
				result.rejectValue("idsale", "notcancelparcial");
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
		ModelAndView model = new ModelAndView("removesale");
		List<PaymentEntity> payments = paymentService.findAllActive();
		PaymentEntity pve = new PaymentEntity();
		pve.setIdpayment(6L);
		pve.setName("Vale descuento y efectivo");
		payments.add(pve);
		model.addObject("removeSaleForm", new Sale());
		model.addObject("payments", payments);
		return model;
	}

	/**
	 * Delete sale.
	 *
	 * @param removeSaleForm
	 *            the remove sale form
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/deletesale")
	public ModelAndView deleteSale(Sale removeSaleForm, HttpServletRequest request, BindingResult result) {
		ModelAndView model = new ModelAndView();
		removeSaleValidator.validate(removeSaleForm, result);
		if (result.hasErrors()) {
			model.addObject("removeSaleForm", new Sale());
			model.setViewName("removesale");
			model.addObject("payments", paymentService.findAllActive());
		} else {
			// ahora hay que mirar si existe la venta para ese lugar
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			PlaceEntity place = placeService.getPlaceUser(user);
			Sale searchSale = saleService.searchByNumsaleAndPlace(removeSaleForm.getNumsale(), place.getIdplace());
			if (searchSale == null) {
				model.addObject("removeSaleForm", new Sale());
				model.setViewName("removesale");
				model.addObject("payments", paymentService.findAllActive());
				result.rejectValue("idsale", "notfoundsale");
			} else {
				String ipAddress = request.getHeader("X-FORWARDED-FOR");
				Date today = new Date();
				if (ipAddress == null) {
					ipAddress = request.getRemoteAddr();
				}
				model.setViewName("dailyarrow");
				searchSale.setPlace(place);
				searchSale.setIddiscount(removeSaleForm.getIddiscount());
				searchSale.setPayment(removeSaleForm.getPayment());
				saleService.removeSale(searchSale);
				model.addObject("daily", dailyService.getDaily(today, place, ipAddress));
				model.addObject("datedaily", today);
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
		ModelAndView model = new ModelAndView("newsale");
		Sale sale = new Sale();
		List<JewelEntity> jewels = new ArrayList<JewelEntity>();
		for (int i = 0; i < Constants.MAXJEWELS; i++) {
			jewels.add(new JewelEntity());
		}
		sale.setJewels(jewels);
		model.addObject("materials", materialService.getAllMetals());
		model.addObject("categories", categoriesService.getAllCategoriesOrderByName());
		model.addObject("payments", paymentService.findAllActive());
		model.addObject("saleForm", sale);
		return model;
	}
}
