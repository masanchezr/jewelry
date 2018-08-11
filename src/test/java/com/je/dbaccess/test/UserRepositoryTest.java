package com.je.dbaccess.test;

import java.util.Date;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.ClientEntity;
import com.je.dbaccess.repositories.UserRepository;

/**
 * The Class UserRepositoryTest.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class UserRepositoryTest {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Save test.
	 */
	@Test
	public void saveTest() {
		ClientEntity user = new ClientEntity();
		user.setEmail("prueba");
		user.setName("maria");
		// user.setSurname("fdafsafa");
		user.setNifclient("000006461K");
		user.setCreationdate(new Date());
		// userRepository.save(user);
	}

	/**
	 * Find one test.
	 */
	@Test
	public void findByIdTest() {
		userRepository.findById("70000000K");
	}

	/**
	 * Find all.
	 */
	@Test
	public void findAll() {
		Iterable<ClientEntity> clients = userRepository.findAll();
		if (clients != null) {
			Iterator<ClientEntity> iclients = clients.iterator();
			if (iclients != null) {
				while (iclients.hasNext()) {
					ClientEntity client = iclients.next();
					System.out.println("nif:" + client.getNifclient() + " fecha creacion: "
							+ client.getCreationdate().toString() + " email:" + client.getEmail());
				}
			}
		}

	}
}
