package com.atmj.jsboot.services.users;

import com.atmj.jsboot.dbaccess.entities.UserEntity;

public interface UserService {

	public void newUser(User user);

	public UserEntity getUser(String username);

}
