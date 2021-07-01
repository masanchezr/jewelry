package com.je.jsboot.services.rentals;

import java.util.Calendar;
import java.util.Date;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.entities.RentalEntity;
import com.je.jsboot.dbaccess.repositories.PlaceUserRepository;
import com.je.jsboot.dbaccess.repositories.RentalsRepository;
import com.je.jsboot.dbaccess.repositories.UsersRepository;
import com.je.jsboot.services.dailies.Daily;
import com.je.jsboot.services.dailies.DailyService;
import com.je.jsboot.utils.date.DateUtil;

@Service
public class RentalServiceImpl implements RentalService {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	@Autowired
	private RentalsRepository rentalsRepository;

	@Autowired
	private PlaceUserRepository placeUserRepository;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private Mapper mapper;

	@Override
	public Daily saveRental(Rental rental) {
		Date date = DateUtil.getDate(rental.getRentaldate());
		PlaceEntity place = placeUserRepository.findByUser(usersRepository.findByUsername(rental.getUser())).get(0)
				.getPlace();
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
		return dailyService.getDaily(DateUtil.getDateFormated(new Date()), place, null);
	}

	@Override
	public boolean existsLocalRental(Rental rental) {
		Date date = DateUtil.getDate(rental.getRentaldate());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String id = String.valueOf(calendar.get(Calendar.YEAR)).concat(String.valueOf(calendar.get(Calendar.MONTH) + 1))
				.concat(String.valueOf(placeUserRepository.findByUser(usersRepository.findByUsername(rental.getUser()))
						.get(0).getPlace().getIdplace()));
		return rentalsRepository.existsById(Long.valueOf(id));
	}

}
