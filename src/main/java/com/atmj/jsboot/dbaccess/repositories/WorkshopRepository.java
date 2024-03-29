package com.atmj.jsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import jakarta.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.atmj.jsboot.dbaccess.entities.WorkshopEntity;

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
