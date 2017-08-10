package com.je.services.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.services.users.Client;
import com.je.services.users.RegistrationService;

/**
 * The Class RegistrationServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class RegistrationServiceTest {

	/** The registration service. */
	@Autowired
	RegistrationService registrationService;

	/**
	 * Register user test.
	 */
	@Test
	public void registerUserTest() {
		Client user = new Client();
		user.setEmail("mangeles.sanchez0807@gmail.com");
		user.setName("maria");
		// user.setSurname("fdafsafa");
		user.setNifclient("085478564K");
		user.setAddress("dfsdaf");
		registrationService.registerUser(user);
	}

}
