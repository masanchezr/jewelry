package com.je.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.je.dbaccess.entities.JewelEntity;
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
	@GetMapping("/addProduct/{id}")
	public String addProduct(@PathVariable Long id, @ModelAttribute("cart") List<JewelEntity> cart,
			RedirectAttributes attributes) {
		cart.add(jewelService.selectProduct(id));
		attributes.addFlashAttribute("cart", cart);
		return "redirect:/productoSeleccionado" + id;
	}

	@GetMapping("/goodbye")
	public String goodbye(SessionStatus status) {
		status.setComplete();
		return "goodbye";
	}
}