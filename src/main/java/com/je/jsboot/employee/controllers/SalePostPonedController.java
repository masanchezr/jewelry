package com.je.jsboot.employee.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.jsboot.dbaccess.entities.JewelEntity;
import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.entities.SalePostponedEntity;
import com.je.jsboot.employee.validators.InstallmentValidator;
import com.je.jsboot.employee.validators.SalePostPonedValidator;
import com.je.jsboot.forms.SalePostPoned;
import com.je.jsboot.services.categories.CategoriesService;
import com.je.jsboot.services.dailies.Daily;
import com.je.jsboot.services.dailies.DailyService;
import com.je.jsboot.services.jewels.JewelService;
import com.je.jsboot.services.metal.MetalService;
import com.je.jsboot.services.payment.PaymentService;
import com.je.jsboot.services.places.PlaceService;
import com.je.jsboot.services.sales.Installment;
import com.je.jsboot.services.sales.SalesPostPonedService;
import com.je.jsboot.utils.constants.Constants;
import com.je.jsboot.utils.constants.ConstantsViews;
import com.je.jsboot.utils.date.DateUtil;

@Controller
public class SalePostPonedController {

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

	@Autowired
	private SalesPostPonedService saleservicepostponed;

	private static final String ADDINSTALLMENT = "employee/salespostponed/addinstallment";
	private static final String INSTALLMENT = "installment";
	private static final String VIEWNEWSALEPOSTPONED = "employee/salespostponed/newsale";

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
	public ModelAndView savesalepostponed(
			@Validated(SalePostPonedValidator.class) @ModelAttribute(ConstantsViews.FORMSALE) SalePostPoned sale,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
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
	public ModelAndView saveinstallment(
			@Validated(InstallmentValidator.class) @ModelAttribute(INSTALLMENT) Installment installment,
			BindingResult result, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.setViewName(ADDINSTALLMENT);
			model.addObject(INSTALLMENT, installment);
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
		} else {
			SalePostPoned sale = saleservicepostponed.addInstallment(installment);
			if (sale != null) {
				if (sale.getDateretired() != null) {
					model = getModelDaily(request);
				} else {
					model.setViewName("employee/salespostponed/finishaddinstallment");
					model.addObject(ConstantsViews.FORMSALE, sale);
				}
			} else {
				model.setViewName(ADDINSTALLMENT);
				model.addObject(INSTALLMENT, installment);
				model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
				result.rejectValue(Constants.IDSALEPOSTPONED, ConstantsViews.ERRORSALENOTEXIST);
			}
		}
		return model;
	}

	private ModelAndView getModelDaily(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		String ipAddress = request.getHeader(ConstantsViews.XFORWARDEDFOR);
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		Daily daily = dailyService.getDaily(DateUtil.getDateFormated(new Date()), placeService.getPlaceUser(user),
				ipAddress);
		if (daily.getFinalamount() == null) {
			model.setViewName(ConstantsViews.VIEWNOTDAILY);
		} else {
			model.addObject(ConstantsViews.DAILY, daily);
			model.setViewName(ConstantsViews.VIEWDAILYARROW);
			model.addObject(ConstantsViews.DATEDAILY, DateUtil.getStringDateddMMyyyy(new Date()));
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
