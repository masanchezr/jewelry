package com.je.dbaccess.test;

import java.util.Date;
import java.util.Iterator;

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
public class AddressRepositoryTest {

	/** The address repository. */
	@Autowired
	private AddressRepository addressRepository;

	/**
	 * Save test.
	 */
	@Test
	public void saveTest() {
		ClientEntity knowClient = new ClientEntity();
		knowClient.setNifclient("71222013Y");
		knowClient.setEmail("prueba");
		AddressEntity mailing = new AddressEntity();
		mailing.setAddress("Calle");
		mailing.setCity("Madrid");
		mailing.setCountry("España");
		mailing.setPostalcode(28017L);
		mailing.setCreationdate(new Date());
		// addressRepository.save(mailing);
	}

	/**
	 * Find all test.
	 */
	@Test
	public void findAllTest() {
		Iterable<AddressEntity> iaddresses = addressRepository.findAll();
		if (iaddresses != null) {
			Iterator<AddressEntity> itaddress = iaddresses.iterator();
			if (itaddress != null) {
				AddressEntity address;
				while (itaddress.hasNext()) {
					address = itaddress.next();
					System.out.println("Direccion: " + address.getAddress() + " id:" + address.getIdaddress());
				}
			}
		}
	}
}
