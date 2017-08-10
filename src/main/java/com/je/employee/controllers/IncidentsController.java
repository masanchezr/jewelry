package com.je.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.employee.validators.IncidentValidator;
import com.je.forms.SearchForm;
import com.je.services.incidents.Incident;
import com.je.services.incidents.IncidentService;
import com.je.validators.SearchFormValidator;

@Controller
public class IncidentsController {

	@Autowired
	private IncidentService incidentService;

	@Autowired
	private IncidentValidator incidentValidator;

	@Autowired
	private SearchFormValidator searchFormValidator;

	@RequestMapping(value = "/employee/newincident")
	public ModelAndView newIncident() {
		ModelAndView model = new ModelAndView("newincident");
		model.addObject("incident", new Incident());
		return model;
	}

	@RequestMapping(value = "/employee/saveincident")
	public ModelAndView saveIncident(@ModelAttribute("incident") Incident incident, BindingResult result) {
		ModelAndView model = new ModelAndView();
		incidentValidator.validate(incident, result);
		if (result.hasErrors()) {
			model.setViewName("newincident");
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			// String description = incident.getDescription();
			model.setViewName("saveincident");
			incident.setUser(user);
			incidentService.save(incident);
		}
		model.addObject("incident", incident);
		return model;
	}

	@RequestMapping(value = "/employee/myincidents")
	public ModelAndView myincidents() {
		ModelAndView model = new ModelAndView("searchincidents");
		model.addObject("searchForm", new SearchForm());
		return model;
	}

	@RequestMapping(value = "/employee/resultIncidents")
	public ModelAndView resultSearchIncidents(@ModelAttribute("searchForm") SearchForm form, BindingResult result) {
		ModelAndView model = new ModelAndView();
		searchFormValidator.validate(form, result);
		if (result.hasErrors()) {
			model.addObject("searchForm", form);
			model.setViewName("searchincidents");
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			List<Incident> incidents = incidentService.searchByUserAndDates(user, form);
			model.addObject("incidents", incidents);
			model.setViewName("myincidents");
		}
		return model;
	}

	@RequestMapping("/employee/pendingissues")
	public ModelAndView pendingissues() {
		ModelAndView model = new ModelAndView("myincidents");
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Incident> incidents = incidentService.searchPendingByUser(user);
		model.addObject("incidents", incidents);
		return model;
	}
}
