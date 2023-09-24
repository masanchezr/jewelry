package com.atmj.jsboot.dbaccess.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.atmj.jsboot.dbaccess.entities.AdjustmentEntity;
import com.atmj.jsboot.dbaccess.entities.PaymentEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.utils.constants.Constants;

/**
 * The Interface AdjustmentRepository.
 */
public interface AdjustmentRepository extends CrudRepository<AdjustmentEntity, Long> {

	List<AdjustmentEntity> findByCreationdateAndPlace(@Temporal(TemporalType.DATE) Date creation,
			PlaceEntity placeEntity);

	/**
	 * Miramos si el arreglo ya se lo ha llevado el cliente
	 * 
	 * @param placeEntity
	 * @return
	 */
	List<AdjustmentEntity> findByCreationdateIsNotNullAndIdadjustment(Long idadjustment);

	List<AdjustmentEntity> findByCreationdateBetweenAndPayment(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until, PaymentEntity payment);

	@Query("select sum(a.amount) from AdjustmentEntity a where a.creationdate>=:from and a.creationdate<=:until and a.place=:place")
	BigDecimal sumAmountByCreationdateAndPlace(@Param("from") @Temporal(TemporalType.DATE) Date from,
			@Param("until") @Temporal(TemporalType.DATE) Date until, @Param(Constants.PLACE) PlaceEntity placeEntity);

}
