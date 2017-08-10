package com.je.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.WorkshopEntity;

/**
 * The Interface WorkshopRepository.
 */
public interface WorkshopRepository extends
		CrudRepository<WorkshopEntity, Long> {

	/**
	 * Find by creationdate between.
	 *
	 * @param from
	 *            the from
	 * @param until
	 *            the until
	 * @return the list
	 */
	List<WorkshopEntity> findByCreationdateBetween(
			@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until);
}
