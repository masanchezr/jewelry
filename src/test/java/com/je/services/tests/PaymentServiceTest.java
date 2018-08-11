package com.je.services.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.services.payment.PaymentService;

/**
 * The Class PaymentServiceTest.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class PaymentServiceTest {

	/** The payment service. */
	@Autowired
	PaymentService paymentService;

	/**
	 * Search all payments actives.
	 */
	@Test
	public void searchAllPaymentsActives() {
		paymentService.findAllActive();
	}

}
