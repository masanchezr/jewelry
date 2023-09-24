package com.atmj.jsboot.services.tracks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.TrackEntity;
import com.atmj.jsboot.dbaccess.repositories.TracksRepository;

@Service
public class TrackServiceImpl implements TrackService {

	@Autowired
	private TracksRepository tracksrepository;

	@Override
	public Iterable<TrackEntity> getTracks() {
		return tracksrepository.findAll();
	}

}
