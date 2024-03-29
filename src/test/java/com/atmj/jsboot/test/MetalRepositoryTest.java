package com.atmj.jsboot.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.dbaccess.repositories.MetalRepository;

/**
 * The Class MetalRepositoryTest.
 */
@SpringBootTest
class MetalRepositoryTest {

	/** The material repository. */
	@Autowired
	private MetalRepository materialRepository;

	/**
	 * Find all test.
	 */
	@Test
	void findAllTest() {
		Assertions.assertNotNull(materialRepository.findAll());
	}
}
