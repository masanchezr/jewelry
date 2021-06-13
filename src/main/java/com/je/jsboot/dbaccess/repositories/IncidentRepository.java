package com.je.jsboot.dbaccess.repositories;

import java.util.Date;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.je.jsboot.dbaccess.entities.IncidentEntity;
import com.je.jsboot.dbaccess.entities.UserEntity;

public interface IncidentRepository extends CrudRepository<IncidentEntity, Long> {

	public Iterable<IncidentEntity> findByUsername(UserEntity user);

	public Iterable<IncidentEntity> findByState(Boolean state);

	public Iterable<IncidentEntity> findByUsernameAndState(UserEntity entity, Boolean state);

	public Iterable<IncidentEntity> findByUsernameAndCreationdateBetween(UserEntity entity,
			@Temporal(TemporalType.DATE) Date datefrom, @Temporal(TemporalType.DATE) Date dateuntil);
}
