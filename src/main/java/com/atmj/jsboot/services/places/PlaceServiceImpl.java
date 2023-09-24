package com.atmj.jsboot.services.places;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceUserEntity;
import com.atmj.jsboot.dbaccess.entities.UserEntity;
import com.atmj.jsboot.dbaccess.repositories.PlaceRepository;
import com.atmj.jsboot.dbaccess.repositories.PlaceUserRepository;
import com.atmj.jsboot.dbaccess.repositories.UsersRepository;
import com.atmj.jsboot.utils.constants.Constants;

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

	@Autowired
	private UsersRepository usersRepository;

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
		UserEntity u = usersRepository.findByUsername(user);
		List<PlaceUserEntity> placeuser = placeUserRepository.findByUser(u);
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
