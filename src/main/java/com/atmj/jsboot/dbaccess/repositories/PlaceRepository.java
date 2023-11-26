package com.atmj.jsboot.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.atmj.jsboot.dbaccess.entities.PlaceEntity;

/**
 * The Interface PlaceRepository.
 */
public interface PlaceRepository
		extends PagingAndSortingRepository<PlaceEntity, Long>, ListCrudRepository<PlaceEntity, Long> {

	public List<PlaceEntity> findByActiveTrueOrderByDescription();
}
