package com.je.jsboot.services.converters;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.jsboot.dbaccess.entities.PawnEntity;
import com.je.jsboot.services.pawns.NewPawn;
import com.je.jsboot.services.pawns.Pawn;
import com.je.jsboot.utils.date.DateUtil;

@Component
public class PawnEntityConverter {

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	Converter<String, Date> toDate = new AbstractConverter<String, Date>() {
		protected Date convert(String source) {
			System.out.println("Converter" + source);
			if (source == null || source.isEmpty()) {
				return new Date();
			} else
				return DateUtil.getDate(source);
		}
	};

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

	public PawnEntity convertToDTO(NewPawn pawn) {
		mapper.addConverter(toDate);
		return mapper.map(pawn, PawnEntity.class);
	}

	public List<Pawn> entitiesToPawns(List<PawnEntity> entities) {
		return entities.stream().map(this::convertToPawn).collect(Collectors.toList());
	}
}
