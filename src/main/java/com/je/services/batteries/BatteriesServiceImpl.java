package com.je.services.batteries;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.BatteryEntity;
import com.je.dbaccess.repositories.BatteriesRepository;

public class BatteriesServiceImpl implements BatteriesService {

	@Autowired
	private BatteriesRepository batteriesRepository;

	public void saveSaleBattery(BatteryEntity battery) {
		battery.setCreationdate(new Date());
		batteriesRepository.save(battery);
	}

}
