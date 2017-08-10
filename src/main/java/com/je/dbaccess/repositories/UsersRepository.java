package com.je.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, String> {

}
