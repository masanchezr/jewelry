package com.je.services.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.UserEntity;
import com.je.services.users.User;
import com.je.services.users.UserService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class UserServiceTest {

	@Autowired
	private UserService userservice;

	@Test
	public void newUserTest() {
		User user = new User();
		user.setUsername("13700");
		user.setPassword("tomelloso");
		user.setEnabled(Boolean.TRUE);
		userservice.newUser(user);
	}

	@Test
	public void getUserTest() {
		UserEntity user = userservice.getUser("9004");
		System.out.println(user.getPassword());
	}

}
