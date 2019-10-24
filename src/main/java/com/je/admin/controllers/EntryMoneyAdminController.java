package com.je.admin.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.forms.SearchForm;
import com.je.services.entrymoney.EntryMoneyService;
import com.je.utils.constants.ConstantsViews;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;
import com.je.validators.SearchFormValidator;

@Controller
public class EntryMoneyAdminController {

	@Autowired
	private EntryMoneyService entryMoneyService;

	@Autowired
	private SearchFormValidator adminSearchValidator;

	private static final String VIEWSEARCHENTRIES = "admin/entriesmoney/searchentries";

	@GetMapping("/searchEntries")
	public ModelAndView searchEntries() {
		ModelAndView model = new ModelAndView(VIEWSEARCHENTRIES);
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.FORMSEARCH, new SearchForm());
		return model;
	}

	@PostMapping("/resultentries")
	public ModelAndView resultEntries(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchForm asf, BindingResult arg1) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		adminSearchValidator.validate(asf, arg1);
		if (arg1.hasErrors()) {
			model.setViewName("VIEWSEARCHENTRIES");
			model.addObject(ConstantsViews.FORMSEARCH, asf);
		} else {
			model.setViewName("admin/entriesmoney/resultentriesmoney");
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
