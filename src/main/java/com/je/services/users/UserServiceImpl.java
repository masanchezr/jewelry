package com.je.services.users;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.PlaceUserEntity;
import com.je.dbaccess.entities.UserEntity;
import com.je.dbaccess.repositories.PlaceUserRepository;
import com.je.dbaccess.repositories.UsersRepository;

public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private PlaceUserRepository placeUserRepository;

	@Autowired
	private PasswordEncoder pbkdf2Encoder;

	@Autowired
	private Mapper mapper;

	@Override
	public void newUser(User user) {
		String username = user.getUsername();
		UserEntity entity = usersRepository.findById(username).orElse(null);
		if (entity == null) {
			PlaceUserEntity pue = new PlaceUserEntity();
			pue.setPlace(mapper.map(user.getPlace(), PlaceEntity.class));
			pue.setUsername(username);
			placeUserRepository.save(pue);
		}
		user.setPassword(pbkdf2Encoder.encode(user.getPassword()));
		entity = mapper.map(user, UserEntity.class);
		usersRepository.save(entity);
	}

	@Override
	public UserEntity getUser(String username) {
		return usersRepository.findById(username).orElse(null);
	}
}
