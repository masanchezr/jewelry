package com.je.jsboot.services.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.jsboot.dbaccess.entities.HolidayEntity;
import com.je.jsboot.services.holidays.Holiday;
import com.je.jsboot.utils.date.DateUtil;

@Component
public class HolidayEntityConverter {

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	public HolidayEntity convertToEntity(Holiday holiday) {
		HolidayEntity entity = mapper.map(holiday, HolidayEntity.class);
		entity.setHoliday(DateUtil.getDate(holiday.getDateholiday()));
		return entity;
	}

	public Holiday convertToDTO(HolidayEntity entity) {
		return mapper.map(entity, Holiday.class);
	}

	public List<Holiday> entitiesToDTOs(List<HolidayEntity> entities) {
		return entities.stream().map(x -> convertToDTO(x)).collect(Collectors.toList());
	}

}
