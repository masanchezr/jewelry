package com.atmj.jsboot.services.tracks;

import com.atmj.jsboot.dbaccess.entities.TrackEntity;

public interface TrackService {

	public Iterable<TrackEntity> getTracks();

}
