package com.je.dbaccess.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.repositories.SetsRepository;

/**
 * The Class SetsRepositoryTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
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
