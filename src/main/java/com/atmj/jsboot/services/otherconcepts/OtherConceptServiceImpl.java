package com.atmj.jsboot.services.otherconcepts;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.OtherConceptEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceUserEntity;
import com.atmj.jsboot.dbaccess.repositories.OtherConceptsRepository;
import com.atmj.jsboot.dbaccess.repositories.PlaceUserRepository;
import com.atmj.jsboot.dbaccess.repositories.UsersRepository;
import com.atmj.jsboot.services.dailies.Daily;
import com.atmj.jsboot.services.dailies.DailyService;
import com.atmj.jsboot.utils.date.DateUtil;

/**
 * The Class OtherConceptServiceImpl.
 */
@Service
public class OtherConceptServiceImpl implements OtherConceptService {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	/** The others concepts repository. */
	@Autowired
	private OtherConceptsRepository othersConceptsRepository;

	@Autowired
	private PlaceUserRepository placeUserRepository;

	@Autowired
	private UsersRepository usersRepository;

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	public Daily save(OtherConcept otherconcept) {
		OtherConceptEntity otherConceptEntity = mapper.map(otherconcept, OtherConceptEntity.class);
		List<PlaceUserEntity> places = placeUserRepository
				.findByUser(usersRepository.findByUsername(otherconcept.getUser()));
		PlaceEntity place = places.get(0).getPlace();
		otherConceptEntity.setPlace(place);
		otherConceptEntity.setCreationdate(new Date());
		othersConceptsRepository.save(otherConceptEntity);
		return dailyService.getDaily(DateUtil.getDateFormated(new Date()), place, null);
	}
}
