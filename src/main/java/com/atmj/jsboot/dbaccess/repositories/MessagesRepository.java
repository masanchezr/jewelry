package com.atmj.jsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.atmj.jsboot.dbaccess.entities.MessageEntity;
import com.atmj.jsboot.dbaccess.entities.UserEntity;

public interface MessagesRepository extends CrudRepository<MessageEntity, Long> {

	public List<MessageEntity> findByUserAndActiveTrueAndDatefromBeforeAndDateuntilAfter(UserEntity userEntity,
			Date from, Date until);

}
