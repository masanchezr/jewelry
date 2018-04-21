package com.je.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.forms.SearchForm;
import com.je.services.sales.SalesPostPonedService;
import com.je.utils.constants.ConstantsJsp;

@Controller
public class SalePostPonedAdminController {

	@Autowired
	private SalesPostPonedService salesPostPonedService;

	@RequestMapping(value = "/showsalepost{id}")
	public ModelAndView showsale(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("showsalepost");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.FORMSALE, salesPostPonedService.searchByPK(id));
		return model;
	}

	@RequestMapping(value = "/searchmissingsalepostponed")
	public ModelAndView salepostponed(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchForm searchForm) {
		ModelAndView model = new ModelAndView(ConstantsJsp.SALEPOSTPONED);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.NUMSALE, salesPostPonedService.getMissing());
		return model;
	}

}
