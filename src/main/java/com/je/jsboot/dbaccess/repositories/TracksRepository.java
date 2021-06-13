package com.je.jsboot.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.je.jsboot.dbaccess.entities.TrackEntity;

public interface TracksRepository extends CrudRepository<TrackEntity, Long> {

}
