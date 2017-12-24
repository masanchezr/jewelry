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

	@Override
	public Iterable<MetalEntity> getAllMetals() {
		return metalRepository.findAll();
	}

	@Override
	public List<MetalEntity> getAllMetalsActive() {
		return metalRepository.findByActiveTrue();
	}

	@Override
	public MetalEntity findById(long l) {
		return metalRepository.findById(l).get();
	}
}
