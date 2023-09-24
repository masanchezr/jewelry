package com.atmj.jsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.atmj.jsboot.dbaccess.entities.RegisterEntity;
import com.atmj.jsboot.dbaccess.entities.UserEntity;

public interface RegisterRepository extends CrudRepository<RegisterEntity, Long> {

	public List<RegisterEntity> findByDateBetweenOrderByDate(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until);

	public RegisterEntity findByDateAndEmployee(@Temporal(TemporalType.DATE) Date date, UserEntity userEntity);
}
