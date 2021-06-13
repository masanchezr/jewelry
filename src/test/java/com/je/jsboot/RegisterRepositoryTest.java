package com.je.jsboot;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.je.jsboot.dbaccess.entities.UserEntity;
import com.je.jsboot.dbaccess.repositories.RegisterRepository;
import com.je.jsboot.utils.date.DateUtil;

@SpringBootTest
class RegisterRepositoryTest {
	@Autowired
	private RegisterRepository registerRepository;

	@Test
	void findByDateAndEmployeeTest() {
		UserEntity userEntity = new UserEntity();
		Date date = DateUtil.getDate("2019-05-15");
		userEntity.setUsername("13700");
		Assertions.assertNull(registerRepository.findByDateAndEmployee(date, userEntity));
	}

}
