package com.je.dbaccess.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.repositories.SetsRepository;

/**
 * The Class SetsRepositoryTest.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class SetsRepositoryTest {

	/** The sets repository. */
	@Autowired
	private SetsRepository setsRepository;

	/**
	 * Count test.
	 */
	@Test
	public void countTest() {
		System.out.println("Num:" + setsRepository.count());
	}
}
