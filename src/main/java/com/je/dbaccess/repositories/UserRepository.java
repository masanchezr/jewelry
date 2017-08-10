package com.je.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.ClientEntity;

/**
 * The Interface UserRepository.
 */
public interface UserRepository extends CrudRepository<ClientEntity, String> {

	/**
	 * Find by email.
	 * 
	 * @param email
	 *            the email
	 * @return the list
	 */
	Iterable<ClientEntity> findByEmail(String email);
}
