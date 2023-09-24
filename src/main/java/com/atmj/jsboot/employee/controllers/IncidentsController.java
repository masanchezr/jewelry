package com.atmj.jsboot.employee.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.forms.SearchForm;
import com.atmj.jsboot.services.incidents.Incident;
import com.atmj.jsboot.services.incidents.IncidentService;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.validators.SearchFormValidator;

@Controller
public class IncidentsController {

	@Autowired
	private IncidentService incidentService;

	@Autowired
	private SearchFormValidator searchFormValidator;

	@GetMapping("/employee/newincident")
	public ModelAndView newIncident() {
		ModelAndView model = new ModelAndView("employee/incidents/newincident");
		model.addObject(ConstantsViews.FORMINCIDENT, new Incident());
		return model;
	}

	@PostMapping("/employee/saveincident")
	public ModelAndView saveIncident(@Valid Incident incident, BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.setViewName("employee/incidents/newincident");
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			model.setViewName("employee/incidents/resultsaveincident");
			incident.setUser(user);
			incidentService.save(incident);
		}
		model.addObject(ConstantsViews.FORMINCIDENT, incident);
		return model;
	}

	@GetMapping("/employee/myincidents")
	public ModelAndView myincidents() {
		ModelAndView model = new ModelAndView("employee/incidents/searchincidents");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchForm());
		return model;
	}

	@PostMapping("/employee/resultIncidents")
	public ModelAndView resultSearchIncidents(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchForm form,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		searchFormValidator.validate(form, result);
		if (result.hasErrors()) {
			model.addObject(ConstantsViews.FORMSEARCH, form);
			model.setViewName("employee/incidents/searchincidents");
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			List<Incident> incidents = incidentService.searchByUserAndDates(user, form);
			model.addObject("incidents", incidents);
			model.setViewName("employee/incidents/myincidents");
		}
		return model;
	}

	@GetMapping("/employee/pendingissues")
	public ModelAndView pendingissues() {
		ModelAndView model = new ModelAndView("employee/incidents/myincidents");
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Incident> incidents = incidentService.searchPendingByUser(user);
		model.addObject("incidents", incidents);
		return model;
	}
}
