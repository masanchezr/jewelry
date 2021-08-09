package com.je.jsboot.services.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.jsboot.dbaccess.entities.PawnEntity;
import com.je.jsboot.services.pawns.NewPawn;
import com.je.jsboot.services.pawns.Pawn;

@Component
public class PawnEntityConverter {

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	public Pawn convertToPawn(PawnEntity entity) {
		Pawn p = mapper.map(entity, Pawn.class);
		p.setId(entity.getIdpawn());
		return p;
	}

	public NewPawn convertToNewPawn(PawnEntity entity) {
		NewPawn p = mapper.map(entity, NewPawn.class);
		p.setId(entity.getIdpawn());
		return p;
	}

	public List<Pawn> entitiesToPawns(List<PawnEntity> entities) {
		return entities.stream().map(x -> convertToPawn(x)).collect(Collectors.toList());
	}
}
