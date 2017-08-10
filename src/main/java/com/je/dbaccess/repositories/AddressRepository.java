package com.je.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.AddressEntity;

/**
 * The Interface AddressRepository.
 */
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

}
