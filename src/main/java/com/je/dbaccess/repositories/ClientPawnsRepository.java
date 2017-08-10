package com.je.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.ClientPawnEntity;

/**
 * The Interface ClientPawnsRepository.
 */
public interface ClientPawnsRepository extends
		CrudRepository<ClientPawnEntity, String> {

}
