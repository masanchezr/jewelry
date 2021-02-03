package com.je.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.DiscountEntity;
import com.je.dbaccess.entities.PlaceEntity;

public interface DiscountsRepository extends CrudRepository<DiscountEntity, Long> {

	public List<DiscountEntity> findByCreationdateAndPlace(@Temporal(TemporalType.DATE) Date creationdate,
			PlaceEntity placeEntity);

	public DiscountEntity findByNumsaleAndYear(Long num, int year);
}
