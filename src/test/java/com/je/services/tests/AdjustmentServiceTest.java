package com.je.services.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.services.adjustments.AdjustmentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class AdjustmentServiceTest {

	@Autowired
	private AdjustmentService adjustmentservice;

	@Test
	public void findByIdTest() {
		System.out.println(adjustmentservice.findById(23L));
	}

}
