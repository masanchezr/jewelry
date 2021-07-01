package com.je.jsboot.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.entities.PlaceUserEntity;
import com.je.jsboot.dbaccess.entities.UserEntity;

public interface PlaceUserRepository extends CrudRepository<PlaceUserEntity, Long> {

	public List<PlaceUserEntity> findByPlace(PlaceEntity place);

	public List<PlaceUserEntity> findByUser(UserEntity findByUsername);
}
