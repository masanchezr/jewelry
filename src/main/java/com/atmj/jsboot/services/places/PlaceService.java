package com.atmj.jsboot.services.places;

import com.atmj.jsboot.dbaccess.entities.PlaceEntity;

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
