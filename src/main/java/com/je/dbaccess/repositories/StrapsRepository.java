package com.je.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.StrapEntity;

public interface StrapsRepository extends CrudRepository<StrapEntity, Long> {
	public List<StrapEntity> findByCreationdateAndPlace(@Temporal(TemporalType.DATE) Date creationdate,
			PlaceEntity placeEntity);

	public List<StrapEntity> findByCreationdateBetweenAndPlaceAndNumsaleNotNullOrderByNumsaleAsc(
			@Temporal(TemporalType.DATE) Date from, @Temporal(TemporalType.DATE) Date until, PlaceEntity place);

	public StrapEntity findByNumsaleAndPlace(Long numsale, PlaceEntity place);

	public List<StrapEntity> findByCreationdateBetweenAndPayment(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until, PaymentEntity payment);
}
