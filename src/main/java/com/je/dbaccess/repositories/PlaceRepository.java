package com.je.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.je.dbaccess.entities.PlaceEntity;

/**
 * The Interface PlaceRepository.
 */
public interface PlaceRepository extends PagingAndSortingRepository<PlaceEntity, Long> {

	public List<PlaceEntity> findByActiveTrueOrderByDescription();
}
