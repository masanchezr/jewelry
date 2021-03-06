package com.je.jsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.entities.RentalEntity;

public interface RentalsRepository extends CrudRepository<RentalEntity, Long> {
	public List<RentalEntity> findByCreationdateAndPlace(
			@Temporal(TemporalType.DATE) Date creationdate,
			PlaceEntity placeEntity);
}
