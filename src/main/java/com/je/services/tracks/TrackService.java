package com.je.services.tracks;

import com.je.dbaccess.entities.TrackEntity;

public interface TrackService {

	public Iterable<TrackEntity> getTracks();

}
