package com.atmj.jsboot.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.atmj.jsboot.dbaccess.entities.AddressEntity;

/**
 * The Interface AddressRepository.
 */
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

}
