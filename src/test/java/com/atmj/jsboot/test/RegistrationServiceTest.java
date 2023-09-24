package com.atmj.jsboot.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.services.users.Client;
import com.atmj.jsboot.services.users.RegistrationService;

/**
 * The Class RegistrationServiceTest.
 */
@SpringBootTest
class RegistrationServiceTest {

	/** The registration service. */
	@Autowired
	RegistrationService registrationService;

	/**
	 * Register user test.
	 */
	@Test
	void registerUserTest() {
		Client user = new Client();
		user.setEmail("mangeles.sanchez0807@gmail.com");
		user.setName("maria");
		// user.setSurname("fdafsafa");
		user.setNifclient("085478564K");
		user.setAddress("dfsdaf");
		Assertions.assertEquals(false, registrationService.registerUser(user));
	}

}
