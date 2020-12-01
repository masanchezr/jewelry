package com.je.services.tests;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.PlaceEntity;
import com.je.services.adjustments.AdjustmentService;
import com.je.utils.date.DateUtil;

import junit.framework.Assert;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
class AdjustmentServiceTest {

	@Autowired
	private AdjustmentService adjustmentservice;

	@Test
	void findByIdTest() {
		Assert.assertNotNull(adjustmentservice.findById(23L));
	}

	@Test
	void sumAdjustmentByDatesTest() {
		PlaceEntity place = new PlaceEntity();
		String sfrom = "20/01/2019";
		String suntil = "01/12/2020";
		place.setIdplace(24002L);
		Date from = DateUtil.getDate(sfrom);
		Date until = DateUtil.getDate(suntil);
		Assert.assertNotNull(adjustmentservice.sumAdjustmentByDates(from, until, place));
	}
}
