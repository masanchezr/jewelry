package com.je.services.recordings;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.RecordingEntity;
import com.je.dbaccess.repositories.RecordingRepository;

public class RecordingServiceImpl implements RecordingService {

	@Autowired
	private RecordingRepository recordingRepository;

	public void save(RecordingEntity recording) {
		recording.setCreationdate(new Date());
		recordingRepository.save(recording);
	}

}
