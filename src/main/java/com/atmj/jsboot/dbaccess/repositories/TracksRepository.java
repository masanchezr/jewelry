package com.atmj.jsboot.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.atmj.jsboot.dbaccess.entities.TrackEntity;

public interface TracksRepository extends CrudRepository<TrackEntity, Long> {

}
