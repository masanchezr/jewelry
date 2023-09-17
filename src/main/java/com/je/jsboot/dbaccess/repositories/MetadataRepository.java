package com.je.jsboot.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.je.jsboot.dbaccess.entities.MetadataEntity;

public interface MetadataRepository extends CrudRepository<MetadataEntity, String> {

}
