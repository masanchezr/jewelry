package com.je.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.services.users.Client;
import com.je.services.users.SearchClientsService;

/**
 * The Class ClientsController.
 */
@Controller
public class ClientsController {

	/** The search clients service. */
	@Autowired
	SearchClientsService searchClientsService;

	/**
	 * Search clients.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/searchClients")
	public ModelAndView searchClients() {
		ModelAndView model = new ModelAndView();
		List<Client> clients = searchClientsService.getDataClients();
		model.addObject("clients", clients);
		model.addObject("adminForm", new AdminForm());
		model.setViewName("resultClients");
		return model;
	}
}
