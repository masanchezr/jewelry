package com.je.jsboot.services.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.jsboot.dbaccess.entities.PawnEntity;
import com.je.jsboot.services.pawns.Pawn;

@Component
public class PawnEntityConverter {

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	public Pawn convertTo(PawnEntity entity) {
		Pawn p = mapper.map(entity, Pawn.class);
		p.setId(entity.getIdpawn());
		return p;
	}

	public List<Pawn> entitiesToDTO(List<PawnEntity> entities) {
		return entities.stream().map(x -> convertTo(x)).collect(Collectors.toList());
	}
}
