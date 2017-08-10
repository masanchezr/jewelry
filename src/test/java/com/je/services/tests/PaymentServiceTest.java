package com.je.services.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.services.payment.PaymentService;

/**
 * The Class PaymentServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
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
