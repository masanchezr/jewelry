package com.je.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.RecordingEntity;

public interface RecordingRepository extends CrudRepository<RecordingEntity, Long> {

	List<RecordingEntity> findByCreationdateAndPlace(@Temporal(TemporalType.DATE) Date date, PlaceEntity placeEntity);

	List<RecordingEntity> findByCreationdateBetweenAndPay(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until, PaymentEntity payment);

	RecordingEntity findByNumsale(Long numsale);

}
