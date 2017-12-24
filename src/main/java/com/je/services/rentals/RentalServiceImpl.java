package com.je.services.rentals;

import java.util.Calendar;
import java.util.Date;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.RentalEntity;
import com.je.dbaccess.repositories.PlaceUserRepository;
import com.je.dbaccess.repositories.RentalsRepository;
import com.je.services.dailies.Daily;
import com.je.services.dailies.DailyService;
import com.je.utils.date.DateUtil;

public class RentalServiceImpl implements RentalService {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	@Autowired
	private RentalsRepository rentalsRepository;

	@Autowired
	private PlaceUserRepository placeUserRepository;

	@Autowired
	private Mapper mapper;

	@Override
	public Daily saveRental(Rental rental) {
		Date date = DateUtil.getDate(rental.getRentaldate());
		PlaceEntity place = placeUserRepository.findByUsername(rental.getUser()).get(0).getPlace();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String id = String.valueOf(calendar.get(Calendar.YEAR)).concat(String.valueOf(calendar.get(Calendar.MONTH) + 1))
				.concat(String.valueOf(place.getIdplace()));
		RentalEntity entity = new RentalEntity();
		entity.setCreationdate(new Date());
		entity.setIdrental(Long.valueOf(id));
		entity.setPlace(place);
		mapper.map(rental, entity);
		rentalsRepository.save(entity);
		return dailyService.getDaily(new Date(), place, null);
	}

	@Override
	public boolean existsLocalRental(Rental rental) {
		boolean exists = false;
		Date date = DateUtil.getDate(rental.getRentaldate());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String id = String.valueOf(calendar.get(Calendar.YEAR)).concat(String.valueOf(calendar.get(Calendar.MONTH) + 1))
				.concat(String
						.valueOf(placeUserRepository.findByUsername(rental.getUser()).get(0).getPlace().getIdplace()));
		RentalEntity rentalEntity = rentalsRepository.findById(Long.valueOf(id)).get();
		if (rentalEntity != null) {
			exists = true;
		}
		return exists;
	}

}
