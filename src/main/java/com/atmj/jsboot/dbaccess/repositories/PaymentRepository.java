package com.atmj.jsboot.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.atmj.jsboot.dbaccess.entities.PaymentEntity;

/**
 * The Interface PaymentRepository.
 */
public interface PaymentRepository extends CrudRepository<PaymentEntity, Long> {

	/**
	 * Find by active.
	 *
	 * @param b
	 *            the b
	 * @return the iterable
	 */
	public List<PaymentEntity> findByActiveOrderByName(boolean b);
}
