package com.atmj.jsboot.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.services.salesrepeated.SearchSaleRepeatedService;

@SpringBootTest
class SearchSaleRepeatedServiceTest {

	@Autowired
	private SearchSaleRepeatedService searchSaleRepeatedService;

	@Test
	void isSaleRepeatedTest() {
		Assertions.assertEquals(false, searchSaleRepeatedService.isNotRepeatSale(1L, 2025));
	}
}
