package com.je.dbaccess.repositories;

import java.util.Date;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.HolidayEntity;
import com.je.dbaccess.entities.PlaceEntity;

/**
 * The Interface HolidayRepository.
 */
public interface HolidayRepository extends CrudRepository<HolidayEntity, Long> {

	/**
	 * Find by holiday and place.
	 *
	 * @param holiday the holiday
	 * @param place   the place
	 * @return the holiday entity
	 */
	public HolidayEntity findByHolidayAndPlace(@Temporal(TemporalType.DATE) Date holiday, PlaceEntity place);

	public Iterable<HolidayEntity> findByHolidayBetween(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until);
}
