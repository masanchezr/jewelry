package com.je.dbaccess.test;

import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.RegisterEntity;
import com.je.dbaccess.entities.UserEntity;
import com.je.dbaccess.repositories.RegisterRepository;
import com.je.utils.date.DateUtil;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class RegisterRepositoryTest {
	@Autowired
	private RegisterRepository registerRepository;

	@Test
	public void findByDateAndEmployeeTest() {
		UserEntity userEntity = new UserEntity();
		Date date = DateUtil.getDate("2019-05-15");
		userEntity.setUsername("13700");
		RegisterEntity register = registerRepository.findByDateAndEmployee(date, userEntity);
		if (register != null) {
			System.out.println(register.getIpaddress() + " " + LocalDate.now());
		}
	}

}
