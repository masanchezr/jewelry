package com.je.services.otherconcepts;

import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.OtherConceptEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.PlaceUserEntity;
import com.je.dbaccess.repositories.OtherConceptsRepository;
import com.je.dbaccess.repositories.PlaceUserRepository;
import com.je.services.dailies.Daily;
import com.je.services.dailies.DailyService;
import com.je.utils.date.DateUtil;

/**
 * The Class OtherConceptServiceImpl.
 */
public class OtherConceptServiceImpl implements OtherConceptService {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	/** The others concepts repository. */
	@Autowired
	private OtherConceptsRepository othersConceptsRepository;

	@Autowired
	private PlaceUserRepository placeUserRepository;

	/** The mapper. */
	@Autowired
	private Mapper mapper;

	public Daily save(OtherConcept otherconcept) {
		OtherConceptEntity otherConceptEntity = mapper.map(otherconcept, OtherConceptEntity.class);
		List<PlaceUserEntity> places = placeUserRepository.findByUsername(otherconcept.getUser());
		PlaceEntity place = places.get(0).getPlace();
		otherConceptEntity.setPlace(place);
		otherConceptEntity.setCreationdate(new Date());
		othersConceptsRepository.save(otherConceptEntity);
		return dailyService.getDaily(DateUtil.getDateFormated(new Date()), place, null);
	}
}
