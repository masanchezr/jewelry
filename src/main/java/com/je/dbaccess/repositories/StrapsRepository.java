package com.je.dbaccess.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.StrapEntity;

public interface StrapsRepository extends CrudRepository<StrapEntity, Long> {
	public List<StrapEntity> findByCreationdateAndPlace(@Temporal(TemporalType.DATE) Date creationdate,
			PlaceEntity placeEntity);

	public List<StrapEntity> findByCreationdateBetweenAndPlaceAndNumsaleNotNullOrderByNumsaleAsc(
			@Temporal(TemporalType.DATE) Date from, @Temporal(TemporalType.DATE) Date until, PlaceEntity place);

	public List<StrapEntity> findByNumsale(Long numsale);

	public List<StrapEntity> findByCreationdateBetweenAndPayment(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until, PaymentEntity payment);

	public List<StrapEntity> findByCreationdateBetweenAndPlace(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until, PlaceEntity place);

	@Query("select sum(s.amount) from StrapEntity s where s.creationdate>=:from and s.creationdate<=:until and s.place=:place")
	public BigDecimal sumCreationdateBetweenAndPlace(@Param("from") Date from, @Param("until") Date until,
			@Param("place") PlaceEntity place);
}
