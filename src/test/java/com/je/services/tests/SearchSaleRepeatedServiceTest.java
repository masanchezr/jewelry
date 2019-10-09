package com.je.services.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.services.salesrepeated.SearchSaleRepeatedService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class SearchSaleRepeatedServiceTest {

	@Autowired
	private SearchSaleRepeatedService searchSaleRepeatedService;

	@Test
	public void isSaleRepeatedTest() {
		System.out.print("Hay ventas repetidas?" + searchSaleRepeatedService.isSaleRepeated(5L));
	}
}
