package com.je.services.nations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.je.dbaccess.entities.NationEntity;
import com.je.dbaccess.repositories.NationsRepository;

public class NationServiceImpl implements NationService {

	@Autowired
	private NationsRepository nationsrepository;

	@Override
	public List<NationEntity> getNations() {
		return nationsrepository.findAll(new Sort(Direction.ASC, "nation"));
	}

}
