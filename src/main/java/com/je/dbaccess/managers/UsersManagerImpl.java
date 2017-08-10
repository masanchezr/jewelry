package com.je.dbaccess.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.ClientEntity;
import com.je.dbaccess.repositories.UserRepository;

/**
 * The Class UsersManagerImpl.
 */
public class UsersManagerImpl implements UsersManager {

	/** The user repository. */
	@Autowired
	UserRepository userRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.je.dbaccess.managers.UsersManager#findByEmail(java.lang.String)
	 */
	public List<ClientEntity> findByEmail(String email) {
		return iterableToList(userRepository.findByEmail(email));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.je.dbaccess.managers.UsersManager#findOne(java.lang.String)
	 */
	public ClientEntity findOne(String nifclient) {
		return userRepository.findOne(nifclient);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.je.dbaccess.managers.UsersManager#save(com.je.dbaccess.entities.
	 * ClientEntity)
	 */
	public void save(ClientEntity userEntity) {
		userRepository.save(userEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.je.dbaccess.managers.UsersManager#findAll()
	 */
	public List<ClientEntity> findAll() {
		return iterableToList(userRepository.findAll());
	}

	/**
	 * Iterable to list.
	 * 
	 * @param clients
	 *            the clients
	 * @return the list
	 */
	private List<ClientEntity> iterableToList(Iterable<ClientEntity> clients) {
		if (clients != null) {
			List<ClientEntity> lclients = new ArrayList<ClientEntity>();
			Iterator<ClientEntity> iclients = clients.iterator();
			while (iclients.hasNext()) {
				lclients.add(iclients.next());
			}
			return lclients;
		} else {
			return null;
		}
	}
}