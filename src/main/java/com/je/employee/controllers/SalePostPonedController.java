package com.je.employee.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.SalePostponedEntity;
import com.je.employee.validators.InstallmentValidator;
import com.je.employee.validators.SalePostPonedValidator;
import com.je.services.categories.CategoriesService;
import com.je.services.dailies.Daily;
import com.je.services.dailies.DailyService;
import com.je.services.jewels.JewelService;
import com.je.services.metal.MetalService;
import com.je.services.payment.PaymentService;
import com.je.services.places.PlaceService;
import com.je.services.sales.Installment;
import com.je.services.sales.SalePostPoned;
import com.je.services.sales.SalesPostPonedService;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;
import com.je.utils.string.Util;

@Controller
public class SalePostPonedController {

	@Autowired
	private SalesPostPonedService saleservicepostponed;

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

	@Autowired
	private SalePostPonedValidator salepostponedvalidator;

	@Autowired
	private InstallmentValidator installmentvalidator;

	private static final String VIEWNEWSALEPOSTPONED = "newsalepostponed";
	private static final String ADDINSTALLMENT = "addinstallment";
	private static final String INSTALLMENT = "installment";

	@RequestMapping(value = "/employee/newsalepostponed")
	public ModelAndView newSalepostponed() {
		ModelAndView model = new ModelAndView(VIEWNEWSALEPOSTPONED);
		SalePostPoned sale = new SalePostPoned();
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

	@RequestMapping(value = "/employee/savesalepostponed")
	public ModelAndView savesalepostponed(@ModelAttribute(ConstantsJsp.FORMSALE) SalePostPoned sale,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		salepostponedvalidator.validate(sale, result);
		if (!result.hasErrors()) {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			List<JewelEntity> jewels = sale.getJewels();
			List<JewelEntity> newjewels = new ArrayList<>();
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
				sale.setJewels(newjewels);
				sale.setPlace(place);
				// comprobamos si ya existe la venta
				SalePostponedEntity entitySale = saleservicepostponed.searchByNumsale(sale.getIdsale());
				if (entitySale == null) {
					saleservicepostponed.buy(sale);
					model.setViewName("finishsalepostponed");
					model.addObject(ConstantsJsp.FORMSALE, sale);
				} else {
					model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
					model.addObject(ConstantsJsp.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
					model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
					result.rejectValue(ConstantsJsp.IDSALE, ConstantsJsp.ERRORNUMSALEREPEATED);
					model.addObject(ConstantsJsp.FORMSALE, sale);
					model.setViewName(VIEWNEWSALEPOSTPONED);
				}
			} else {
				model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
				model.addObject(ConstantsJsp.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
				model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
				result.rejectValue(ConstantsJsp.IDSALE, "jewelnoexist");
				model.addObject(ConstantsJsp.FORMSALE, sale);
				model.setViewName(VIEWNEWSALEPOSTPONED);
			}
		} else {
			model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
			model.addObject(ConstantsJsp.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
			model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsJsp.FORMSALE, sale);
			model.setViewName(VIEWNEWSALEPOSTPONED);
		}
		return model;
	}

	@RequestMapping(value = "/employee/addinstallment")
	public ModelAndView addinstallment() {
		ModelAndView model = new ModelAndView(ADDINSTALLMENT);
		model.addObject(INSTALLMENT, new Installment());
		model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
		return model;
	}

	@RequestMapping(value = "/employee/saveinstallment")
	public ModelAndView saveinstallment(@ModelAttribute(INSTALLMENT) Installment installment, BindingResult result,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		installmentvalidator.validate(installment, result);
		if (result.hasErrors()) {
			model.setViewName(ADDINSTALLMENT);
			model.addObject(INSTALLMENT, installment);
			model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
		} else {
			SalePostPoned sale = saleservicepostponed.addInstallment(installment);
			if (sale != null) {
				if (sale.getDateretired() != null) {
					String user = SecurityContextHolder.getContext().getAuthentication().getName();
					String ipAddress = request.getHeader(ConstantsJsp.XFORWARDEDFOR);
					if (ipAddress == null) {
						ipAddress = request.getRemoteAddr();
					}
					Daily daily = dailyService.getDaily(new Date(), placeService.getPlaceUser(user), ipAddress);
					if (daily.getFinalamount() == null) {
						model.setViewName(ConstantsJsp.VIEWNOTDAILY);
					} else {
						model.addObject(ConstantsJsp.DAILY, daily);
						model.setViewName(ConstantsJsp.VIEWDAILYARROW);
						model.addObject(ConstantsJsp.DATEDAILY, new Date());
					}
				} else {
					model.setViewName("finishaddinstallment");
					model.addObject(ConstantsJsp.FORMSALE, sale);
				}
			} else {
				model.setViewName(ADDINSTALLMENT);
				model.addObject(INSTALLMENT, installment);
				model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
				result.rejectValue("idsalepostponed", "salenotexit");
			}
		}
		return model;
	}

	@RequestMapping(value = "/employee/howmanyamount")
	public ModelAndView howmanyamount(@ModelAttribute(ConstantsJsp.FORMSALE) SalePostPoned sale, BindingResult arg1) {
		ModelAndView model = new ModelAndView(ADDINSTALLMENT);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsJsp.IDSALE, ConstantsJsp.ERRORSELECTIDSALE);
		// comprobamos si existe la venta
		SalePostponedEntity entitySale = saleservicepostponed.searchByNumsale(sale.getIdsale());
		if (entitySale != null) {
			BigDecimal howmany = saleservicepostponed.howmanyamount(entitySale);
			model.addObject("howmany", howmany);
			model.addObject("installments", sale.getSpayments());
		} else {
			arg1.rejectValue(ConstantsJsp.IDSALE, "salenotexit");
		}
		return model;
	}
}
