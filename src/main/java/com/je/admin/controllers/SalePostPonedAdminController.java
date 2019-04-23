package com.je.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.forms.SalePostPoned;
import com.je.services.sales.SalesPostPonedService;
import com.je.utils.constants.ConstantsJsp;

@Controller
public class SalePostPonedAdminController {

	@Autowired
	private SalesPostPonedService salesPostPonedService;

	@RequestMapping(value = "/showsalepost{id}")
	public ModelAndView showsale(@PathVariable("id") long id, BindingResult result) {
		return getModelSalePostponed(id, result);
	}

	@RequestMapping(value = "/searchmissingsalepostponed")
	public ModelAndView salepostponed() {
		ModelAndView model = new ModelAndView("salepostponedadmin");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.NUMSALE, salesPostPonedService.getMissing());
		return model;
	}

	@RequestMapping(value = "/searchsalepostponed")
	public ModelAndView searchsalepostponed() {
		ModelAndView model = new ModelAndView("searchsalepostponed");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.FORMSALEPOSTPONED, new SalePostPoned());
		return model;
	}

	@RequestMapping(value = "/showsale")
	public ModelAndView showsale(@ModelAttribute(ConstantsJsp.FORMSALEPOSTPONED) SalePostPoned salepostponed,
			BindingResult result) {
		return getModelSalePostponed(salepostponed.getIdsale(), result);
	}

	private ModelAndView getModelSalePostponed(long id, BindingResult result) {
		ModelAndView model = new ModelAndView();
		SalePostPoned spp = salesPostPonedService.searchByPK(id);
		if (spp != null) {
			model.setViewName("showsalepost");
			model.addObject(ConstantsJsp.FORMSALE, spp);
		} else {
			model.setViewName("searchsalepostponed");
			model.addObject(ConstantsJsp.FORMSALEPOSTPONED, new SalePostPoned());
			result.rejectValue("idsale", "salepostponednotexist");
		}
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	@RequestMapping(value = "/timeout{id}")
	public ModelAndView timeout(@PathVariable long id, BindingResult result) {
		salesPostPonedService.timeout(id);
		return getModelSalePostponed(id, result);
	}

	@RequestMapping(value = "/searchexpired")
	public ModelAndView searchExpired() {
		ModelAndView model = new ModelAndView("expired");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject("resultexpired", salesPostPonedService.getListTimeout());
		return model;
	}
}
