package com.je.jsboot.services.metadata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.je.jsboot.dbaccess.entities.MetadataEntity;
import com.je.jsboot.dbaccess.repositories.MetadataRepository;

@Service
public class MetadataServiceImpl implements MetadataService {

	@Autowired
	private MetadataRepository metadata;

	@Override
	public String getValue(String key) {
		MetadataEntity entity = metadata.findById(key).orElse(null);
		if (entity != null) {
			return entity.getValue();
		} else {
			return null;
		}
	}
}
