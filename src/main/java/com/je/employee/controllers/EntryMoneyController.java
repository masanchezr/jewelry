package com.je.employee.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.dbaccess.entities.EntryMoneyEntity;
import com.je.employee.validators.EntryMoneyValidator;
import com.je.services.entrymoney.EntryMoneyService;

@Controller
public class EntryMoneyController {

	@Autowired
	private EntryMoneyValidator entryMoneyValidator;

	@Autowired
	private EntryMoneyService entryMoneyService;

	@RequestMapping(value = "/employee/newentrymoney")
	public ModelAndView newentrymoney() {
		ModelAndView model = new ModelAndView("newentrymoney");
		model.addObject("entrymoneyForm", new EntryMoneyEntity());
		return model;
	}

	@RequestMapping(value = "/employee/saveentrymoney")
	public ModelAndView saveEntryMoney(@ModelAttribute("entrymoneyForm") EntryMoneyEntity entryMoney,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		entryMoneyValidator.validate(entryMoney, result);
		if (result.hasErrors()) {
			model.addObject("entrymoney", entryMoney);
			model.setViewName("newentrymoney");
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			model.addObject("daily", entryMoneyService.saveEntryMoney(user, entryMoney.getAmount()));
			model.setViewName("dailyarrow");
			model.addObject("datedaily", new Date());
		}
		return model;
	}
}
