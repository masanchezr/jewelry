package com.je.dbaccess.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.je.dbaccess.entities.OtherSaleEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;

public interface OtherSaleRepository extends CrudRepository<OtherSaleEntity, Long> {

	List<OtherSaleEntity> findByCreationdateAndPlace(@Temporal(TemporalType.DATE) Date date, PlaceEntity placeEntity);

	List<OtherSaleEntity> findByNumsale(Long num);

	List<OtherSaleEntity> findByCreationdateBetweenAndPlace(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until, PlaceEntity place);

	@Query("select sum(s.amount) from OtherSaleEntity s where s.creationdate>=:from and s.creationdate<=:until and s.place=:place")
	BigDecimal sumCreationdateBetweenAndPlace(@Param("from") Date from, @Param("until") Date until,
			@Param("place") PlaceEntity place);

	List<OtherSaleEntity> findByCreationdateBetweenAndPay(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until, PaymentEntity payment);

}
