package com.je.jsboot.services.metal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.je.jsboot.dbaccess.entities.MetalEntity;
import com.je.jsboot.dbaccess.repositories.MetalRepository;

/**
 * The Class MetalServiceImpl.
 */
@Service
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
