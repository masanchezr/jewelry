package com.atmj.jsboot.dbaccess.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.ClientEntity;
import com.atmj.jsboot.dbaccess.repositories.UserRepository;

/**
 * The Class UsersManagerImpl.
 */
@Service
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

	@Override
	public List<ClientEntity> findAll() {
		return iterableToList(userRepository.findAll());
	}

	/**
	 * Iterable to list.
	 * 
	 * @param clients the clients
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