package com.je.services.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.PlaceEntity;
import com.je.services.shoppings.QuarterMetal;
import com.je.services.shoppings.ShoppingService;

/**
 * The Class ShoppingServiceTest.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class ShoppingServiceTest {

	/** The shopping service. */
	@Autowired
	private ShoppingService shoppingService;

	/**
	 * Find shop by pk test.
	 */
	@Test
	public void findShopByPKTest() {
		assertNotNull(shoppingService.findShopByPK(4L));
	}

	@Test
	public void searchGramsByMetalTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(13700L);
		List<QuarterMetal> lqm = shoppingService.searchGramsByMetal("01-01-2017", "31-03-2017", place);
		assertNotNull(lqm.iterator());
	}
}
