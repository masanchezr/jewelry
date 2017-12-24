package com.je.dbaccess.managers;

import java.util.List;

import com.je.dbaccess.entities.ClientEntity;

/**
 * The Interface UsersManager.
 */
public interface UsersManager {

	/**
	 * Find by email.
	 * 
	 * @param email
	 *            the email
	 * @return the list
	 */
	public List<ClientEntity> findByEmail(String email);

	/**
	 * Find one.
	 * 
	 * @param nifclient
	 *            the nifclient
	 * @return the client entity
	 */
	public ClientEntity findById(String nifclient);

	/**
	 * Save.
	 * 
	 * @param userEntity
	 *            the user entity
	 */
	public void save(ClientEntity userEntity);

	/**
	 * Find all.
	 * 
	 * @return the list
	 */
	public List<ClientEntity> findAll();

}
