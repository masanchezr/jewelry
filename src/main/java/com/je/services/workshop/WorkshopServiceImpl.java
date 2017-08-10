package com.je.services.workshop;

import java.util.Date;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.WorkshopEntity;
import com.je.dbaccess.repositories.WorkshopRepository;

/**
 * The Class WorkshopServiceImpl.
 */
public class WorkshopServiceImpl implements WorkshopService {

	/** The workshop repository. */
	@Autowired
	private WorkshopRepository workshopRepository;

	/** The mapper. */
	@Autowired
	private Mapper mapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.je.services.workshop.WorkshopService#save(com.je.services.workshop
	 * .Workshop)
	 */
	public void save(Workshop workshop) {
		WorkshopEntity work = mapper.map(workshop, WorkshopEntity.class);
		work.setCreationdate(new Date());
		workshopRepository.save(work);
	}
}
