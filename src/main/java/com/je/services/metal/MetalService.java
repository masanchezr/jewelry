package com.je.services.metal;

import java.util.List;

import com.je.dbaccess.entities.MetalEntity;

/**
 * The Interface MetalService.
 */
public interface MetalService {

	/**
	 * Gets the all metals.
	 *
	 * @return the all metals
	 */
	public Iterable<MetalEntity> getAllMetals();

	public List<MetalEntity> getAllMetalsActive();

	public MetalEntity findOne(long l);

}