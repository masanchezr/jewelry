package com.je.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.MessageEntity;
import com.je.dbaccess.entities.UserEntity;

public interface MessagesRepository extends CrudRepository<MessageEntity, Long> {

	public List<MessageEntity> findByUserAndActiveTrueAndDatefromBeforeAndDateuntilAfter(UserEntity userEntity,
			@Temporal(TemporalType.DATE) Date from, @Temporal(TemporalType.DATE) Date until);

}
