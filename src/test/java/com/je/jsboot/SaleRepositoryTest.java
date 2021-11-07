package com.je.jsboot;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.dbaccess.entities.ClientEntity;
import com.je.jsboot.dbaccess.entities.PaymentEntity;
import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.repositories.SalesRepository;

/**
 * The Class SaleRepositoryTest.
 */
@SpringBootTest

class SaleRepositoryTest {

	/** The sale repository. */
	@Autowired
	private SalesRepository saleRepository;

	/**
	 * Find by client.
	 */
	@Test
	void findByClient() {
		ClientEntity client = new ClientEntity();
		client.setNifclient("71222013Y");
		Assertions.assertNotNull(saleRepository.findByClient(client));
	}

	@Test
	void findByCreationdateBetweenPayTest() {
		PaymentEntity pay = new PaymentEntity();
		Calendar c = Calendar.getInstance();
		c.set(2021, 11, 1);
		pay.setIdpayment(3L);
		Assertions.assertNotNull(saleRepository.findByCreationdateBetweenPay(c.getTime(), new Date(), pay));
	}

	@Test
	void findByNumsaleLessThanTest() {
		PlaceEntity place = new PlaceEntity();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 6);
		place.setIdplace(28017L);
		Assertions.assertNotNull(
				saleRepository.findByPlaceAndCreationdateBeforeAndNumsaleLessThan(place, c.getTime(), 0L));
	}
}