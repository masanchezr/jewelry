package com.atmj.jsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import jakarta.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.atmj.jsboot.dbaccess.entities.OtherConceptEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;

/**
 * The Interface OtherConceptsRepository.
 */
public interface OtherConceptsRepository extends CrudRepository<OtherConceptEntity, Long> {

	/**
	 * Find by creationdate and place.
	 *
	 * @param creationdate the creationdate
	 * @param placeEntity  the place entity
	 * @return the list
	 */
	List<OtherConceptEntity> findByCreationdateAndPlace(@Temporal(TemporalType.DATE) Date creationdate,
			PlaceEntity placeEntity);

}
