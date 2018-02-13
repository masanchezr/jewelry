package com.je.services.metal;

import java.util.List;

import com.je.dbaccess.entities.MetalEntity;

/**
 * The Interface MetalService.
 */
public interface MetalService {

	/**
	 * Gets the all materials.
	 *
	 * @return the all materials
	 */
	public Iterable<MetalEntity> getAllMetals();

	public List<MetalEntity> getAllMetalsActive();

	public MetalEntity findById(long l);

}
