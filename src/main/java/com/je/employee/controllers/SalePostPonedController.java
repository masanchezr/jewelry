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
import com.je.utils.string.Util;

@Controller
public class SalePostPonedController {

	@Autowired
	private SalesPostPonedService saleservicepostponed;

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
	private PlaceService placeService;

	@Autowired
	private DailyService dailyService;

	@Autowired
	private SalePostPonedValidator salepostponedvalidator;

	@Autowired
	private InstallmentValidator installmentvalidator;

	@RequestMapping(value = "/employee/newsalepostponed")
	public ModelAndView newSalepostponed() {
		ModelAndView model = new ModelAndView("newsalepostponed");
		SalePostPoned sale = new SalePostPoned();
		List<JewelEntity> jewels = new ArrayList<JewelEntity>();
		for (int i = 0; i < Constants.MAXJEWELS; i++) {
			jewels.add(new JewelEntity());
		}
		sale.setJewels(jewels);
		model.addObject("metals", metalService.getAllMetals());
		model.addObject("categories", categoriesService.getAllCategoriesOrderByName());
		model.addObject("payments", paymentService.findAllActive());
		model.addObject("saleForm", sale);
		return model;
	}

	@RequestMapping(value = "/employee/savesalepostponed")
	public ModelAndView savesalepostponed(@ModelAttribute("saleForm") SalePostPoned saleForm, BindingResult result) {
		ModelAndView model = new ModelAndView();
		salepostponedvalidator.validate(saleForm, result);
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
				SalePostponedEntity sale = saleservicepostponed.searchByNumsale(saleForm.getIdsale());
				if (sale == null) {
					saleservicepostponed.buy(saleForm);
					model.setViewName("finishsalepostponed");
					model.addObject("sale", saleForm);
				} else {
					model.addObject("metals", metalService.getAllMetals());
					model.addObject("categories", categoriesService.getAllCategoriesOrderByName());
					model.addObject("payments", paymentService.findAllActive());
					result.rejectValue("idsale", "numsalerepeated");
					model.addObject("saleForm", saleForm);
					model.setViewName("newsalepostponed");
				}
			} else {
				model.addObject("metals", metalService.getAllMetals());
				model.addObject("categories", categoriesService.getAllCategoriesOrderByName());
				model.addObject("payments", paymentService.findAllActive());
				result.rejectValue("idsale", "jewelnoexist");
				model.addObject("saleForm", saleForm);
				model.setViewName("newsalepostponed");
			}
		} else {
			model.addObject("metals", metalService.getAllMetals());
			model.addObject("categories", categoriesService.getAllCategoriesOrderByName());
			model.addObject("payments", paymentService.findAllActive());
			model.addObject("saleForm", saleForm);
			model.setViewName("newsalepostponed");
		}
		return model;
	}

	@RequestMapping(value = "/employee/addinstallment")
	public ModelAndView addinstallment() {
		ModelAndView model = new ModelAndView("addinstallment");
		model.addObject("installment", new Installment());
		model.addObject("payments", paymentService.findAllActive());
		return model;
	}

	@RequestMapping(value = "/employee/saveinstallment")
	public ModelAndView saveinstallment(@ModelAttribute("installment") Installment installment, BindingResult result,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		installmentvalidator.validate(installment, result);
		if (result.hasErrors()) {
			model.setViewName("addinstallment");
			model.addObject("installment", installment);
			model.addObject("payments", paymentService.findAllActive());
		} else {
			SalePostPoned sale = saleservicepostponed.addInstallment(installment);
			if (sale != null) {
				if (sale.getDateretired() != null) {
					String user = SecurityContextHolder.getContext().getAuthentication().getName();
					String ipAddress = request.getHeader("X-FORWARDED-FOR");
					if (ipAddress == null) {
						ipAddress = request.getRemoteAddr();
					}
					Daily daily = dailyService.getDaily(new Date(), placeService.getPlaceUser(user), ipAddress);
					if (daily.getFinalamount() == null) {
						model.setViewName("notdaily");
					} else {
						model.addObject("daily", daily);
						model.setViewName("daily");
						model.addObject("datedaily", new Date());
					}
				} else {
					model.setViewName("finishaddinstallment");
					model.addObject("sale", sale);
				}
			} else {
				model.setViewName("addinstallment");
				model.addObject("installment", installment);
				model.addObject("payments", paymentService.findAllActive());
				result.rejectValue("idsalepostponed", "salenotexit");
			}
		}
		return model;
	}

	@RequestMapping(value = "/employee/howmanyamount")
	public ModelAndView howmanyamount(@ModelAttribute("saleForm") SalePostPoned saleForm, BindingResult arg1) {
		ModelAndView model = new ModelAndView("addinstallment");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "idsale", "selectidsale");
		// comprobamos si existe la venta
		SalePostponedEntity sale = saleservicepostponed.searchByNumsale(saleForm.getIdsale());
		if (sale != null) {
			BigDecimal howmany = saleservicepostponed.howmanyamount(sale);
			model.addObject("howmany", howmany);
			model.addObject("installments", sale.getSpayments());
		} else {
			arg1.rejectValue("idsale", "salenotexit");
		}
		return model;
	}
}
