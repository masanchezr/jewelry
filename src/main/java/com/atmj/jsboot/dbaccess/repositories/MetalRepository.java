package com.atmj.jsboot.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.atmj.jsboot.dbaccess.entities.MetalEntity;

/**
 * The Interface MetalRepository.
 */
public interface MetalRepository extends CrudRepository<MetalEntity, Long> {

	public List<MetalEntity> findByActiveTrue();
}
