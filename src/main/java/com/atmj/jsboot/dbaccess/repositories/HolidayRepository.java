package com.atmj.jsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.atmj.jsboot.dbaccess.entities.HolidayEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;

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

	public List<HolidayEntity> findByHolidayBetween(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until);
}
