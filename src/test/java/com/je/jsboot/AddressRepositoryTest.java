package com.je.jsboot;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.dbaccess.entities.AddressEntity;
import com.je.jsboot.dbaccess.entities.ClientEntity;
import com.je.jsboot.dbaccess.repositories.AddressRepository;

/**
 * The Class AddressRepositoryTest.
 */
@SpringBootTest
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
