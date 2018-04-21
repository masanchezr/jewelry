package com.je.dbaccess.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.entities.SalePostponedEntity;
import com.je.dbaccess.repositories.SalesPostponedRepository;

@RunWith(SpringJUnit4ClassRunner.class)
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

}
