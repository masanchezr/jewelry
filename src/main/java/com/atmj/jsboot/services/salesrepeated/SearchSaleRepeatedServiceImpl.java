package com.atmj.jsboot.services.salesrepeated;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.OtherSaleEntity;
import com.atmj.jsboot.dbaccess.entities.SaleEntity;
import com.atmj.jsboot.dbaccess.repositories.DiscountsRepository;
import com.atmj.jsboot.dbaccess.repositories.OtherSaleRepository;
import com.atmj.jsboot.dbaccess.repositories.SalesRepository;

@Service
public class SearchSaleRepeatedServiceImpl implements SearchSaleRepeatedService {

	@Autowired
	private DiscountsRepository discountsRepository;

	@Autowired
	private SalesRepository saleRepository;

	@Autowired
	private OtherSaleRepository othersaleRepository;

	public boolean isNotRepeatSale(Long num, int year) {
		SaleEntity sale = saleRepository.findByNumsaleAndYear(num, year);
		List<OtherSaleEntity> othersales = othersaleRepository.findByNumsale(num);
		return sale == null && othersales == null
				&& discountsRepository.findByNumsaleAndYear(num, year) == null;
	}
}
