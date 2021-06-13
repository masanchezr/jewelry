package com.je.jsboot.dbaccess.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.je.jsboot.dbaccess.entities.NationEntity;

public interface NationsRepository extends CrudRepository<NationEntity, Long> {

	public List<NationEntity> findAll(Sort sort);

}
