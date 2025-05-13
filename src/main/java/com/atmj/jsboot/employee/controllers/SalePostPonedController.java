package com.atmj.jsboot.employee.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.dbaccess.entities.JewelEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.entities.SalePostponedEntity;
import com.atmj.jsboot.employee.validators.InstallmentValidator;
import com.atmj.jsboot.employee.validators.SalePostPonedValidator;
import com.atmj.jsboot.forms.SalePostPoned;
import com.atmj.jsboot.services.categories.CategoriesService;
import com.atmj.jsboot.services.jewels.JewelService;
import com.atmj.jsboot.services.metal.MetalService;
import com.atmj.jsboot.services.payment.PaymentService;
import com.atmj.jsboot.services.places.PlaceService;
import com.atmj.jsboot.services.sales.Installment;
import com.atmj.jsboot.services.sales.SalesPostPonedService;
import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.constants.ConstantsViews;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SalePostPonedController {

	/** The categories service. */
	private CategoriesService categoriesService;

	/** The jewel service. */
	private JewelService jewelService;

	/** The material service. */
	private MetalService materialService;

	private PaymentService paymentService;

	private PlaceService placeService;

	private SalesPostPonedService saleservicepostponed;

	private SalePostPonedValidator salePostPonedValidator;

	private InstallmentValidator installmentValidator;

	private static final String ADDINSTALLMENT = "employee/salespostponed/addinstallment";
	private static final String INSTALLMENT = "installment";
	private static final String VIEWNEWSALEPOSTPONED = "employee/salespostponed/newsale";

	public SalePostPonedController(CategoriesService categoriesService, JewelService jewelService,
			MetalService materialService, PaymentService paymentService, PlaceService placeService,
			SalesPostPonedService saleservicepostponed, SalePostPonedValidator salePostPonedValidator,
			InstallmentValidator installmentValidator) {
		this.categoriesService = categoriesService;
		this.jewelService = jewelService;
		this.materialService = materialService;
		this.paymentService = paymentService;
		this.placeService = placeService;
		this.saleservicepostponed = saleservicepostponed;
		this.salePostPonedValidator = salePostPonedValidator;
		this.installmentValidator = installmentValidator;
	}

	@GetMapping("/employee/newsalepostponed")
	public ModelAndView newSalepostponed() {
		ModelAndView model = new ModelAndView(VIEWNEWSALEPOSTPONED);
		SalePostPoned sale = new SalePostPoned();
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

	@PostMapping("/employee/savesalepostponed")
	public ModelAndView savesalepostponed(@ModelAttribute(ConstantsViews.FORMSALE) SalePostPoned sale,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		salePostPonedValidator.validate(sale, result);
		if (!result.hasErrors()) {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			List<JewelEntity> jewels = sale.getJewels();
			PlaceEntity place = placeService.getPlaceUser(user);
			List<JewelEntity> newjewels = jewelService.searchJewels(jewels, place);
			if (!jewels.isEmpty() && newjewels != null) {
				sale.setJewels(newjewels);
				sale.setPlace(place);
				// comprobamos si ya existe la venta
				SalePostponedEntity entitySale = saleservicepostponed.searchByNumsale(sale.getIdsale());
				if (entitySale == null) {
					saleservicepostponed.buy(sale);
					model.setViewName("employee/salespostponed/finishsale");
					model.addObject(ConstantsViews.FORMSALE, sale);
				} else {
					model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
					model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
					model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
					result.rejectValue(ConstantsViews.IDSALE, ConstantsViews.ERRORNUMSALEREPEATED);
					model.addObject(ConstantsViews.FORMSALE, sale);
					model.setViewName(VIEWNEWSALEPOSTPONED);
				}
			} else {
				model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
				model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
				model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
				result.rejectValue(ConstantsViews.IDSALE, "jewelnoexist");
				model.addObject(ConstantsViews.FORMSALE, sale);
				model.setViewName(VIEWNEWSALEPOSTPONED);
			}
		} else {
			model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
			model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsViews.FORMSALE, sale);
			model.setViewName(VIEWNEWSALEPOSTPONED);
		}
		return model;
	}

	@GetMapping("/employee/addinstallment")
	public ModelAndView addinstallment() {
		ModelAndView model = new ModelAndView(ADDINSTALLMENT);
		model.addObject(INSTALLMENT, new Installment());
		model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
		return model;
	}

	@PostMapping("/employee/saveinstallment")
	public ModelAndView saveinstallment(@ModelAttribute(INSTALLMENT) Installment installment, BindingResult result,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		installmentValidator.validate(installment, result);
		if (result.hasErrors()) {
			model.setViewName(ADDINSTALLMENT);
			model.addObject(INSTALLMENT, installment);
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
		} else {
			SalePostPoned sale = saleservicepostponed.addInstallment(installment);
			if (sale != null) {
				model.setViewName("employee/salespostponed/finishaddinstallment");
				model.addObject(ConstantsViews.FORMSALE, sale);
			} else {
				model.setViewName(ADDINSTALLMENT);
				model.addObject(INSTALLMENT, installment);
				model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
				result.rejectValue(Constants.IDSALEPOSTPONED, ConstantsViews.ERRORSALENOTEXIST);
			}
		}
		return model;
	}

	@GetMapping("/employee/howmanyamount")
	public ModelAndView howmanyamount(@ModelAttribute(ConstantsViews.FORMSALE) SalePostPoned sale, BindingResult arg1) {
		ModelAndView model = new ModelAndView(ADDINSTALLMENT);
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, ConstantsViews.IDSALE, ConstantsViews.ERRORSELECTIDSALE);
		// comprobamos si existe la venta
		SalePostponedEntity entitySale = saleservicepostponed.searchByNumsale(sale.getIdsale());
		if (entitySale != null) {
			BigDecimal howmany = saleservicepostponed.howmanyamount(entitySale);
			model.addObject("howmany", howmany);
			model.addObject("installments", sale.getSpayments());
		} else {
			arg1.rejectValue(ConstantsViews.IDSALE, ConstantsViews.ERRORSALENOTEXIST);
		}
		return model;
	}

	@GetMapping("/employee/getsptimeout")
	public ModelAndView getsptimeout() {
		ModelAndView model = new ModelAndView("employee/salespostponed/resultsalespostponed");
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addObject(Constants.SALESPOSTPONED, saleservicepostponed.getListTimeout(placeService.getPlaceUser(user)));
		return model;
	}

	@GetMapping("/employee/searchsalepostponed")
	public ModelAndView searchsalepostponed() {
		ModelAndView model = new ModelAndView("employee/salespostponed/searchsalepostponed");
		model.addObject(ConstantsViews.FORMSALE, new SalePostPoned());
		return model;
	}

	@PostMapping("/employee/resultsalepostponed")
	public ModelAndView resultsalepostponed(@ModelAttribute(ConstantsViews.FORMSALE) SalePostPoned sale,
			BindingResult arg1) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		SalePostPoned salep = saleservicepostponed.searchByIdAndPlace(sale.getIdsale(),
				placeService.getPlaceUser(user));
		if (salep != null) {
			model.setViewName("employee/salespostponed/sale");
			model.addObject(ConstantsViews.FORMSALE, salep);
		} else {
			model.setViewName("employee/salespostponed/searchsalepostponed");
			model.addObject(ConstantsViews.FORMSALE, sale);
			arg1.rejectValue("idsale", ConstantsViews.ERRORSALENOTEXIST);
		}
		return model;
	}
}
