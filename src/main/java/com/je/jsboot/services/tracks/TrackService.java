package com.je.jsboot.services.tracks;

import com.je.jsboot.dbaccess.entities.TrackEntity;

public interface TrackService {

	public Iterable<TrackEntity> getTracks();

}
