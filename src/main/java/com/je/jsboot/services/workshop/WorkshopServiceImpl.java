package com.je.jsboot.services.workshop;

import java.util.Date;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.je.jsboot.dbaccess.entities.WorkshopEntity;
import com.je.jsboot.dbaccess.repositories.WorkshopRepository;

/**
 * The Class WorkshopServiceImpl.
 */
@Service
public class WorkshopServiceImpl implements WorkshopService {

	/** The workshop repository. */
	@Autowired
	private WorkshopRepository workshopRepository;

	/** The mapper. */
	@Autowired
	private Mapper mapper;

	public void save(Workshop workshop) {
		WorkshopEntity work = mapper.map(workshop, WorkshopEntity.class);
		work.setCreationdate(new Date());
		workshopRepository.save(work);
	}
}
