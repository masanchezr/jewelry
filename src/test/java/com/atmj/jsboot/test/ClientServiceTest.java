package com.atmj.jsboot.test;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.services.clients.Client;
import com.atmj.jsboot.services.clients.ClientService;

@SpringBootTest
public class ClientServiceTest {

	@Autowired
	private ClientService clientservice;

	@Test
	public void searchClientTest() {
		Client client = new Client();
		client.setName("Teresa");
		List<Client> clients = clientservice.searchClients(client);
		Iterator<Client> iclients = clients.iterator();
		while (iclients.hasNext()) {
			System.out.println(iclients.next().getNif());
		}
	}

}
