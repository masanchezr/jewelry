package com.atmj.jsboot.services.holidays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.HolidayEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.repositories.HolidayRepository;
import com.atmj.jsboot.dbaccess.repositories.PlaceRepository;
import com.atmj.jsboot.services.converters.HolidayEntityConverter;
import com.atmj.jsboot.utils.date.DateUtil;
import com.atmj.jsboot.utils.string.Util;

/**
 * The Class HolidayServiceImpl.
 */
@Service
public class HolidayServiceImpl implements HolidayService {

	/** The holiday repository. */
	@Autowired
	private HolidayRepository holidayRepository;

	@Autowired
	private PlaceRepository placeRepository;

	/** The mapper. */
	@Autowired
	private HolidayEntityConverter converter;

	public void addHoliday(Holiday holiday) {
		holidayRepository.save(converter.convertToEntity(holiday));
	}

	public void addHolidayAllPlaces(Holiday holiday) {
		List<PlaceEntity> place = placeRepository.findAll();
		Iterator<PlaceEntity> iplace = place.iterator();
		while (iplace.hasNext()) {
			HolidayEntity entity = converter.convertToEntity(holiday);
			entity.setPlace(iplace.next());
			holidayRepository.save(entity);
		}
	}

	public List<Holiday> findAll() {
		Iterable<HolidayEntity> holidays = holidayRepository.findAll();
		Iterator<HolidayEntity> iholidays = holidays.iterator();
		List<Holiday> lholidays = new ArrayList<>();
		while (iholidays.hasNext()) {
			lholidays.add(converter.convertToDTO(iholidays.next()));
		}
		return lholidays;
	}

	public boolean existsHoliday(Holiday holiday) {
		HolidayEntity entity = holidayRepository.findByHolidayAndPlace(DateUtil.getDate(holiday.getDateholiday()),
				holiday.getPlace());
		boolean exists = false;
		if (entity != null) {
			exists = true;
		}
		return exists;
	}

	public List<Holiday> findByBetweenDates(Holiday holiday) {
		Date from = DateUtil.getDate(holiday.getDateholiday());
		Date until = new Date();
		String suntil = holiday.getUntildate();
		if (!Util.isEmpty(suntil)) {
			until = DateUtil.getDate(suntil);
		}
		List<HolidayEntity> iholidays = holidayRepository.findByHolidayBetween(from, until);
		if (iholidays != null) {
			return converter.entitiesToDTOs(iholidays);
		} else {
			return Collections.emptyList();
		}
	}
}
