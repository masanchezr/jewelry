package com.je.services.users;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

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
	private Mapper mapper;

	@Override
	public User disableEnableUser(String username) {
		User user = null;
		UserEntity entity = usersRepository.findById(username).get();
		if (entity != null) {
			Boolean state = entity.getEnabled();
			if (state.equals(Boolean.TRUE)) {
				entity.setEnabled(Boolean.FALSE);
			} else {
				entity.setEnabled(Boolean.TRUE);
			}

			user = mapper.map(usersRepository.save(entity), User.class);
		}
		return user;
	}

	@Override
	public void newUser(User user) {
		String username = user.getUsername();
		UserEntity entity = usersRepository.findById(username).get();
		if (entity == null) {
			PlaceUserEntity pue = new PlaceUserEntity();
			pue.setPlace(mapper.map(user.getPlace(), PlaceEntity.class));
			pue.setUsername(username);
			placeUserRepository.save(pue);
		}
		entity = mapper.map(user, UserEntity.class);
		usersRepository.save(entity);
	}

}
