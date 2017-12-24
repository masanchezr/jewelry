package com.je.dbaccess.test;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.entities.PayrollEntity;
import com.je.dbaccess.repositories.PayrollRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class PayrollRepositoryTest {

	@Autowired
	private PayrollRepository payrollRepository;

	@Test
	public void findByIdTest() {
		Optional<PayrollEntity> payroll = payrollRepository.findById(2015513700L);
		if (payroll.isPresent()) {
			System.out.println("no es nulo");
		}
	}

}
