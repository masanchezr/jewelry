package com.atmj.jsboot.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.atmj.jsboot.dbaccess.entities.CoinEntity;

/**
 * The Interface CoinsRepository.
 */
public interface CoinsRepository extends CrudRepository<CoinEntity, Long> {

}
