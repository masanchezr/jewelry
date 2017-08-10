package com.je.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.PlaceUserEntity;

public interface PlaceUserRepository extends CrudRepository<PlaceUserEntity, Long> {

	public List<PlaceUserEntity> findByUsername(String username);
}
