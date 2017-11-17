package com.je.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.je.dbaccess.entities.TrackEntity;

public interface TracksRepository extends CrudRepository<TrackEntity, Long> {

}
