package com.atmj.jsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.atmj.jsboot.dbaccess.entities.DiscountEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;

import jakarta.persistence.TemporalType;

public interface DiscountsRepository extends CrudRepository<DiscountEntity, Long> {

	public List<DiscountEntity> findByCreationdateAndPlace(@Temporal(TemporalType.DATE) Date creationdate,
			PlaceEntity placeEntity);

	public DiscountEntity findByNumsaleAndYear(Long num, int year);

	public DiscountEntity findByNumsale(Long num);
}
