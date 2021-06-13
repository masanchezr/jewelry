package com.je.jsboot.services.dailies;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.je.jsboot.dbaccess.entities.PlaceEntity;

public class DailyThread extends Thread {

	@Autowired
	private DailyService dailyService;

	private Date date;

	private PlaceEntity place;

	public DailyThread(DailyService dailyService, Date date, PlaceEntity place) {
		this.dailyService = dailyService;
		this.date = date;
		this.place = place;
	}

	@Override
	public void run() {
		dailyService.calculateDailies(date, place);
	}
}
