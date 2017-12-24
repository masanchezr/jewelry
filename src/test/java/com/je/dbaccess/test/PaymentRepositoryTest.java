package com.je.dbaccess.test;

import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.repositories.PaymentRepository;

/**
 * The Class PaymentRepositoryTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class PaymentRepositoryTest {

	/** The payment repository. */
	@Autowired
	PaymentRepository paymentRepository;

	/**
	 * Save test.
	 */
	@Test
	public void saveTest() {
		PaymentEntity payment = new PaymentEntity();
		payment.setIdpayment(5L);
		payment.setName("PAYPAL");
		payment.setActive(false);
		// paymentRepository.save(payment);
	}

	/**
	 * Find all test.
	 */
	@Test
	public void findAllTest() {
		Iterable<PaymentEntity> payments = paymentRepository.findAll();
		if (payments != null) {
			Iterator<PaymentEntity> ipayments = payments.iterator();
			if (ipayments != null) {
				while (ipayments.hasNext()) {
					PaymentEntity payment = ipayments.next();
					System.out.println("idpayment:" + payment.getIdpayment() + " payment name:" + payment.getName()
							+ " active: " + payment.isActive());
				}
			}
		}
	}

	@Test
	public void findAllActiveTest() {
		Iterable<PaymentEntity> payments = paymentRepository.findByActiveOrderByName(true);
		if (payments != null) {
			Iterator<PaymentEntity> ipayments = payments.iterator();
			if (ipayments != null) {
				while (ipayments.hasNext()) {
					PaymentEntity payment = ipayments.next();
					System.out.println("idpayment:" + payment.getIdpayment() + " payment name:" + payment.getName()
							+ " active: " + payment.isActive());
				}
			}
		}
	}
}
