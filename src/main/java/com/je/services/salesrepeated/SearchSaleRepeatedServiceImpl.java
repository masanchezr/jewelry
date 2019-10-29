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

	public boolean isSaleRepeated(Long num) {
		List<SaleEntity> sales = saleRepository.findByNumsale(num);
		List<OtherSaleEntity> othersales = othersaleRepository.findByNumsale(num);
		if ((sales == null || sales.isEmpty()) && (othersales == null || othersales.isEmpty())) {
			return discountsRepository.existsById(num);
		} else {
			return true;
		}
	}
}
