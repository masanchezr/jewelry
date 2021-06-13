package com.je.jsboot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.services.shoppings.QuarterMetal;
import com.je.jsboot.services.shoppings.ShoppingService;

/**
 * The Class ShoppingServiceTest.
 */
@SpringBootTest
class ShoppingServiceTest {

	/** The shopping service. */
	@Autowired
	private ShoppingService shoppingService;

	/**
	 * Find shop by pk test.
	 */
	@Test
	void findShopByPKTest() {
		assertNotNull(shoppingService.findShopByPK(4L));
	}

	@Test
	void searchGramsByMetalTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(13700L);
		List<QuarterMetal> lqm = shoppingService.searchGramsByMetal("01-01-2017", "31-03-2017", place);
		assertNotNull(lqm.iterator());
	}
}
