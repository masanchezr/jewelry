package com.je.jsboot.services.entrymoney;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.je.jsboot.dbaccess.entities.EntryMoneyEntity;
import com.je.jsboot.services.dailies.Daily;

public interface EntryMoneyService {

	public Daily saveEntryMoney(String user, BigDecimal amount);

	public List<EntryMoneyEntity> findByDates(Date from, Date until);
}
