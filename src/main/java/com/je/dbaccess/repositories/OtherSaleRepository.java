package com.je.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.OtherSaleEntity;
import com.je.dbaccess.entities.PlaceEntity;

public interface OtherSaleRepository extends CrudRepository<OtherSaleEntity, Long> {

	List<OtherSaleEntity> findByCreationdateAndPlace(@Temporal(TemporalType.DATE) Date date, PlaceEntity placeEntity);

	List<OtherSaleEntity> findByNumsale(Long num);

}
