package com.je.jsboot.services.othersale;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.je.jsboot.dbaccess.entities.OtherSaleEntity;
import com.je.jsboot.dbaccess.entities.TypeOfSaleEntity;
import com.je.jsboot.dbaccess.repositories.OtherSaleRepository;
import com.je.jsboot.dbaccess.repositories.TypesOfSalesRepository;

@Service
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
