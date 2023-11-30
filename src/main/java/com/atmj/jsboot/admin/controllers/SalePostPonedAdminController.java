package com.atmj.jsboot.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.admin.forms.AdminForm;
import com.atmj.jsboot.forms.SalePostPoned;
import com.atmj.jsboot.services.sales.SalesPostPonedService;
import com.atmj.jsboot.utils.constants.ConstantsViews;

@Controller
public class SalePostPonedAdminController {

	@Autowired
	private SalesPostPonedService salesPostPonedService;

	@GetMapping("/showsalepost{id}")
	public ModelAndView showsale(@PathVariable("id") long id) {
		return getModelSalePostponed(id);
	}

	@GetMapping("/searchmissingsalepostponed")
	public ModelAndView salepostponed() {
		ModelAndView model = new ModelAndView("admin/salespostponed/resultnummissing");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.NUMSALE, salesPostPonedService.getMissing());
		return model;
	}

	@GetMapping("/searchsalepostponed")
	public ModelAndView searchsalepostponed() {
		ModelAndView model = new ModelAndView("admin/salespostponed/searchsalepostponed");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.FORMSALEPOSTPONED, new SalePostPoned());
		return model;
	}

	@PostMapping("/showsale")
	public ModelAndView showsale(@ModelAttribute(ConstantsViews.FORMSALEPOSTPONED) SalePostPoned salepostponed,
			BindingResult result) {
		return getModelSalePostponed(salepostponed.getIdsale());
	}

	private ModelAndView getModelSalePostponed(long id) {
		ModelAndView model = new ModelAndView();
		SalePostPoned spp = salesPostPonedService.searchByPK(id);
		if (spp != null) {
			model.setViewName("admin/salespostponed/finishsale");
			model.addObject(ConstantsViews.FORMSALE, spp);
		} else {
			model.setViewName("admin/salespostponed/searchsalepostponed");
			model.addObject(ConstantsViews.FORMSALEPOSTPONED, new SalePostPoned());
		}
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	@GetMapping("/timeout{id}")
	public ModelAndView timeout(@PathVariable("id") long id) {
		salesPostPonedService.timeout(id);
		return getModelSalePostponed(id);
	}

	@GetMapping("/searchexpired")
	public ModelAndView searchExpired() {
		ModelAndView model = new ModelAndView("admin/salespostponed/expired");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject("resultexpired", salesPostPonedService.getListTimeout());
		return model;
	}
}
