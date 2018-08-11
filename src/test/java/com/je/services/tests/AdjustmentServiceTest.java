package com.je.services.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.services.adjustments.AdjustmentService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class AdjustmentServiceTest {

	@Autowired
	private AdjustmentService adjustmentservice;

	@Test
	public void findByIdTest() {
		System.out.println(adjustmentservice.findById(23L));
	}

}
