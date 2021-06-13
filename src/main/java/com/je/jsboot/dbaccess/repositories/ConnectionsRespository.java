package com.je.jsboot.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.je.jsboot.dbaccess.entities.ConnectionEntity;

public interface ConnectionsRespository extends CrudRepository<ConnectionEntity, Long> {

}
