package com.je.services.tests;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.entities.ObjectShopEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.services.shoppings.QuarterMetal;
import com.je.services.shoppings.Shopping;
import com.je.services.shoppings.ShoppingService;

/**
 * The Class ShoppingServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
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
	public void searchGramsByMetalTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(13700L);
		List<QuarterMetal> lqm = shoppingService.searchGramsByMetal("01-01-2015", "25-07-2015", place);
		Iterator<QuarterMetal> ilqm = lqm.iterator();
		QuarterMetal qm;
		while (ilqm.hasNext()) {
			qm = ilqm.next();
			System.out.println("grossgrams:" + qm.getGrossgrams() + " netgrams" + qm.getNetgrams() + " realgrams:"
					+ qm.getRealgrams() + " metal:" + qm.getMetal().getDescription());
		}
	}

	@Test
	public void saveShoppingTest() {
		Shopping shopping = new Shopping();
		shopping.setCreationdate("02-03-2017");
		shopping.setTotalamount(new BigDecimal(85));
		shopping.setNumshop(9L);
		shopping.setUser("13700");
		/*
		 * shopping.setNif("06227087K"); shopping.setAddress("Leon");
		 * shopping.setName("Ainhoa"); shopping.setSurname("Gonzalez Gonzalez");
		 * shopping.setNationality("Española");
		 */
		List<ObjectShopEntity> los = new ArrayList<ObjectShopEntity>();
		ObjectShopEntity os = new ObjectShopEntity();
		MetalEntity metal = new MetalEntity();
		metal.setIdmetal(1L);
		os.setAmount(new BigDecimal(85));
		os.setGrossgrams(new BigDecimal(5));
		os.setMetal(metal);
		los.add(os);
		shopping.setObjects(los);
		// shoppingService.save(shopping);
	}

	@Test
	public void generateExcel() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(13700L);
		try {
			XSSFWorkbook myWorkBook = shoppingService.generateExcel("20-03-2017", "20-03-2017", place);
			File file = new File("D://workbook.xlsx");
			FileOutputStream out = new FileOutputStream(file);
			// write operation workbook using file out object
			myWorkBook.write(out);
			myWorkBook.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
