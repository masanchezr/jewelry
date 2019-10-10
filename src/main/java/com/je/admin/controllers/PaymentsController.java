package com.je.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.services.payment.PaymentService;
import com.je.utils.constants.ConstantsJsp;

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
	 * @param payment the payment
	 * @return the string
	 */
	@PostMapping(value = "/savePayment")
	public ModelAndView savePayment(@ModelAttribute(ConstantsJsp.FORMPAYMENT) PaymentEntity payment) {
		ModelAndView model = new ModelAndView("admin/success");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		paymentService.save(payment);
		return model;
	}

	/**
	 * Allpayments.
	 *
	 * @return the model and view
	 */
	@PostMapping(value = "/allpayments")
	public ModelAndView allpayments() {
		ModelAndView model = new ModelAndView("admin/payments/allpayments");
		Iterable<PaymentEntity> payments = paymentService.findAll();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.PAYMENTS, payments);
		return model;
	}
}
