package com.je.services.clients;

import java.util.ArrayList;
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
			entity = clientpawnsrepository.findByNameLike(name);
			if (entity != null) {
				clients.add(mapper.map(entity, Client.class));
			}
		}
		if (!Util.isEmpty(surname)) {
			entity = clientpawnsrepository.findBySurnameLike(surname);
			if (entity != null) {
				clients.add(mapper.map(entity, Client.class));
			}
		}
		return clients;
	}

}
