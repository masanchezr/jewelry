package com.je.services.metal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.repositories.MetalRepository;

/**
 * The Class MetalServiceImpl.
 */
public class MetalServiceImpl implements MetalService {

	/** The material repository. */
	@Autowired
	private MetalRepository materialRepository;

	@Override
	public Iterable<MetalEntity> getAllMetals() {
		return materialRepository.findAll();
	}

	@Override
	public List<MetalEntity> getAllMetalsActive() {
		return materialRepository.findByActiveTrue();
	}

	@Override
	public MetalEntity findById(long l) {
		return materialRepository.findById(l).orElse(null);
	}
}
