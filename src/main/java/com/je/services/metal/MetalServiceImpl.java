package com.je.services.metal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.repositories.MetalRepository;

/**
 * The Class MetalServiceImpl.
 */
public class MetalServiceImpl implements MetalService {

	/** The metal repository. */
	@Autowired
	private MetalRepository metalRepository;

	public Iterable<MetalEntity> getAllMetals() {
		return metalRepository.findAll();
	}

	public List<MetalEntity> getAllMetalsActive() {
		return metalRepository.findByActiveTrue();
	}

	public MetalEntity findOne(long l) {
		return metalRepository.findOne(l);
	}
}