package com.atmj.jsboot.services.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.atmj.dbaccess.entities.AuthorityEntity;
import com.atmj.jsboot.dbaccess.entities.UserEntity;
import com.atmj.jsboot.dbaccess.repositories.UsersRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		UserEntity appUser = usersRepository.findByUsername(username);
		// Mapear nuestra lista de Authority con la de spring security
		List<GrantedAuthority> grantList = new ArrayList<>();
		for (AuthorityEntity authority : appUser.getAuthority()) {
			// ROLE_USER, ROLE_ADMIN,..
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
			grantList.add(grantedAuthority);
		}

		// Crear El objeto UserDetails que va a ir en sesion y retornarlo.
		return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(),
				grantList);
	}

	public void saveUser(UserEntity user) {
		Optional<UserEntity> entity = usersRepository.findById(user.getId());
		if (entity.isPresent()) {
			UserEntity ue = entity.get();
			ue.setPassword(encoder().encode(user.getPassword()));
			usersRepository.save(ue);

		} else {
			user.setPassword(encoder().encode(user.getPassword()));
			usersRepository.save(user);
		}
	}

	@Bean
	public PasswordEncoder encoder() {
		return new Pbkdf2PasswordEncoder();
	}

	public List<UserEntity> allEmployeesActives() {
		return usersRepository.findByEnabledTrue();
	}
}