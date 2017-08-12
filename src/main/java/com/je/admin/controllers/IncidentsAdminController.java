package com.je.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.services.incidents.Incident;
import com.je.services.incidents.IncidentService;

@Controller
public class IncidentsAdminController {

	@Autowired
	private IncidentService incidentService;

	@RequestMapping(value = "/allincidents")
	public ModelAndView allincidents() {
		ModelAndView model = new ModelAndView("allincidents");
		model.addObject("adminForm", new AdminForm());
		model.addObject("incidents", incidentService.searchAllIncidents());
		model.addObject("incident", new Incident());
		return model;
	}

	@RequestMapping(value = "/pendingissues")
	public ModelAndView pendingissues() {
		ModelAndView model = new ModelAndView("allincidents");
		model.addObject("adminForm", new AdminForm());
		model.addObject("incidents", incidentService.searchPending());
		model.addObject("incident", new Incident());
		return model;
	}

	@RequestMapping(value = "/searchincident")
	public ModelAndView searchIncident(@ModelAttribute("incident") Incident incident) {
		ModelAndView model = new ModelAndView("updateincident");
		model.addObject("incident", incidentService.searchIncident(incident));
		model.addObject("adminForm", new AdminForm());
		return model;
	}

	@RequestMapping(value = "/resolvedincident")
	public ModelAndView resolvedIncident(@ModelAttribute("incident") Incident incident) {
		if (incident != null && incident.getIdincident() != null) {
			incidentService.resolve(incident);
		} return pendingissues();
	}
}