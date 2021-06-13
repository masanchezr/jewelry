package com.je.jsboot.services.otherconcepts;

import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.je.jsboot.dbaccess.entities.OtherConceptEntity;
import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.entities.PlaceUserEntity;
import com.je.jsboot.dbaccess.repositories.OtherConceptsRepository;
import com.je.jsboot.dbaccess.repositories.PlaceUserRepository;
import com.je.jsboot.services.dailies.Daily;
import com.je.jsboot.services.dailies.DailyService;
import com.je.jsboot.utils.date.DateUtil;

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
