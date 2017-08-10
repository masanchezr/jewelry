package com.je.services.entrymoney;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.je.dbaccess.entities.EntryMoneyEntity;
import com.je.services.dailies.Daily;

public interface EntryMoneyService {

	public Daily saveEntryMoney(String user, BigDecimal amount);

	public List<EntryMoneyEntity> findByDates(Date from, Date until);
}
