package com.je.dbaccess.test;

import java.util.Date;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.repositories.MetalRepository;

/**
 * The Class MetalRepositoryTest.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class MetalRepositoryTest {

	/** The material repository. */
	@Autowired
	private MetalRepository materialRepository;

	/**
	 * Save test.
	 */
	@Test
	public void saveTest() {
		MetalEntity material = new MetalEntity();
		material.setIdmetal(4L);
		material.setCreationdate(new Date());
		material.setDescription("ORO 20K");
		// materialRepository.save(material);
	}

	/**
	 * Find all test.
	 */
	@Test
	public void findAllTest() {
		Iterable<MetalEntity> materials = materialRepository.findAll();
		if (materials != null) {
			Iterator<MetalEntity> imaterials = materials.iterator();
			MetalEntity material;
			while (imaterials.hasNext()) {
				material = imaterials.next();
				System.out.println("metal:" + material.getIdmetal() + " description:" + material.getDescription());
			}
		}
	}
}
