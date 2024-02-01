package com.atmj.jsboot.test;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.dbaccess.entities.PaymentEntity;
import com.atmj.jsboot.dbaccess.repositories.InstallmentsRepository;
import com.atmj.jsboot.utils.date.DateUtil;

@SpringBootTest
class InstallmentsRepositoryTest {

	@Autowired
	InstallmentsRepository repository;

	@Test
	void findByCreationdateBetweenPayTest() {
		PaymentEntity pay = new PaymentEntity();
		Date from = DateUtil.getDate("2021-01-01");
		pay.setIdpayment(3L);
		Assertions.assertNotNull(repository.findByCreationdateBetweenAndPay(from, new Date(), pay));
	}

}
