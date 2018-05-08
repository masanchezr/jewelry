package com.je.services.users;

import com.je.dbaccess.entities.UserEntity;

public interface UserService {

	public void newUser(User user);

	public UserEntity getUser(String username);

}
