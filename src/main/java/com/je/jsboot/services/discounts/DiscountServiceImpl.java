package com.je.jsboot.services.discounts;

import java.math.BigDecimal;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.je.jsboot.dbaccess.entities.DiscountEntity;
import com.je.jsboot.dbaccess.repositories.DiscountsRepository;

@Service
public class DiscountServiceImpl implements DiscountService {

	@Autowired
	private DiscountsRepository discountsRepository;

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	public void save(Discount discount) {
		DiscountEntity discountEntity = mapper.map(discount, DiscountEntity.class);
		discountEntity.setDiscount(new BigDecimal(discount.getSdiscount()));
		discountEntity.setCreationdate(new Date());
		discountsRepository.save(discountEntity);
	}
}
