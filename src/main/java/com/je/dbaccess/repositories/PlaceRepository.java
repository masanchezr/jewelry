package com.je.dbaccess.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.je.dbaccess.entities.PlaceEntity;

/**
 * The Interface PlaceRepository.
 */
public interface PlaceRepository extends
		PagingAndSortingRepository<PlaceEntity, Long> {

}
