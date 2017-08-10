package com.je.services.straps;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.StrapEntity;
import com.je.dbaccess.repositories.StrapsRepository;

public class StrapsServiceImpl implements StrapsService {

	@Autowired
	private StrapsRepository strapsRepository;

	public void saveSaleStrap(StrapEntity strap) {
		strap.setCreationdate(new Date());
		strapsRepository.save(strap);
	}

}
