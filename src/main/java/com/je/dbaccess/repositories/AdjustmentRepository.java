package com.je.dbaccess.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.je.dbaccess.entities.AdjustmentEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;

/**
 * The Interface AdjustmentRepository.
 */
public interface AdjustmentRepository extends CrudRepository<AdjustmentEntity, Long> {

	List<AdjustmentEntity> findByCarrydateAndPlace(@Temporal(TemporalType.DATE) Date carrydate,
			PlaceEntity placeEntity);

	List<AdjustmentEntity> findByCreationdateBetweenAndWorkTrue(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until);

	List<AdjustmentEntity> findByCreationdateAndPlaceAndAmountworkNotNullAndWorkFalse(
			@Temporal(TemporalType.DATE) Date creationdate, PlaceEntity placeEntity);

	List<AdjustmentEntity> findByCarrydateBetweenAndPayment(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until, PaymentEntity payment);

	@Query("select sum(a.amountwork) from AdjustmentEntity a where a.creationdate>=:from and a.creationdate<=:until and a.place=:place")
	BigDecimal sumAmountworkByCreationdateAndPlace(@Param("from") @Temporal(TemporalType.DATE) Date from,
			@Param("until") @Temporal(TemporalType.DATE) Date until, @Param("place") PlaceEntity placeEntity);

	@Query("select sum(a.amount) from AdjustmentEntity a where a.creationdate>=:from and a.creationdate<=:until and a.place=:place")
	BigDecimal sumAmountByCreationdateAndPlace(@Param("from") @Temporal(TemporalType.DATE) Date from,
			@Param("until") @Temporal(TemporalType.DATE) Date until, @Param("place") PlaceEntity placeEntity);

}
