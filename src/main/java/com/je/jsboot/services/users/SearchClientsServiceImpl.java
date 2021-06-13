package com.je.jsboot.services.users;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.je.jsboot.dbaccess.entities.ClientEntity;
import com.je.jsboot.dbaccess.managers.UsersManager;

/**
 * The Class SearchClientsServiceImpl.
 */
@Service
public class SearchClientsServiceImpl implements SearchClientsService {

	/** The users manager. */
	@Autowired
	private UsersManager usersManager;

	/** The mapper. */
	@Autowired
	private Mapper mapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.je.jsboot.services.users.SearchClientsService#getDataClients()
	 */
	public List<Client> getDataClients() {
		List<ClientEntity> clients = usersManager.findAll();
		return mapperListObjects(clients);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.je.jsboot.services.users.SearchClientsService#getClient(java.lang.String)
	 */
	public Client getClient(String nif) {
		Client client = new Client();
		mapper.map(usersManager.findById(nif), client);
		return client;
	}

	/**
	 * Mapper list objects.
	 * 
	 * @param clients the clients
	 * @return the list
	 */
	private List<Client> mapperListObjects(List<ClientEntity> clients) {
		List<Client> lclients = null;
		if (clients != null) {
			lclients = new ArrayList<>();
			Iterator<ClientEntity> iclients = clients.iterator();
			while (iclients.hasNext()) {
				Client client = new Client();
				mapper.map(iclients.next(), client);
				lclients.add(client);
			}
		}
		return lclients;
	}

}