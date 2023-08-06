package com.je.jsboot.dbaccess.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.entities.WorkEntity;
import com.je.jsboot.utils.constants.Constants;

public interface WorksRepository extends CrudRepository<WorkEntity, Long> {

	List<WorkEntity> findByCreationdateAndPlace(Date date, PlaceEntity place);

	@Query("select sum(a.amount) from WorkEntity a where a.creationdate>=:from and a.creationdate<=:until and a.place=:place")
	BigDecimal sumAmountByCreationdateAndPlace(@Param("from") @Temporal(TemporalType.DATE) Date from,
			@Param("until") @Temporal(TemporalType.DATE) Date until, @Param(Constants.PLACE) PlaceEntity placeEntity);
}
