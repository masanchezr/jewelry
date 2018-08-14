package com.je.services.tests;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.entities.ObjectShopEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.services.shoppings.QuarterMetal;
import com.je.services.shoppings.Shopping;
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
		shoppingService.findShopByPK(4L);
	}

	@Test
	public void saveShoppingTest() {
		Shopping shopping = new Shopping();
		shopping.setCreationdate("02-03-2017");
		shopping.setAmount("85");
		shopping.setNumshop(9L);
		shopping.setUser("13700");
		shopping.setNif("04189105T");
		shopping.setAddress("Leon");
		shopping.setName("Ainhoa");
		shopping.setSurname("Gonzalez Gonzalez");
		shopping.setNumshop(907L);
		List<ObjectShopEntity> los = new ArrayList<ObjectShopEntity>();
		ObjectShopEntity os = new ObjectShopEntity();
		MetalEntity material = new MetalEntity();
		material.setIdmetal(1L);
		os.setAmount(BigDecimal.valueOf(85));
		os.setGrossgrams(BigDecimal.valueOf(5));
		os.setMetal(material);
		los.add(os);
		shopping.setObjects(los);
		// shoppingService.save(shopping);
	}

	@Test
	public void generateExcelTest() {
		shoppingService.generateExcel("16-03-2017", "20-03-2018");
	}

	@Test
	public void searchGramsByMetalTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(13700L);
		List<QuarterMetal> lqm = shoppingService.searchGramsByMetal("01-01-2017", "31-03-2017", place);
		lqm.iterator();
	}
}
