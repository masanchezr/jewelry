package com.atmj.jsboot.services.users;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.ClientEntity;
import com.atmj.jsboot.dbaccess.managers.UsersManager;

/**
 * The Class RegistrationServiceImpl.
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

	/** The user manager. */
	@Autowired
	private UsersManager usersManager;

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.atmj.jsboot.services.users.RegistrationService#registerUser(com.atmj.jsboot.
	 * services. users.Client)
	 */
	public boolean registerUser(Client user) {
		// comprobamos primero si ya existe un usuario con el mismo nif
		ClientEntity userEntity = usersManager.findById(user.getNifclient());
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
