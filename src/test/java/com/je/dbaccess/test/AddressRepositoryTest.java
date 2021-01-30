package com.je.dbaccess.test;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.AddressEntity;
import com.je.dbaccess.entities.ClientEntity;
import com.je.dbaccess.repositories.AddressRepository;

/**
 * The Class AddressRepositoryTest.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
class AddressRepositoryTest {

	/** The address repository. */
	@Autowired
	private AddressRepository addressRepository;

	/**
	 * Save test.
	 */
	@Test
	void saveTest() {
		ClientEntity knowClient = new ClientEntity();
		knowClient.setNifclient("71222013Y");
		knowClient.setEmail("prueba");
		AddressEntity mailing = new AddressEntity();
		mailing.setAddress("Calle");
		mailing.setCity("Madrid");
		mailing.setCountry("España");
		mailing.setPostalcode(28017L);
		mailing.setCreationdate(new Date());
		Assertions.assertNotNull(addressRepository.save(mailing));
	}

	/**
	 * Find all test.
	 */
	@Test
	void findAllTest() {
		Assertions.assertNotNull(addressRepository.findAll());
	}
}
