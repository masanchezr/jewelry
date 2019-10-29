package com.je.services.othersale;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.OtherSaleEntity;
import com.je.dbaccess.entities.TypeOfSaleEntity;
import com.je.dbaccess.repositories.OtherSaleRepository;
import com.je.dbaccess.repositories.TypesOfSalesRepository;

public class OtherSaleServiceImpl implements OtherSaleService {

	@Autowired
	private OtherSaleRepository recordingRepository;

	@Autowired
	private TypesOfSalesRepository typesOfSalesRepository;

	public void save(OtherSaleEntity recording) {
		recording.setCreationdate(new Date());
		recordingRepository.save(recording);
	}

	public Iterable<TypeOfSaleEntity> findAllTypes() {
		return typesOfSalesRepository.findAll();
	}

}
