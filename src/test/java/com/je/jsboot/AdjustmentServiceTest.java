package com.je.jsboot;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.services.adjustments.AdjustmentService;
import com.je.jsboot.utils.date.DateUtil;

@SpringBootTest
class AdjustmentServiceTest {

	@Autowired
	private AdjustmentService adjustmentservice;

	@Test
	void findByIdTest() {
		Assertions.assertNotNull(adjustmentservice.findById(23L));
	}

	@Test
	void sumAdjustmentByDatesTest() {
		PlaceEntity place = new PlaceEntity();
		String sfrom = "20/01/2019";
		String suntil = "01/12/2020";
		place.setIdplace(24002L);
		Date from = DateUtil.getDate(sfrom);
		Date until = DateUtil.getDate(suntil);
		Assertions.assertNotNull(adjustmentservice.sumAdjustmentByDates(from, until, place));
	}
}
