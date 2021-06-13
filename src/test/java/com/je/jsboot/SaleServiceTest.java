package com.je.jsboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.services.sales.SaleService;

/**
 * The Class SaleServiceTest.
 */
@SpringBootTest
class SaleServiceTest {

	/** The sale service. */
	@Autowired
	private SaleService saleService;

	/**
	 * Search address by client test.
	 */
	@Test
	void searchAddressByClientTest() {
		Assertions.assertNull(saleService.searchAddressByClient("71222013Y"));
	}
}
