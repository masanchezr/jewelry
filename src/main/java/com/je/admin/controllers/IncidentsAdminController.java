package com.je.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.services.incidents.Incident;
import com.je.services.incidents.IncidentService;
import com.je.utils.constants.ConstantsJsp;

@Controller
public class IncidentsAdminController {

	@Autowired
	private IncidentService incidentService;

	@GetMapping("/allincidents")
	public ModelAndView allincidents() {
		ModelAndView model = new ModelAndView("admin/incidents/allincidents");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject("incidents", incidentService.searchAllIncidents());
		model.addObject(ConstantsJsp.FORMINCIDENT, new Incident());
		return model;
	}

	@GetMapping("/pendingissues")
	public ModelAndView pendingissues() {
		ModelAndView model = new ModelAndView("admin/incidents/allincidents");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject("incidents", incidentService.searchPending());
		model.addObject(ConstantsJsp.FORMINCIDENT, new Incident());
		return model;
	}

	@GetMapping("/searchincident")
	public ModelAndView searchIncident(@ModelAttribute(ConstantsJsp.FORMINCIDENT) Incident incident) {
		ModelAndView model = new ModelAndView("admin/incidents/updateincident");
		model.addObject(ConstantsJsp.FORMINCIDENT, incidentService.searchIncident(incident));
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}

	@GetMapping("/resolvedincident")
	public ModelAndView resolvedIncident(@ModelAttribute(ConstantsJsp.FORMINCIDENT) Incident incident) {
		if (incident != null && incident.getIdincident() != null) {
			incidentService.resolve(incident);
		}
		return pendingissues();
	}
}
