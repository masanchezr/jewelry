package com.atmj.jsboot.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.services.users.UserService;

@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserService userservice;

	@Test
	void getUserTest() {
		Assertions.assertNotNull(userservice.getUser("9004"));
	}

}
