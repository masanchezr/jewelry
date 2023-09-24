package com.atmj.jsboot.test;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.dbaccess.entities.PaymentEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.services.adjustments.Adjustment;
import com.atmj.jsboot.services.adjustments.AdjustmentService;
import com.atmj.jsboot.utils.date.DateUtil;

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

	@Test
	void saveTest() {
		Adjustment adjustment = new Adjustment();
		PaymentEntity pay = new PaymentEntity();
		pay.setIdpayment(1L);
		adjustment.setAmount(new BigDecimal(5));
		adjustment.setDescription("prueba arreglo retirado por el cliente");
		adjustment.setIdadjustment(11L);
		adjustment.setUser("SONIAMARCOS");
		adjustment.setPayment(pay);
		Assertions.assertNotNull(adjustmentservice.save(adjustment));
	}

	@Test
	void saveWorkTest() {
		Adjustment adjustment = new Adjustment();
		PaymentEntity pay = new PaymentEntity();
		pay.setIdpayment(1L);
		adjustment.setAmount(new BigDecimal(5));
		adjustment.setDescription("prueba hechura");
		adjustment.setIdadjustment(11L);
		adjustment.setUser("24002");
		adjustment.setPayment(pay);
		Assertions.assertNotNull(adjustmentservice.saveWorkshop(adjustment));
	}
}
