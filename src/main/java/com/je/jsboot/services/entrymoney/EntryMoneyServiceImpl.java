package com.je.jsboot.services.entrymoney;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.je.jsboot.dbaccess.entities.EntryMoneyEntity;
import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.repositories.EntryMoneyRepository;
import com.je.jsboot.dbaccess.repositories.PlaceUserRepository;
import com.je.jsboot.services.dailies.Daily;
import com.je.jsboot.services.dailies.DailyService;
import com.je.jsboot.utils.date.DateUtil;

@Service
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
		return dailyService.getDaily(DateUtil.getDateFormated(new Date()), place, null);
	}

	public List<EntryMoneyEntity> findByDates(Date from, Date until) {
		return entryMoneyRepository.findByCreationdateBetween(from, until);
	}
}
