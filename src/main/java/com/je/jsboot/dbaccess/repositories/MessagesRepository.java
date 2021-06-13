package com.je.jsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.je.jsboot.dbaccess.entities.MessageEntity;
import com.je.jsboot.dbaccess.entities.UserEntity;

public interface MessagesRepository extends CrudRepository<MessageEntity, Long> {

	public List<MessageEntity> findByUserAndActiveTrueAndDatefromBeforeAndDateuntilAfter(UserEntity userEntity,
			Date from, Date until);

}
