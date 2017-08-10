package com.je.services.payment;

import java.util.List;

import com.je.dbaccess.entities.PaymentEntity;

/**
 * The Interface PaymentService.
 */
public interface PaymentService {

	/**
	 * Save.
	 *
	 * @param payment
	 *            the payment
	 */
	void save(PaymentEntity payment);

	/**
	 * Find all active.
	 *
	 * @return the list
	 */
	public List<PaymentEntity> findAllActive();

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public Iterable<PaymentEntity> findAll();

	public List<PaymentEntity> findAllActiveFalse();
}
