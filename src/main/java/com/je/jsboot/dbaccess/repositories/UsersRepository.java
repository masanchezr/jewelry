package com.je.jsboot.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.je.jsboot.dbaccess.entities.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
	public UserEntity findByUsername(String user);

	public List<UserEntity> findByEnabledTrue();
}
