package com.je.services.entrymoney;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.EntryMoneyEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.repositories.EntryMoneyRepository;
import com.je.dbaccess.repositories.PlaceUserRepository;
import com.je.services.dailies.Daily;
import com.je.services.dailies.DailyService;

public class EntryMoneyServiceImpl implements EntryMoneyService {

	@Autowired
	private DailyService dailyService;

	@Autowired
	private EntryMoneyRepository entryMoneyRepository;

	@Autowired
	private PlaceUserRepository placeUserRepository;

	public Daily saveEntryMoney(String user, BigDecimal amount) {
		EntryMoneyEntity entrymoney = new EntryMoneyEntity();
		PlaceEntity place = placeUserRepository.findByUsername(user).get(0).getPlace();
		entrymoney.setAmount(amount);
		entrymoney.setCreationdate(new Date());
		entrymoney.setPlace(place);
		entryMoneyRepository.save(entrymoney);
		return dailyService.getDaily(new Date(), place, null);
	}

	public List<EntryMoneyEntity> findByDates(Date from, Date until) {
		return entryMoneyRepository.findByCreationdateBetween(from, until);
	}
}
