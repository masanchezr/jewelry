package com.je.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.je.dbaccess.entities.JewelEntity;
import com.je.services.jewels.JewelService;

@Controller
@SessionAttributes({ "cart" })
public class CartController {

	/** The jewel service. */
	@Autowired
	private JewelService jewelService;

	/**
	 * The shopping cart (list of products) is stored in session. Simply inject
	 * it using method argument
	 */
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute JewelEntity product, @ModelAttribute("cart") List<JewelEntity> cart) {
		if (cart == null) {
			cart = new ArrayList<JewelEntity>();
		}
		product = jewelService.selectProduct(product.getIdjewel());
		cart.add(product);
		// totalprice = totalprice + product.getPrice();
		return "redirect:/productoSeleccionado" + product.getIdjewel();
	}
}
