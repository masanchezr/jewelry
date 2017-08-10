package com.je.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.BatteryEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;

public interface BatteriesRepository extends CrudRepository<BatteryEntity, Long> {

	public List<BatteryEntity> findByCreationdateAndPlace(@Temporal(TemporalType.DATE) Date creationdate,
			PlaceEntity placeEntity);

	public List<BatteryEntity> findByCreationdateBetweenAndPlaceAndNumsaleNotNullOrderByNumsaleAsc(
			@Temporal(TemporalType.DATE) Date from, @Temporal(TemporalType.DATE) Date until, PlaceEntity place);

	public List<BatteryEntity> findByNumsaleAndPlace(Long numsale, PlaceEntity place);

	public List<BatteryEntity> findByCreationdateBetweenAndPayment(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until, PaymentEntity payment);

}
