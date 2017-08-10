package com.je.services.places;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.PlaceUserEntity;
import com.je.dbaccess.repositories.PlaceRepository;
import com.je.dbaccess.repositories.PlaceUserRepository;

/**
 * The Class PlaceServiceImpl.
 */
public class PlaceServiceImpl implements PlaceService {

	/** The place repository. */
	@Autowired
	private PlaceRepository placeRepository;

	@Autowired
	private PlaceUserRepository placeUserRepository;

	public Iterable<PlaceEntity> getAllPlaces() {
		return placeRepository.findAll(new Sort(new Order("description")));
	}

	public PlaceEntity getPlace(Long idplace) {
		return placeRepository.findOne(idplace);
	}

	public PlaceEntity getPlaceUser(String user) {
		List<PlaceUserEntity> placeuser = placeUserRepository.findByUsername(user);
		PlaceEntity place = null;
		if (placeuser != null && !placeuser.isEmpty()) {
			place = placeuser.get(0).getPlace();
		}
		return place;
	}
}
