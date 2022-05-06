package com.je.jsboot.services.converters;

import java.math.BigDecimal;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.jsboot.dbaccess.entities.DiscountEntity;
import com.je.jsboot.services.discounts.Discount;
import com.je.jsboot.utils.date.DateUtil;

@Component
public class DiscountEntityConverter {

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	public DiscountEntity convertToDiscount(Discount discount) {
		DiscountEntity discountEntity = mapper.map(discount, DiscountEntity.class);
		Date today = new Date();
		discountEntity.setDiscount(new BigDecimal(discount.getSdiscount()));
		discountEntity.setCreationdate(today);
		discountEntity.setYear(DateUtil.getYear(today));
		return discountEntity;
	}

	public Discount convertToEntity(DiscountEntity entity) {
		Discount discount = mapper.map(entity, Discount.class);
		discount.setSdiscount(entity.getDiscount().toString());
		return discount;
	}
}
