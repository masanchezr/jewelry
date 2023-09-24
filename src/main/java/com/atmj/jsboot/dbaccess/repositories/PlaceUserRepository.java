package com.atmj.jsboot.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceUserEntity;
import com.atmj.jsboot.dbaccess.entities.UserEntity;

public interface PlaceUserRepository extends CrudRepository<PlaceUserEntity, Long> {

	public List<PlaceUserEntity> findByPlace(PlaceEntity place);

	public List<PlaceUserEntity> findByUser(UserEntity findByUsername);
}
