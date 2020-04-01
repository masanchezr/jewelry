package com.je.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.je.services.mails.MailService;
import com.je.web.forms.MessageForm;
import com.je.web.validators.MessageFormValidator;

@Controller
@SessionAttributes({ "cart" })
public class ContactController {

	@Autowired
	private MessageFormValidator messageformvalidator;

	@PostMapping("/sendMessage")
	public ModelAndView sendMessage(@ModelAttribute("messageForm") MessageForm message, BindingResult result) {
		ModelAndView model = new ModelAndView();
		messageformvalidator.validate(message, result);
		if (result.hasErrors()) {

		} else {
			MailService mailService = new MailService(message.getMessage().concat(message.getEmail()), "Nuevo contacto",
					null);
			mailService.start();
		}
		return model;
	}

}
