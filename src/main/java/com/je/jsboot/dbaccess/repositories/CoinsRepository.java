package com.je.jsboot.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.je.jsboot.dbaccess.entities.CoinEntity;

/**
 * The Interface CoinsRepository.
 */
public interface CoinsRepository extends CrudRepository<CoinEntity, Long> {

}
