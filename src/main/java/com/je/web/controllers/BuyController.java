package com.je.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.je.dbaccess.entities.AddressEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.forms.Sale;
import com.je.services.payment.PaymentService;
import com.je.services.sales.Addresses;
import com.je.services.sales.SaleService;
import com.je.services.users.Client;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;
import com.je.web.forms.BuyForm;
import com.je.web.forms.SearchJewelForm;
import com.je.web.validators.BuyFormValidator;

/**
 * The Class BuyController.
 */
@Controller
@SessionAttributes({ "cart" })
@RequestMapping("/aceptarcompra")
public class BuyController {

	/** The sale service. */
	@Autowired
	private SaleService saleService;

	/** The payment service. */
	@Autowired
	private PaymentService paymentService;

	@Autowired
	private BuyFormValidator buyFormValidator;

	/**
	 * este caso es para cuando el cliente es nuevo.
	 * 
	 * @param buyform the buyform
	 * @param result  the result
	 * @return the model and view
	 */
	@GetMapping
	public ModelAndView add(@ModelAttribute("buyForm") BuyForm buyform, @ModelAttribute("cart") List<JewelEntity> cart,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchJewelForm());
		buyFormValidator.validate(buyform, result);
		if (result.hasErrors()) {
			Addresses addresses = saleService.searchAddressByClient(buyform.getNif());
			if (addresses != null) {
				model.addObject("addressesbilling", addresses.getAddressesBilling());
				model.addObject("addressesmailing", addresses.getAddressesMailing());
				model.setViewName("chooseAddress");
			} else {
				model.setViewName(ConstantsJsp.FORMSALE);
			}
			model.addObject("buyForm", buyform);
			model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActiveFalse());
			return model;
		} else {
			Sale sale = new Sale();
			Client client = new Client();
			PaymentEntity payment = new PaymentEntity();
			AddressEntity addressmailing = new AddressEntity();
			AddressEntity addressbilling = new AddressEntity();
			PlaceEntity place = new PlaceEntity();
			place.setIdplace(Constants.WEB);
			client.setNifclient(buyform.getNif());
			client.setEmail(buyform.getEmail());
			client.setTelephone(buyform.getTelephone());
			client.setName(buyform.getName());
			// añado cliente
			sale.setClient(client);
			// añado joyas
			sale.setJewels(cart);
			sale.setPlace(place);
			payment.setIdpayment(buyform.getPayment());
			// añado tipo de pago
			sale.setPayment(payment);
			addressmailing.setAddress(buyform.getAddress());
			addressmailing.setCity(buyform.getCity());
			addressmailing.setCountry(buyform.getCountry());
			addressmailing.setPostalcode(buyform.getPostalcode());
			// añado dirección de envío
			sale.setMailing(addressmailing);
			if (buyform.getBilling_same_as_shipping() != Constants.CHECKED) {
				addressbilling.setAddress(buyform.getAddressbilling());
				addressbilling.setCity(buyform.getCitybilling());
				addressbilling.setCountry(buyform.getCountrybilling());
				addressbilling.setPostalcode(buyform.getPostalcodebilling());
				addressbilling.setInvoicename(buyform.getNamebilling());
				addressbilling.setCif(buyform.getCif());
			} else {
				addressbilling.setAddress(buyform.getAddress());
				addressbilling.setCity(buyform.getCity());
				addressbilling.setCountry(buyform.getCountry());
				addressbilling.setPostalcode(buyform.getPostalcode());
				addressbilling.setInvoicename(buyform.getName());
				addressbilling.setCif(buyform.getNif());
			}
			// añado dirección de facturación
			sale.setInvoice(addressbilling);
			model.addObject("orderNumber", saleService.buy(sale));
			model.addObject(ConstantsJsp.JEWELS, cart);
		}
		model.setViewName("finishbuy");
		return model;
	}

	@GetMapping("/endbuy")
	public String goodbye(SessionStatus status) {
		status.setComplete();
		return "goodbye";
	}
}