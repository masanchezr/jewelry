package com.je.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.PayrollEntity;
import com.je.dbaccess.entities.PlaceEntity;

public interface PayrollRepository extends CrudRepository<PayrollEntity, Long> {

	List<PayrollEntity> findByCreationdateAndPlace(
			@Temporal(TemporalType.DATE) Date creationdate, PlaceEntity place);

}
