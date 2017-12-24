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

	@Override
	public List<ClientEntity> findByEmail(String email) {
		return iterableToList(userRepository.findByEmail(email));
	}

	@Override
	public ClientEntity findById(String nifclient) {
		return userRepository.findById(nifclient).get();
	}

	@Override
	public void save(ClientEntity userEntity) {
		userRepository.save(userEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.je.dbaccess.managers.UsersManager#findAll()
	 */
	@Override
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