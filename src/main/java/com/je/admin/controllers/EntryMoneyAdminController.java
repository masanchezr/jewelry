package com.je.admin.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.forms.SearchForm;
import com.je.services.entrymoney.EntryMoneyService;
import com.je.utils.constants.ConstantsJsp;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;
import com.je.validators.SearchFormValidator;

@Controller
public class EntryMoneyAdminController {

	@Autowired
	private EntryMoneyService entryMoneyService;

	@Autowired
	private SearchFormValidator adminSearchValidator;

	@RequestMapping(value = "/searchEntries")
	public ModelAndView searchEntries() {
		ModelAndView model = new ModelAndView("searchentries");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchForm());
		return model;
	}

	@RequestMapping(value = "/resultentries")
	public ModelAndView resultEntries(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchForm asf, BindingResult arg1) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		adminSearchValidator.validate(asf, arg1);
		if (arg1.hasErrors()) {
			model.setViewName("searchentries");
			model.addObject(ConstantsJsp.FORMSEARCH, asf);
		} else {
			model.setViewName("resultentriesmoney");
			Date dfrom = DateUtil.getDate(asf.getDatefrom());
			String suntil = asf.getDateuntil();
			Date duntil;
			if (!Util.isEmpty(suntil)) {
				duntil = DateUtil.getDate(asf.getDateuntil());
			} else {
				duntil = new Date();
			}
			model.addObject("entries", entryMoneyService.findByDates(dfrom, duntil));
		}
		return model;
	}
}
