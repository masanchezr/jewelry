package com.je.services.users;

import java.util.Date;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.ClientEntity;
import com.je.dbaccess.managers.UsersManager;

/**
 * The Class RegistrationServiceImpl.
 */
public class RegistrationServiceImpl implements RegistrationService {

	/** The user manager. */
	@Autowired
	private UsersManager usersManager;

	/** The mapper. */
	@Autowired
	private Mapper mapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.je.services.users.RegistrationService#registerUser(com.je.services.
	 * users.Client)
	 */
	public boolean registerUser(Client user) {
		// comprobamos primero si ya existe un usuario con el mismo nif
		ClientEntity userEntity = usersManager.findOne(user.getNifclient());
		boolean successfull = false;
		if (userEntity == null) {
			userEntity = new ClientEntity();
			mapper.map(user, userEntity);
			userEntity.setCreationdate(new Date());
			usersManager.save(userEntity);
			successfull = true;
		}
		return successfull;

	}

}
