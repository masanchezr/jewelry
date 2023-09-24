package com.atmj.jsboot.services.converters;

import java.util.Date;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atmj.jsboot.dbaccess.entities.ClientPawnEntity;
import com.atmj.jsboot.services.pawns.NewPawn;
import com.atmj.jsboot.utils.date.DateUtil;

@Component
public class ClientPawnEntityConverter {

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	Converter<String, Date> toDate = new AbstractConverter<String, Date>() {
		protected Date convert(String source) {
			if (source == null || source.isEmpty()) {
				return new Date();
			} else
				return DateUtil.getDate(source);
		}
	};

	public void convertToDTO(NewPawn pawn, ClientPawnEntity cpe) {
		mapper.addConverter(toDate);
		mapper.map(pawn, cpe);
	}
}
