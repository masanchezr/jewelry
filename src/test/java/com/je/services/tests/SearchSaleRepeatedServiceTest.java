package com.je.services.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.services.salesrepeated.SearchSaleRepeatedService;

import junit.framework.Assert;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
class SearchSaleRepeatedServiceTest {

	@Autowired
	private SearchSaleRepeatedService searchSaleRepeatedService;

	@Test
	void isSaleRepeatedTest() {
		Assert.assertEquals(true, searchSaleRepeatedService.isSaleRepeated(5L, 2020));
	}
}
