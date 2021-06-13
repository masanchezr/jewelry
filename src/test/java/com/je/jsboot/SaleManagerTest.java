package com.je.jsboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.dbaccess.entities.ClientEntity;
import com.je.jsboot.dbaccess.managers.SaleManager;

/**
 * The Class SaleManagerTest.
 */
@SpringBootTest
class SaleManagerTest {

	/** The sale manager. */
	@Autowired
	private SaleManager saleManager;

	/**
	 * Search address by client.
	 */
	@Test
	void searchAddressByClient() {
		ClientEntity client = new ClientEntity();
		client.setNifclient("71222013Y");
		Assertions.assertNull(saleManager.searchAddressByClient(client));
	}
}
