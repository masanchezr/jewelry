package com.atmj.jsboot.services.converters;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atmj.jsboot.dbaccess.entities.PawnEntity;
import com.atmj.jsboot.services.pawns.NewPawn;
import com.atmj.jsboot.services.pawns.Pawn;
import com.atmj.jsboot.utils.date.DateUtil;

@Component
public class PawnEntityConverter {

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	private static Logger log = LoggerFactory.getLogger(PawnEntityConverter.class);

	Converter<String, Date> toDate = new AbstractConverter<String, Date>() {
		protected Date convert(String source) {
			log.error(source);
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
