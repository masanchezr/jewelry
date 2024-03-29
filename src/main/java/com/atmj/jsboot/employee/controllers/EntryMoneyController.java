package com.atmj.jsboot.employee.controllers;

import java.util.Date;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.dbaccess.entities.EntryMoneyEntity;
import com.atmj.jsboot.forms.EntryMoney;
import com.atmj.jsboot.services.entrymoney.EntryMoneyService;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.date.DateUtil;

@Controller
public class EntryMoneyController {

	@Autowired
	private EntryMoneyService entryMoneyService;

	@GetMapping("/employee/newentrymoney")
	public ModelAndView newentrymoney() {
		ModelAndView model = new ModelAndView("employee/newentrymoney");
		model.addObject(ConstantsViews.FORMENTRYMONEY, new EntryMoneyEntity());
		return model;
	}

	@PostMapping("/employee/saveentrymoney")
	public ModelAndView saveEntryMoney(@Valid EntryMoney entryMoney, BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.addObject(ConstantsViews.FORMENTRYMONEY, entryMoney);
			model.setViewName("employee/newentrymoney");
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			model.addObject(ConstantsViews.DAILY, entryMoneyService.saveEntryMoney(user, entryMoney.getAmount()));
			model.setViewName(ConstantsViews.VIEWDAILYARROW);
			model.addObject(ConstantsViews.DATEDAILY, DateUtil.getStringDateddMMyyyy(new Date()));
		}
		return model;
	}
}
