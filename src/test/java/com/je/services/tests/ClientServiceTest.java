package com.je.services.tests;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.services.clients.Client;
import com.je.services.clients.ClientService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class ClientServiceTest {

	@Autowired
	private ClientService clientservice;

	@Test
	public void searchClientTest() {
		Client client = new Client();
		client.setName("Mario");
		List<Client> clients = clientservice.searchClients(client);
		Iterator<Client> iclients = clients.iterator();
		while (iclients.hasNext()) {
			System.out.println(iclients.next().getNif());
		}
	}

}
