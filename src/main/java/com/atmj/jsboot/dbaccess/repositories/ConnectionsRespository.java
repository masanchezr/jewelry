package com.atmj.jsboot.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.atmj.jsboot.dbaccess.entities.ConnectionEntity;

public interface ConnectionsRespository extends CrudRepository<ConnectionEntity, Long> {

}
