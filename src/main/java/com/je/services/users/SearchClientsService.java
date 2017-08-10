package com.je.services.users;

import java.util.List;

/**
 * The Interface SearchClientsService.
 */
public interface SearchClientsService {

	/**
	 * Gets the data clients.
	 *
	 * @return the data clients
	 */
	public List<Client> getDataClients();

	/**
	 * Gets the client.
	 *
	 * @param nif
	 *            the nif
	 * @return the client
	 */
	public Client getClient(String nif);
}
