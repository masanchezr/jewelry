package com.je.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.je.dbaccess.entities.JewelEntity;
import com.je.forms.Jewel;
import com.je.services.jewels.JewelService;

@Controller
@SessionAttributes({ "cart" })
public class CartController {

	/** The jewel service. */
	@Autowired
	private JewelService jewelService;

	/**
	 * The shopping cart (list of products) is stored in session. Simply inject it
	 * using method argument
	 */
	@PostMapping(value = "/addProduct")
	public String addProduct(@ModelAttribute Jewel product, @ModelAttribute("cart") List<JewelEntity> cart) {
		if (cart == null) {
			cart = new ArrayList<>();
		}
		cart.add(jewelService.selectProduct(product.getIdjewel()));
		return "redirect:/productoSeleccionado" + product.getIdjewel();
	}

	@PostMapping("/goodbye")
	public String goodbye(SessionStatus status) {
		status.setComplete();
		return "goodbye";
	}
}