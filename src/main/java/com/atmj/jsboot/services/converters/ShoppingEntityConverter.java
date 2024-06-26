package com.atmj.jsboot.services.converters;

import java.util.Date;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atmj.jsboot.dbaccess.entities.ShoppingEntity;
import com.atmj.jsboot.services.shoppings.Shopping;
import com.atmj.jsboot.utils.date.DateUtil;

@Component
public class ShoppingEntityConverter {

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	Converter<String, Date> toDate = new AbstractConverter<String, Date>() {
		protected Date convert(String source) {
			if (source == null || source.isEmpty()) {
				return new Date();
			} else
				return DateUtil.getDate(source);
		}
	};

	public Shopping convertToShopping(ShoppingEntity entity) {
		Shopping shop = mapper.map(entity, Shopping.class);
		shop.setId(entity.getIdshop());
		shop.setAmount(entity.getTotalamount().toString());
		return shop;
	}

	public ShoppingEntity convertToShoppingEntity(Shopping shop) {
		mapper.addConverter(toDate);
		ShoppingEntity entity = mapper.map(shop, ShoppingEntity.class);
		entity.setIdshop(shop.getId());
		return entity;
	}
}
