package com.atmj.jsboot.test;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.dbaccess.entities.ClientEntity;
import com.atmj.jsboot.dbaccess.repositories.UserRepository;

/**
 * The Class UserRepositoryTest.
 */
@SpringBootTest
class UserRepositoryTest {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Save test.
	 */
	@Test
	void saveTest() {
		ClientEntity user = new ClientEntity();
		user.setEmail("prueba");
		user.setName("maria");
		user.setNifclient("000006461K");
		user.setCreationdate(new Date());
		Assertions.assertNotNull(userRepository.save(user));
	}

	/**
	 * Find one test.
	 */
	@Test
	void findByIdTest() {
		Assertions.assertNotNull(userRepository.findById("70000000K"));
	}

	/**
	 * Find all.
	 */
	@Test
	void findAll() {
		Assertions.assertNotNull(userRepository.findAll());
	}
}
