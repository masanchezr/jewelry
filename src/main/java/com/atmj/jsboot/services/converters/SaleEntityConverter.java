package com.atmj.jsboot.services.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atmj.jsboot.dbaccess.entities.SaleEntity;
import com.atmj.jsboot.forms.Sale;
import com.atmj.jsboot.utils.date.DateUtil;

@Component
public class SaleEntityConverter {

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	public Sale convertToDTO(SaleEntity entity) {
		Sale sale = mapper.map(entity, Sale.class);
		sale.setSaledate(DateUtil.getStringDateddMMyyyy(entity.getCreationdate()));
		return sale;
	}

}
