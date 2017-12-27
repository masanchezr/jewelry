package com.je.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.services.sales.SalesPostPonedService;

@Controller
public class SalePostPonedAdminController {

	@Autowired
	private SalesPostPonedService salesPostPonedService;

	@RequestMapping(value = "/showsalepost{id}")
	public ModelAndView showsale(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("showsalepost");
		model.addObject("adminForm", new AdminForm());
		model.addObject("sale", salesPostPonedService.searchByPK(id));
		return model;
	}

}
