package com.je.services.holidays;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.HolidayEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.repositories.HolidayRepository;
import com.je.dbaccess.repositories.PlaceRepository;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

/**
 * The Class HolidayServiceImpl.
 */
public class HolidayServiceImpl implements HolidayService {

	/** The holiday repository. */
	@Autowired
	private HolidayRepository holidayRepository;

	@Autowired
	private PlaceRepository placeRepository;

	/** The mapper. */
	@Autowired
	private Mapper mapper;

	public void addHoliday(Holiday holiday) {
		holidayRepository.save(mapper.map(holiday, HolidayEntity.class));
	}

	public void addHolidayAllPlaces(Holiday holiday) {
		Iterable<PlaceEntity> place = placeRepository.findAll();
		Iterator<PlaceEntity> iplace = place.iterator();
		while (iplace.hasNext()) {
			HolidayEntity entity = mapper.map(holiday, HolidayEntity.class);
			entity.setPlace(iplace.next());
			holidayRepository.save(entity);
		}
	}

	public List<Holiday> findAll() {
		Iterable<HolidayEntity> holidays = holidayRepository.findAll();
		return mapper(holidays);
	}

	public boolean existsHoliday(Holiday holiday) {
		HolidayEntity entity = holidayRepository.findByHolidayAndPlace(
				DateUtil.getDate(holiday.getHoliday()),
				mapper.map(holiday.getPlace(), PlaceEntity.class));
		boolean exists = false;
		if (entity != null) {
			exists = true;
		}
		return exists;
	}

	public List<Holiday> findByBetweenDates(Holiday holiday) {
		Date from = DateUtil.getDate(holiday.getHoliday());
		Date until = new Date();
		String suntil = holiday.getUntildate();
		if (!Util.isEmpty(suntil)) {
			until = DateUtil.getDate(suntil);
		}
		Iterable<HolidayEntity> iholidays = holidayRepository
				.findByHolidayBetween(from, until);
		if (iholidays != null) {
			return mapper(iholidays);
		} else {
			return null;
		}
	}

	/**
	 * Mapper.
	 *
	 * @param holidays
	 *            the holidays
	 * @return the list
	 */
	private List<Holiday> mapper(Iterable<HolidayEntity> holidays) {
		Iterator<HolidayEntity> iholidays = holidays.iterator();
		List<Holiday> lholidays = new ArrayList<Holiday>();
		while (iholidays.hasNext()) {
			lholidays.add(mapper.map(iholidays.next(), Holiday.class));
		}
		return lholidays;
	}
}
