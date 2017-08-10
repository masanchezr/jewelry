package com.je.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.services.payment.PaymentService;

/**
 * The Class PaymentsController.
 */
@Controller
public class PaymentsController {

	/** The payment service. */
	@Autowired
	private PaymentService paymentService;

	/**
	 * Save payment.
	 *
	 * @param payment
	 *            the payment
	 * @return the string
	 */
	@RequestMapping(value = "/savePayment")
	public ModelAndView savePayment(@ModelAttribute("payment") PaymentEntity payment) {
		ModelAndView model = new ModelAndView("success");
		model.addObject("adminForm", new AdminForm());
		paymentService.save(payment);
		return model;
	}

	/**
	 * Allpayments.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/allpayments")
	public ModelAndView allpayments() {
		ModelAndView model = new ModelAndView("allpayments");
		Iterable<PaymentEntity> payments = paymentService.findAll();
		model.addObject("adminForm", new AdminForm());
		model.addObject("payments", payments);
		return model;
	}
}
