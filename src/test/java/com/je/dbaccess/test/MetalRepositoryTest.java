package com.je.dbaccess.test;

import java.util.Date;
import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.repositories.MetalRepository;

/**
 * The Class MetalRepositoryTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class MetalRepositoryTest {

	/** The metal repository. */
	@Autowired
	private MetalRepository metalRepository;

	/**
	 * Save test.
	 */
	@Test
	public void saveTest() {
		MetalEntity metal = new MetalEntity();
		metal.setIdmetal(4L);
		metal.setCreationdate(new Date());
		metal.setDescription("ORO 20K");
		// metalRepository.save(metal);
	}

	/**
	 * Find all test.
	 */
	@Test
	public void findAllTest() {
		Iterable<MetalEntity> metals = metalRepository.findAll();
		if (metals != null) {
			Iterator<MetalEntity> imetals = metals.iterator();
			MetalEntity metal;
			while (imetals.hasNext()) {
				metal = imetals.next();
				System.out.println("idmetal:" + metal.getIdmetal()
						+ " description:" + metal.getDescription());
			}
		}
	}
}
