package com.atmj.jsboot.services.users;

/**
 * The Interface RegistrationService.
 */
public interface RegistrationService {

	/**
	 * Register user.
	 * 
	 * @param user
	 *            the user
	 * @return true, if successful
	 */
	public boolean registerUser(Client user);
}
