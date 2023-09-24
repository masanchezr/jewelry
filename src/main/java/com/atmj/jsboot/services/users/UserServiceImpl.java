package com.atmj.jsboot.services.users;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceUserEntity;
import com.atmj.jsboot.dbaccess.entities.UserEntity;
import com.atmj.jsboot.dbaccess.repositories.PlaceUserRepository;
import com.atmj.jsboot.dbaccess.repositories.UsersRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private PlaceUserRepository placeUserRepository;

	@Autowired
	private PasswordEncoder pbkdf2Encoder;

	@Autowired
	private ModelMapper mapper;

	@Override
	public void newUser(User user) {
		String username = user.getUsername();
		UserEntity entity = usersRepository.findByUsername(username);
		if (entity == null) {
			PlaceUserEntity pue = new PlaceUserEntity();
			pue.setPlace(mapper.map(user.getPlace(), PlaceEntity.class));
			pue.setUser(entity);
			placeUserRepository.save(pue);
		}
		user.setPassword(pbkdf2Encoder.encode(user.getPassword()));
		entity = mapper.map(user, UserEntity.class);
		usersRepository.save(entity);
	}

	@Override
	public UserEntity getUser(String username) {
		return usersRepository.findByUsername(username);
	}
}
