package com.je.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.services.clients.Client;
import com.je.services.clients.ClientService;
import com.je.services.pawns.PawnService;
import com.je.services.shoppings.ShoppingService;
import com.je.utils.constants.ConstantsJsp;

/**
 * The Class ClientsController.
 */
@Controller
public class ClientsController {

	/** The search clients service. */
	@Autowired
	private ClientService searchClientsService;

	@Autowired
	private ShoppingService shoppingService;

	@Autowired
	private PawnService pawnService;

	/**
	 * Search clients.
	 *
	 * @return the model and view
	 */
	@GetMapping("/searchclients")
	public ModelAndView searchClients() {
		ModelAndView model = new ModelAndView("admin/clients/searchclients");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject("client", new Client());
		return model;
	}

	@PostMapping("/resultclients")
	public ModelAndView resultclients(@ModelAttribute("client") Client client) {
		ModelAndView model = new ModelAndView();
		List<Client> clients = searchClientsService.searchClients(client);
		model.addObject("clientModel", new Client());
		model.addObject("clients", clients);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.setViewName("admin/clients/resultClients");
		return model;
	}

	@GetMapping("/showoperations")
	public ModelAndView showoperations(@ModelAttribute("clientModel") Client client) {
		ModelAndView model = new ModelAndView("admin/clients/resultoperations");
		model.addObject("shoppings", shoppingService.getByNIF(client.getNif()));
		model.addObject("pawns", pawnService.getByNIF(client.getNif()));
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}
}
