package com.je.services.payment;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.repositories.PaymentRepository;

/**
 * The Class PaymentServiceImpl.
 */
public class PaymentServiceImpl implements PaymentService {

	/** The payment repository. */
	@Autowired
	public PaymentRepository paymentRepository;

	/** The mapper. */
	@Autowired
	Mapper mapper;

	public void save(PaymentEntity payment) {
		paymentRepository.save(payment);

	}

	/**
	 * Find all.
	 * 
	 * @return the list
	 */
	public Iterable<PaymentEntity> findAll() {
		return paymentRepository.findAll();
	}

	public List<PaymentEntity> findAllActive() {
		return paymentRepository.findByActiveOrderByName(true);
	}

	public List<PaymentEntity> findAllActiveFalse() {
		return paymentRepository.findByActiveOrderByName(false);
	}

}
