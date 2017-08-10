package com.je.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.CoinEntity;

/**
 * The Interface CoinsRepository.
 */
public interface CoinsRepository extends CrudRepository<CoinEntity, Long> {

}
