package com.je.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.ClientPawnEntity;

/**
 * The Interface ClientPawnsRepository.
 */
public interface ClientPawnsRepository extends CrudRepository<ClientPawnEntity, String> {

	public List<ClientPawnEntity> findByNameContainingIgnoreCase(String name);

	public List<ClientPawnEntity> findBySurnameContainingIgnoreCase(String surname);

}
