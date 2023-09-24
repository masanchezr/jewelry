package com.atmj.jsboot.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.dbaccess.entities.PaymentEntity;
import com.atmj.jsboot.dbaccess.repositories.PaymentRepository;

/**
 * The Class PaymentRepositoryTest.
 */
@SpringBootTest
class PaymentRepositoryTest {

	/** The payment repository. */
	@Autowired
	PaymentRepository paymentRepository;

	/**
	 * Save test.
	 */
	@Test
	void saveTest() {
		PaymentEntity payment = new PaymentEntity();
		payment.setIdpayment(5L);
		payment.setName("PAYPAL");
		payment.setActive(false);
		Assertions.assertNotNull(paymentRepository.save(payment));
	}

	/**
	 * Find all test.
	 */
	@Test
	void findAllTest() {
		Assertions.assertNotNull(paymentRepository.findAll());
	}

	@Test
	void findAllActiveTest() {
		Assertions.assertNotNull(paymentRepository.findByActiveOrderByName(true));
	}
}
