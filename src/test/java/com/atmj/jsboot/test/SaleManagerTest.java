package com.atmj.jsboot.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.dbaccess.entities.ClientEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.managers.SaleManager;

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

	@Test
	void calculateNumberMissingTest() {
		PlaceEntity place = new PlaceEntity();
		List<Long> numbers;
		place.setIdplace(24002L);
		numbers = saleManager.calculateNumberMissing(4701L, 5593L, place);
		System.out.println(numbers.toString());
	}
}
