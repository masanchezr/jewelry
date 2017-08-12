package com.je.web.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.je.dbaccess.entities.CategoryEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.services.categories.CategoriesService;
import com.je.services.payment.PaymentService;
import com.je.services.sales.Addresses;
import com.je.services.sales.SaleService;
import com.je.services.users.Client;
import com.je.services.users.RegistrationService;
import com.je.utils.string.Util;
import com.je.web.forms.BuyForm;
import com.je.web.forms.DataClientForm;
import com.je.web.forms.SearchJewelForm;
import com.je.web.validators.DataClientFormValidator;

/**
 * The Class SaleController.
 */
@Controller
@SessionAttributes({ "cart" })
public class SaleController {

	/** The payment service. */
	@Autowired
	private PaymentService paymentService;

	/** The sale service. */
	@Autowired
	private SaleService saleService;

	/** The registration service. */
	@Autowired
	private RegistrationService registrationService;

	/** The search categories service. */
	@Autowired
	private CategoriesService searchCategoriesService;

	/** The data client form validator. */
	@Autowired
	private DataClientFormValidator dataClientFormValidator;

	/**
	 * Buy.
	 * 
	 * @param dataForm
	 *            the data form
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/comprar")
	public ModelAndView buy(@ModelAttribute("dataForm") DataClientForm dataForm, BindingResult result) {
		ModelAndView model = new ModelAndView();
		Iterable<CategoryEntity> categories = searchCategoriesService.getAllCategoriesOrderByName();
		String jsp = "sale";
		// hay que validar este formulario
		dataClientFormValidator.validate(dataForm, result);
		if (!result.hasErrors()) {
			// guardamos o actualizamos información del cliente
			Client client = new Client();
			client.setNifclient(dataForm.getNif());
			String mail = dataForm.getEmail(), name = dataForm.getName();
			Long telephone = dataForm.getTelephone();
			if (!Util.isEmpty(name)) {
				client.setName(name);
			}
			if (!Util.isEmpty(mail)) {
				client.setEmail(mail);
			}
			if (telephone != null) {
				client.setTelephone(telephone);
			}
			registrationService.registerUser(client);
			// buscamos si ya realizó alguna compra el cliente para mostrar sus
			// direcciones
			Addresses addresses = saleService.searchAddressByClient(dataForm.getNif());
			if (addresses != null) {
				model.addObject("addressesbilling", addresses.getAddressesBilling());
				model.addObject("addressesmailing", addresses.getAddressesMailing());
				jsp = "chooseAddress";
			}
			BuyForm buyForm = new BuyForm();
			buyForm.setJewels(dataForm.getJewels());
			buyForm.setNif(dataForm.getNif());
			model.addObject("buyForm", buyForm);
			model.addObject("payments", paymentService.findAllActiveFalse());
			model.setViewName(jsp);
			model.addObject("breadcrumbs", "Home >> Producto seleccionado >> Comprar");
		} else {
			model.setViewName("shoppingcart");
		}
		model.addObject("search", new SearchJewelForm());
		model.addObject("categories", categories);
		return model;
	}

	@RequestMapping(value = "/checkout")
	public ModelAndView checkout() {
		ModelAndView model = new ModelAndView("shoppingcart");
		model.addObject("dataForm", new DataClientForm());
		model.addObject("search", new SearchJewelForm());
		model.addObject("categories", searchCategoriesService.getAllCategoriesOrderByName());
		return model;
	}

	@RequestMapping(value = "/eliminar{idjewel}")
	public ModelAndView deleteJewelEntity(@PathVariable("idjewel") long idjewel,
			@ModelAttribute("cart") List<JewelEntity> cart) {
		ModelAndView model = new ModelAndView("shoppingcart");
		model.addObject("dataForm", new DataClientForm());
		Iterator<JewelEntity> icart = cart.iterator();
		JewelEntity jewel;
		while (icart.hasNext()) {
			jewel = icart.next();
			if (jewel.getIdjewel().equals(idjewel)) {
				cart.remove(jewel);
				break;
			}
		}
		return model;
	}
}