package com.atmj.jsboot.services.users;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
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
	private ModelMapper mapper;

	@Override
	public void newUser(User user) {
		String username = user.getUsername();
		UserEntity entity = usersRepository.findByUsername(username);
		PlaceUserEntity pue = null;
		if (entity == null) {
			entity = new UserEntity();
			pue = new PlaceUserEntity();
			pue.setPlace(mapper.map(user.getPlace(), PlaceEntity.class));
		}
		user.setPassword(Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8().encode(user.getPassword()));
		mapper.map(user, entity);
		entity = usersRepository.save(entity);
		if (pue != null) {
			pue.setUser(entity);
			placeUserRepository.save(pue);
		}
	}

	@Override
	public UserEntity getUser(String username) {
		return usersRepository.findByUsername(username);
	}
}
