package com.je.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.RegisterEntity;
import com.je.dbaccess.entities.UserEntity;

public interface RegisterRepository extends CrudRepository<RegisterEntity, Long> {

	public List<RegisterEntity> findByDateBetween(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until);

	public RegisterEntity findByDateAndEmployee(@Temporal(TemporalType.DATE) Date date, UserEntity userEntity);
}
