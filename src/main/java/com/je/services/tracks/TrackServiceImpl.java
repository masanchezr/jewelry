package com.je.services.tracks;

import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.TrackEntity;
import com.je.dbaccess.repositories.TracksRepository;

public class TrackServiceImpl implements TrackService {

	@Autowired
	private TracksRepository tracksrepository;

	@Override
	public Iterable<TrackEntity> getTracks() {
		return tracksrepository.findAll();
	}

}
