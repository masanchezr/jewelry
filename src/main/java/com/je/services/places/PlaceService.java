package com.je.services.places;

import com.je.dbaccess.entities.PlaceEntity;

/**
 * The Interface PlaceService.
 */
public interface PlaceService {

	/**
	 * Gets the all places.
	 *
	 * @return the all places
	 */
	public Iterable<PlaceEntity> getAllPlaces();

	public PlaceEntity getPlace(Long idplace);

	public PlaceEntity getPlaceUser(String user);

}
