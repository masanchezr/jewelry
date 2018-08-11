package com.je.dbaccess.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.ShoppingEntity;
import com.je.dbaccess.repositories.ShoppingsRepository;
import com.je.utils.date.DateUtil;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class ShoppingRepositoryTest {

	@Autowired
	private ShoppingsRepository shoppingsRepository;

	@Test
	public void findByNumshopAndPlaceAndYearTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(13700L);
		ShoppingEntity searchShopping = shoppingsRepository.findByNumshopAndPlaceAndYear(3L, place, 2015);
		if (searchShopping != null) {
			System.out.println("total:" + searchShopping.getTotalamount());
		} else {
			System.out.println("viene a nulo");
		}
	}

	@Test
	public void findGramsNullTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(13700L);
		Date datefrom = DateUtil.getDate("2015-12-01");
		List<Long> shoppings = shoppingsRepository.findGramsNull(place, datefrom, new Date());
		Iterator<Long> ishoppings = shoppings.iterator();
		while (ishoppings.hasNext()) {
			System.out.println(ishoppings.next());
		}
	}
}
