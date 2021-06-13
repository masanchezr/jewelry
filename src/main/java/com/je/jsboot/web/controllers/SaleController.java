package com.je.jsboot.web.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.je.jsboot.dbaccess.entities.CategoryEntity;
import com.je.jsboot.dbaccess.entities.JewelEntity;
import com.je.jsboot.services.categories.CategoriesService;
import com.je.jsboot.services.payment.PaymentService;
import com.je.jsboot.services.sales.Addresses;
import com.je.jsboot.services.sales.SaleService;
import com.je.jsboot.services.users.Client;
import com.je.jsboot.services.users.RegistrationService;
import com.je.jsboot.utils.constants.ConstantsViews;
import com.je.jsboot.utils.string.Util;
import com.je.jsboot.web.forms.BuyForm;
import com.je.jsboot.web.forms.DataClientForm;
import com.je.jsboot.web.forms.SearchJewelForm;
import com.je.jsboot.web.validators.DataClientFormValidator;

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

	private static final String VIEWSHOPPINGCART = "shoppingcart";

	/**
	 * Buy.
	 * 
	 * @param dataForm the data form
	 * @param result   the result
	 * @return the model and view
	 */
	@PostMapping("/comprar")
	public ModelAndView buy(
			@Validated(DataClientFormValidator.class) @ModelAttribute("dataForm") DataClientForm dataForm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		Iterable<CategoryEntity> categories = searchCategoriesService.getAllCategoriesOrderByName();
		String jsp = "sale";
		if (!result.hasErrors()) {
			// guardamos o actualizamos información del cliente
			Client client = new Client();
			client.setNifclient(dataForm.getNif());
			String mail = dataForm.getEmail();
			String name = dataForm.getName();
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
				jsp = "web/chooseAddress";
			}
			BuyForm buyForm = new BuyForm();
			buyForm.setJewels(dataForm.getJewels());
			buyForm.setNif(dataForm.getNif());
			model.addObject("buyForm", buyForm);
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActiveFalse());
			model.setViewName(jsp);
			model.addObject(ConstantsViews.BREADCRUMBS, "Home >> Producto seleccionado >> Comprar");
		} else {
			model.setViewName("web/cart");
		}
		model.addObject(ConstantsViews.FORMSEARCH, new SearchJewelForm());
		model.addObject(ConstantsViews.CATEGORIES, categories);
		return model;
	}

	@GetMapping("/checkout")
	public ModelAndView checkout() {
		ModelAndView model = new ModelAndView(VIEWSHOPPINGCART);
		model.addObject("dataForm", new DataClientForm());
		model.addObject(ConstantsViews.FORMSEARCH, new SearchJewelForm());
		model.addObject(ConstantsViews.CATEGORIES, searchCategoriesService.getAllCategoriesOrderByName());
		return model;
	}

	@GetMapping("/eliminar{idjewel}")
	public ModelAndView deleteJewelEntity(@PathVariable("idjewel") long idjewel,
			@ModelAttribute("cart") List<JewelEntity> cart) {
		ModelAndView model = new ModelAndView(VIEWSHOPPINGCART);
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

	@GetMapping("/endsale")
	public String goodbye(SessionStatus status) {
		status.setComplete();
		return "goodbye";
	}
}
