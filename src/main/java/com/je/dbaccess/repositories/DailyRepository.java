package com.je.dbaccess.repositories;

import java.util.Date;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.DailyEntity;
import com.je.dbaccess.entities.PlaceEntity;

/**
 * The Interface DailyRepository.
 */
public interface DailyRepository extends CrudRepository<DailyEntity, Long> {

	/**
	 * Find by place and dailydate.
	 *
	 * @param place
	 *            the place
	 * @param dailydate
	 *            the dailydate
	 * @return the list
	 */
	public DailyEntity findByPlaceAndDailydate(PlaceEntity place, @Temporal(TemporalType.DATE) Date dailydate);

	/**
	 * Find first by place order by iddaily desc.
	 *
	 * @param place
	 *            the place
	 * @return the daily entity
	 */
	public DailyEntity findFirstByPlaceOrderByIddailyDesc(PlaceEntity place);
}
