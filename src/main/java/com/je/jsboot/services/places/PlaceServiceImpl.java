package com.je.jsboot.services.places;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.entities.PlaceUserEntity;
import com.je.jsboot.dbaccess.repositories.PlaceRepository;
import com.je.jsboot.dbaccess.repositories.PlaceUserRepository;
import com.je.jsboot.utils.constants.Constants;

/**
 * The Class PlaceServiceImpl.
 */
@Service
public class PlaceServiceImpl implements PlaceService {

	/** The place repository. */
	@Autowired
	private PlaceRepository placeRepository;

	@Autowired
	private PlaceUserRepository placeUserRepository;

	@Override
	public Iterable<PlaceEntity> getAllPlacesActive() {
		return placeRepository.findByActiveTrueOrderByDescription();
	}

	@Override
	public PlaceEntity getPlace(Long idplace) {
		return placeRepository.findById(idplace).orElse(null);
	}

	@Override
	public PlaceEntity getPlaceUser(String user) {
		List<PlaceUserEntity> placeuser = placeUserRepository.findByUsername(user);
		PlaceEntity place = null;
		if (placeuser != null && !placeuser.isEmpty()) {
			place = placeuser.get(0).getPlace();
		}
		return place;
	}

	@Override
	public Iterable<PlaceEntity> getAllPlaces() {
		return placeRepository.findAll(Sort.by(Direction.ASC, Constants.DESCRIPTION));
	}
}
