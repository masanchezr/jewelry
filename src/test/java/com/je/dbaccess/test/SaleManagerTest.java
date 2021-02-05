package com.je.dbaccess.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.ClientEntity;
import com.je.dbaccess.managers.SaleManager;

import junit.framework.Assert;

/**
 * The Class SaleManagerTest.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
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
		Assert.assertNull(saleManager.searchAddressByClient(client));
	}
}
