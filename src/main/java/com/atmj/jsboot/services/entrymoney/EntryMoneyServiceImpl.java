package com.atmj.jsboot.services.entrymoney;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.EntryMoneyEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.repositories.EntryMoneyRepository;
import com.atmj.jsboot.dbaccess.repositories.PlaceUserRepository;
import com.atmj.jsboot.dbaccess.repositories.UsersRepository;
import com.atmj.jsboot.services.dailies.Daily;
import com.atmj.jsboot.services.dailies.DailyService;
import com.atmj.jsboot.utils.date.DateUtil;

@Service
public class EntryMoneyServiceImpl implements EntryMoneyService {

	private final DailyService dailyService;

	private final EntryMoneyRepository entryMoneyRepository;

	private final PlaceUserRepository placeUserRepository;

	private final UsersRepository usersRepository;

	public EntryMoneyServiceImpl(final DailyService dailyService, final EntryMoneyRepository entryMoneyRepository,
			final PlaceUserRepository placeUserRepository, final UsersRepository usersRepository) {
		this.dailyService = dailyService;
		this.entryMoneyRepository = entryMoneyRepository;
		this.placeUserRepository = placeUserRepository;
		this.usersRepository = usersRepository;
	}

	public Daily saveEntryMoney(String user, BigDecimal amount) {
		EntryMoneyEntity entrymoney = new EntryMoneyEntity();
		PlaceEntity place = placeUserRepository.findByUser(usersRepository.findByUsername(user)).get(0).getPlace();
		entrymoney.setAmount(amount);
		entrymoney.setCreationdate(new Date());
		entrymoney.setPlace(place);
		entryMoneyRepository.save(entrymoney);
		return dailyService.getDaily(DateUtil.getDateFormated(new Date()), place, null);
	}

	public List<EntryMoneyEntity> findByDates(Date from, Date until) {
		return entryMoneyRepository.findByCreationdateBetween(from, until);
	}
}
