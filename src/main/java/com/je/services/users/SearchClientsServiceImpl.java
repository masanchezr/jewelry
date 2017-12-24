package com.je.services.users;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.ClientEntity;
import com.je.dbaccess.managers.UsersManager;

/**
 * The Class SearchClientsServiceImpl.
 */
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
	 * @see com.je.services.users.SearchClientsService#getDataClients()
	 */
	public List<Client> getDataClients() {
		List<ClientEntity> clients = usersManager.findAll();
		return mapperListObjects(clients);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.je.services.users.SearchClientsService#getClient(java.lang.String)
	 */
	public Client getClient(String nif) {
		Client client = new Client();
		mapper.map(usersManager.findById(nif), client);
		return client;
	}

	/**
	 * Mapper list objects.
	 * 
	 * @param clients
	 *            the clients
	 * @return the list
	 */
	private List<Client> mapperListObjects(List<ClientEntity> clients) {
		List<Client> lclients = null;
		if (clients != null) {
			lclients = new ArrayList<Client>();
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