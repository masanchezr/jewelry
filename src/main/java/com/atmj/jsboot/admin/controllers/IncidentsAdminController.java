package com.atmj.jsboot.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.admin.forms.AdminForm;
import com.atmj.jsboot.services.incidents.Incident;
import com.atmj.jsboot.services.incidents.IncidentService;
import com.atmj.jsboot.utils.constants.ConstantsViews;

@Controller
public class IncidentsAdminController {

	@Autowired
	private IncidentService incidentService;

	@GetMapping("/allincidents")
	public ModelAndView allincidents() {
		ModelAndView model = new ModelAndView("admin/incidents/allincidents");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject("incidents", incidentService.searchAllIncidents());
		model.addObject(ConstantsViews.FORMINCIDENT, new Incident());
		return model;
	}

	@GetMapping("/pendingissues")
	public ModelAndView pendingissues() {
		ModelAndView model = new ModelAndView("admin/incidents/allincidents");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject("incidents", incidentService.searchPending());
		model.addObject(ConstantsViews.FORMINCIDENT, new Incident());
		return model;
	}

	@PostMapping("/searchincident")
	public ModelAndView searchIncident(@ModelAttribute(ConstantsViews.FORMINCIDENT) Incident incident) {
		ModelAndView model = new ModelAndView("admin/incidents/updateincident");
		model.addObject(ConstantsViews.FORMINCIDENT, incidentService.searchIncident(incident));
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	@PostMapping("/resolvedincident")
	public ModelAndView resolvedIncident(@ModelAttribute(ConstantsViews.FORMINCIDENT) Incident incident) {
		if (incident != null && incident.getIdincident() != null) {
			incidentService.resolve(incident);
		}
		return pendingissues();
	}
}
