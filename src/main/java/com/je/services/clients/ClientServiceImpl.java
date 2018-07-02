package com.je.services.clients;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.ClientPawnEntity;
import com.je.dbaccess.repositories.ClientPawnsRepository;
import com.je.utils.string.Util;

public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientPawnsRepository clientpawnsrepository;

	@Autowired
	private Mapper mapper;

	public List<Client> searchClients(Client client) {
		List<Client> clients = new ArrayList<>();
		List<ClientPawnEntity> lclients = null;
		ClientPawnEntity entity;
		String name = client.getName();
		String surname = client.getSurname();
		String nif = client.getNif();
		if (!Util.isEmpty(nif)) {
			entity = clientpawnsrepository.findById(nif).orElse(null);
			if (entity != null) {
				clients.add(mapper.map(entity, Client.class));
			}
		}
		if (!Util.isEmpty(name)) {
			lclients = clientpawnsrepository.findByNameLike(name);
		}
		if (!Util.isEmpty(surname)) {
			lclients = clientpawnsrepository.findBySurnameLike(surname);
		}
		if (lclients != null) {
			Iterator<ClientPawnEntity> iclients = lclients.iterator();
			while (iclients.hasNext()) {
				clients.add(mapper.map(iclients.next(), Client.class));
			}
		}
		return clients;
	}

}
