package com.je.jsboot.admin.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.jsboot.admin.forms.AdminForm;
import com.je.jsboot.dbaccess.entities.PaymentEntity;
import com.je.jsboot.forms.Payment;
import com.je.jsboot.services.payment.PaymentService;
import com.je.jsboot.utils.constants.ConstantsViews;

/**
 * The Class PaymentsController.
 */
@Controller
public class PaymentsController {

	/** The payment service. */
	@Autowired
	private PaymentService paymentService;

	@Autowired
	private ModelMapper mapper;

	/**
	 * Save payment.
	 *
	 * @param payment the payment
	 * @return the string
	 */
	@PostMapping("/employee/savePayment")
	public ModelAndView savePayment(@ModelAttribute(ConstantsViews.FORMPAYMENT) Payment payment) {
		ModelAndView model = new ModelAndView("admin/success");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		paymentService.save(mapper.map(payment, PaymentEntity.class));
		return model;
	}

	/**
	 * Allpayments.
	 *
	 * @return the model and view
	 */
	@GetMapping("/allpayments")
	public ModelAndView allpayments() {
		ModelAndView model = new ModelAndView("admin/payments/allpayments");
		Iterable<PaymentEntity> payments = paymentService.findAll();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.PAYMENTS, payments);
		return model;
	}
}
