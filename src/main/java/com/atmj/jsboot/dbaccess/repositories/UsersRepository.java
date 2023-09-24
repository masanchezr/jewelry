package com.atmj.jsboot.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.atmj.jsboot.dbaccess.entities.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
	public UserEntity findByUsername(String user);

	public List<UserEntity> findByEnabledTrue();
}
