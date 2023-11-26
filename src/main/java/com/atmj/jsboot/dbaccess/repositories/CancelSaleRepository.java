package com.atmj.jsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import jakarta.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.atmj.jsboot.dbaccess.entities.CancelSaleEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;

/**
 * The Interface CancelSaleRepository.
 */
public interface CancelSaleRepository extends CrudRepository<CancelSaleEntity, Long> {

	/**
	 * Find by creationdate and place.
	 *
	 * @param creationdate
	 *            the creationdate
	 * @param place
	 *            the place
	 * @return the list
	 */
	public List<CancelSaleEntity> findByCreationdateAndPlace(@Temporal(TemporalType.DATE) Date creationdate,
			PlaceEntity place);

	public List<CancelSaleEntity> findByNumsaleAndPlace(Long numsale, PlaceEntity place);

	public List<CancelSaleEntity> findByCreationdateBetweenAndPlaceOrderByNumsaleAsc(
			@Temporal(TemporalType.DATE) Date from, @Temporal(TemporalType.DATE) Date until, PlaceEntity place);
}
