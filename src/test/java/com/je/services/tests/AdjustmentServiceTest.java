package com.je.services.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.PaymentEntity;
import com.je.services.adjustments.Adjustment;
import com.je.services.adjustments.AdjustmentService;

import junit.framework.Assert;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class AdjustmentServiceTest {

	@Autowired
	private AdjustmentService adjustmentservice;

	@Test
	public void findByIdTest() {
		Assert.assertNotNull(adjustmentservice.findById(23L));
	}

	@Test
	public void saveTest() {
		Adjustment adjustment = new Adjustment();
		PaymentEntity pay = new PaymentEntity();
		pay.setIdpayment(2L);
		adjustment.setAmount("56,58");
		adjustment.setDescription("prueba de decimales");
		adjustment.setUser("13700");
		adjustment.setIdadjustment(1L);
		adjustment.setPayment(pay);
		adjustmentservice.save(adjustment);
	}

}
