package com.atmj.jsboot.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.services.users.User;
import com.atmj.jsboot.services.users.UserService;

@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	void getUserTest() {
		Assertions.assertNotNull(userService.getUser("9004"));
	}

	@Test
	void newUserTest() {
		User user = new User();
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(28017L);
		user.setEnabled(true);
		user.setPassword("natalia08");
		user.setPlace(place);
		user.setUsername("prueba4");
		if (userService.getUser(user.getUsername()) == null) {
			userService.newUser(user);
		}
	}
}
