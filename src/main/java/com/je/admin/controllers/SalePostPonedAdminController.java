package com.je.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.forms.SalePostPoned;
import com.je.services.sales.SalesPostPonedService;
import com.je.utils.constants.ConstantsJsp;

@Controller
public class SalePostPonedAdminController {

	@Autowired
	private SalesPostPonedService salesPostPonedService;

	@PostMapping("/showsalepost{id}")
	public ModelAndView showsale(@PathVariable("id") long id) {
		return getModelSalePostponed(id);
	}

	@GetMapping("/searchmissingsalepostponed")
	public ModelAndView salepostponed() {
		ModelAndView model = new ModelAndView("admin/salespostponed/resultnummissing");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.NUMSALE, salesPostPonedService.getMissing());
		return model;
	}

	@GetMapping("/searchsalepostponed")
	public ModelAndView searchsalepostponed() {
		ModelAndView model = new ModelAndView("admin/salespostponed/searchsalepostponed");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.FORMSALEPOSTPONED, new SalePostPoned());
		return model;
	}

	@PostMapping("/showsale")
	public ModelAndView showsale(@ModelAttribute(ConstantsJsp.FORMSALEPOSTPONED) SalePostPoned salepostponed,
			BindingResult result) {
		return getModelSalePostponed(salepostponed.getIdsale());
	}

	private ModelAndView getModelSalePostponed(long id) {
		ModelAndView model = new ModelAndView();
		SalePostPoned spp = salesPostPonedService.searchByPK(id);
		if (spp != null) {
			model.setViewName("admin/salespostponed/finishsale");
			model.addObject(ConstantsJsp.FORMSALE, spp);
		} else {
			model.setViewName("admin/salespostponed/searchsalepostponed");
			model.addObject(ConstantsJsp.FORMSALEPOSTPONED, new SalePostPoned());
		}
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	@PostMapping("/timeout{id}")
	public ModelAndView timeout(@PathVariable long id) {
		salesPostPonedService.timeout(id);
		return getModelSalePostponed(id);
	}

	@GetMapping("/searchexpired")
	public ModelAndView searchExpired() {
		ModelAndView model = new ModelAndView("admin/salespostponed/expired");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject("resultexpired", salesPostPonedService.getListTimeout());
		return model;
	}
}
