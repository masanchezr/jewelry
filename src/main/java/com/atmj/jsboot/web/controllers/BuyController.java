package com.atmj.jsboot.web.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.dbaccess.entities.AddressEntity;
import com.atmj.jsboot.dbaccess.entities.JewelEntity;
import com.atmj.jsboot.dbaccess.entities.PaymentEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.forms.Payment;
import com.atmj.jsboot.forms.Sale;
import com.atmj.jsboot.services.payment.PaymentService;
import com.atmj.jsboot.services.sales.Addresses;
import com.atmj.jsboot.services.sales.SaleService;
import com.atmj.jsboot.services.users.Client;
import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.web.forms.BuyForm;
import com.atmj.jsboot.web.forms.SearchJewelForm;
import com.atmj.jsboot.web.validators.BuyFormValidator;

/**
 * The Class BuyController.
 */
@Controller
@SessionAttributes({ "cart" })
@RequestMapping("/aceptarcompra")
public class BuyController {

	/** The payment service. */
	@Autowired
	private PaymentService paymentService;

	/** The sale service. */
	@Autowired
	private SaleService saleService;

	@Autowired
	private BuyFormValidator validator;

	@Autowired
	private ModelMapper mapper;

	/**
	 * este caso es para cuando el cliente es nuevo.
	 * 
	 * @param buyform the buyform
	 * @param result  the result
	 * @return the model and view
	 */
	@GetMapping
	public ModelAndView add(BuyForm buyform, @ModelAttribute("cart") List<JewelEntity> cart, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.FORMSEARCH, new SearchJewelForm());
		validator.validate(buyform, result);
		if (result.hasErrors()) {
			Addresses addresses = saleService.searchAddressByClient(buyform.getNif());
			if (addresses != null) {
				model.addObject("addressesbilling", addresses.getAddressesBilling());
				model.addObject("addressesmailing", addresses.getAddressesMailing());
				model.setViewName("chooseAddress");
			} else {
				model.setViewName(ConstantsViews.FORMSALE);
			}
			model.addObject("buyForm", buyform);
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActiveFalse());
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
			sale.setPayment(mapper.map(payment, Payment.class));
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
			model.addObject(ConstantsViews.JEWELS, cart);
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