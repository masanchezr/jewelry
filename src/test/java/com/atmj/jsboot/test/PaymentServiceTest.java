package com.atmj.jsboot.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.services.payment.PaymentService;

/**
 * The Class PaymentServiceTest.
 */
@SpringBootTest
class PaymentServiceTest {

	/** The payment service. */
	@Autowired
	PaymentService paymentService;

	/**
	 * Search all payments actives.
	 */
	@Test
	void searchAllPaymentsActives() {
		Assertions.assertNotNull(paymentService.findAllActive());
	}

}
