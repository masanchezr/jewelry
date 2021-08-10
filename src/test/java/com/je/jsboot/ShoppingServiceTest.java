package com.je.jsboot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.dbaccess.entities.MetalEntity;
import com.je.jsboot.dbaccess.entities.NationEntity;
import com.je.jsboot.dbaccess.entities.ObjectShopEntity;
import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.entities.TrackEntity;
import com.je.jsboot.services.shoppings.QuarterMetal;
import com.je.jsboot.services.shoppings.Shopping;
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

	@Test
	void saveShoppingTest() {
		Shopping shop = new Shopping();
		TrackEntity track = new TrackEntity();
		NationEntity nation = new NationEntity();
		ObjectShopEntity os = new ObjectShopEntity();
		MetalEntity metal = new MetalEntity();
		List<ObjectShopEntity> los = new ArrayList<>();
		metal.setIdmetal(1L);
		os.setMetal(metal);
		os.setAmount(new BigDecimal(43));
		os.setGrossgrams(new BigDecimal(2));
		os.setNetgrams(new BigDecimal(2));
		los.add(os);
		nation.setIdnation(34L);
		track.setIdtrack(13L);
		shop.setUser("24002");
		shop.setNumshop(shoppingService.getNextNumber("24002"));
		shop.setAmount("43");
		shop.setAddress("C/Santa Ana 8, 1º A");
		shop.setName("María de los Ángeles");
		shop.setNation(nation);
		shop.setTown("La Solana");
		shop.setNif("71222013Y");
		shop.setSurname("Sánchez Ruiz-Romano");
		shop.setTrack(track);
		shop.setObjects(los);
		shop.setCashamount("43");
		assertNotNull(shoppingService.save(shop));
	}
}
