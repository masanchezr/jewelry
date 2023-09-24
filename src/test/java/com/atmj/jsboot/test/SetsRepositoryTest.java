package com.atmj.jsboot.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.dbaccess.repositories.SetsRepository;

/**
 * The Class SetsRepositoryTest.
 */
@SpringBootTest
class SetsRepositoryTest {

	/** The sets repository. */
	@Autowired
	private SetsRepository setsRepository;

	/**
	 * Count test.
	 */
	@Test
	void countTest() {
		Assertions.assertNotEquals(0, setsRepository.count());
	}
}
