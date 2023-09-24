package com.atmj.jsboot.services.entrymoney;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.atmj.jsboot.dbaccess.entities.EntryMoneyEntity;
import com.atmj.jsboot.services.dailies.Daily;

public interface EntryMoneyService {

	public Daily saveEntryMoney(String user, BigDecimal amount);

	public List<EntryMoneyEntity> findByDates(Date from, Date until);
}
