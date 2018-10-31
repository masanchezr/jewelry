package com.je.dbaccess.test;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.SalePostponedEntity;
import com.je.dbaccess.repositories.SalesPostponedRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class SalesPostponedRepositoryTest {

	@Autowired
	private SalesPostponedRepository salespostponedrepository;

	@Test
	public void findFirstByOrderByIdsalepostponedDesc() {
		SalePostponedEntity sale = salespostponedrepository.findFirstByOrderByIdsalepostponedDesc();
		System.out.println(sale.getIdsalepostponed());
	}

	@Test
	public void findFistByOrderByIdsalepostponed() {
		SalePostponedEntity sale = salespostponedrepository.findFirstByOrderByIdsalepostponed();
		System.out.println(sale.getIdsalepostponed());
	}

	@Test
	public void sumDateretiredBetweenAndPlaceTest() {
		Calendar c = Calendar.getInstance();
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(9004L);
		c.set(2015, 4, 1);
		salespostponedrepository.sumDateretiredBetweenAndPlace(c.getTime(), new Date(), place);
	}

}
