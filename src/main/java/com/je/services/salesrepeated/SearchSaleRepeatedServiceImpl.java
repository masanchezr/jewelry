package com.je.services.salesrepeated;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.OtherSaleEntity;
import com.je.dbaccess.entities.SaleEntity;
import com.je.dbaccess.repositories.DiscountsRepository;
import com.je.dbaccess.repositories.OtherSaleRepository;
import com.je.dbaccess.repositories.SalesRepository;

public class SearchSaleRepeatedServiceImpl implements SearchSaleRepeatedService {

	@Autowired
	private DiscountsRepository discountsRepository;

	@Autowired
	private SalesRepository saleRepository;

	@Autowired
	private OtherSaleRepository othersaleRepository;

	public boolean isSaleRepeated(Long num, int year) {
		SaleEntity sales = saleRepository.findByNumsaleAndYear(num, year);
		List<OtherSaleEntity> othersales = othersaleRepository.findByNumsaleAndYear(num, year);
		return sales == null && (othersales == null || othersales.isEmpty())
				&& discountsRepository.findByNumsaleAndYear(num, year) != null;
	}
}
