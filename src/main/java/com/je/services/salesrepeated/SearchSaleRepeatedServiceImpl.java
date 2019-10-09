package com.je.services.salesrepeated;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.BatteryEntity;
import com.je.dbaccess.entities.RecordingEntity;
import com.je.dbaccess.entities.SaleEntity;
import com.je.dbaccess.entities.StrapEntity;
import com.je.dbaccess.repositories.BatteriesRepository;
import com.je.dbaccess.repositories.DiscountsRepository;
import com.je.dbaccess.repositories.RecordingRepository;
import com.je.dbaccess.repositories.SalesRepository;
import com.je.dbaccess.repositories.StrapsRepository;

public class SearchSaleRepeatedServiceImpl implements SearchSaleRepeatedService {

	@Autowired
	private BatteriesRepository batteriesRepository;

	@Autowired
	private DiscountsRepository discountsRepository;

	@Autowired
	private RecordingRepository recordingRepository;

	@Autowired
	private SalesRepository saleRepository;

	@Autowired
	private StrapsRepository strapsRepository;

	public boolean isSaleRepeated(Long num) {
		List<BatteryEntity> batteries = batteriesRepository.findByNumsale(num);
		List<RecordingEntity> recordings = recordingRepository.findByNumsale(num);
		List<SaleEntity> sales = saleRepository.findByNumsale(num);
		List<StrapEntity> straps = strapsRepository.findByNumsale(num);
		if ((batteries == null || batteries.isEmpty()) && (recordings == null || recordings.isEmpty())
				&& (sales == null || sales.isEmpty()) && (straps == null || straps.isEmpty())) {
			return discountsRepository.existsById(num);
		} else {
			return true;
		}
	}
}
