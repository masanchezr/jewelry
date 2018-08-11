package com.je.dbaccess.test;

import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.repositories.PaymentRepository;

/**
 * The Class PaymentRepositoryTest.
 */
@ExtendWith(SpringExtension.class)
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
