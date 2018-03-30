package com.je.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.services.clients.Client;
import com.je.services.clients.ClientService;
import com.je.utils.constants.ConstantsJsp;

/**
 * The Class ClientsController.
 */
@Controller
public class ClientsController {

	/** The search clients service. */
	@Autowired
	private ClientService searchClientsService;

	/**
	 * Search clients.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/searchclients")
	public ModelAndView searchClients() {
		ModelAndView model = new ModelAndView("searchclients");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject("client", new Client());
		return model;
	}

	@RequestMapping(value = "/resultclients")
	public ModelAndView resultclients(Client client) {
		ModelAndView model = new ModelAndView();
		List<Client> clients = searchClientsService.searchClients(client);
		model.addObject("clients", clients);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.setViewName("resultClients");
		return model;
	}
}
