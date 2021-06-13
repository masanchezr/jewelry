package com.je.jsboot.services.nations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.je.jsboot.dbaccess.entities.NationEntity;
import com.je.jsboot.dbaccess.repositories.NationsRepository;

@Service
public class NationServiceImpl implements NationService {

	@Autowired
	private NationsRepository nationsrepository;

	@Override
	public List<NationEntity> getNations() {
		return nationsrepository.findAll(Sort.by(Direction.ASC, "nation"));
	}

}
