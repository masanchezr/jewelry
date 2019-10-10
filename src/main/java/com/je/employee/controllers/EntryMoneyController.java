package com.je.employee.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.dbaccess.entities.EntryMoneyEntity;
import com.je.employee.validators.EntryMoneyValidator;
import com.je.services.entrymoney.EntryMoneyService;
import com.je.utils.constants.ConstantsJsp;
import com.je.utils.date.DateUtil;

@Controller
public class EntryMoneyController {

	@Autowired
	private EntryMoneyValidator entryMoneyValidator;

	@Autowired
	private EntryMoneyService entryMoneyService;

	@GetMapping(value = "/employee/newentrymoney")
	public ModelAndView newentrymoney() {
		ModelAndView model = new ModelAndView("employee/newentrymoney");
		model.addObject(ConstantsJsp.FORMENTRYMONEY, new EntryMoneyEntity());
		return model;
	}

	@GetMapping(value = "/employee/saveentrymoney")
	public ModelAndView saveEntryMoney(@ModelAttribute(ConstantsJsp.FORMENTRYMONEY) EntryMoneyEntity entryMoney,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		entryMoneyValidator.validate(entryMoney, result);
		if (result.hasErrors()) {
			model.addObject(ConstantsJsp.FORMENTRYMONEY, entryMoney);
			model.setViewName("employee/newentrymoney");
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			model.addObject(ConstantsJsp.DAILY, entryMoneyService.saveEntryMoney(user, entryMoney.getAmount()));
			model.setViewName(ConstantsJsp.VIEWDAILYARROW);
			model.addObject(ConstantsJsp.DATEDAILY, DateUtil.getStringDateddMMyyyy(new Date()));
		}
		return model;
	}
}
