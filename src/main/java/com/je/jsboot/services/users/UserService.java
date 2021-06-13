package com.je.jsboot.services.users;

import com.je.jsboot.dbaccess.entities.UserEntity;

public interface UserService {

	public void newUser(User user);

	public UserEntity getUser(String username);

}
