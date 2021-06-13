package com.je.jsboot.services.places;

import com.je.jsboot.dbaccess.entities.PlaceEntity;

/**
 * The Interface PlaceService.
 */
public interface PlaceService {

	/**
	 * Gets the all places.
	 *
	 * @return the all places
	 */
	public Iterable<PlaceEntity> getAllPlacesActive();

	public PlaceEntity getPlace(Long idplace);

	public PlaceEntity getPlaceUser(String user);

	public Iterable<PlaceEntity> getAllPlaces();

}
