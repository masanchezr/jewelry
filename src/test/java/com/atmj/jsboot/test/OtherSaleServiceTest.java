package com.atmj.jsboot.test;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.jsboot.dbaccess.entities.OtherSaleEntity;
import com.atmj.jsboot.dbaccess.entities.PaymentEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.entities.TypeOfSaleEntity;
import com.atmj.jsboot.services.othersale.OtherSaleService;

@SpringBootTest
public class OtherSaleServiceTest {
	
	@Autowired
	private OtherSaleService othersaleservice;
	
	@Test
	public void saveTest() {
		OtherSaleEntity sale=new OtherSaleEntity();
		PaymentEntity pay=new PaymentEntity();
		PlaceEntity place =new PlaceEntity();
		TypeOfSaleEntity type =new TypeOfSaleEntity();
		type.setIdtypeofsale(1L);
		place.setIdplace(13700L);
		pay.setIdpayment(2L);
		sale.setAmount(new BigDecimal(600000));
		sale.setCreationdate(new Date());
		sale.setDescription("pruebas");
		sale.setNumsale(1L);
		sale.setPay(pay);
		sale.setPlace(place);
		sale.setType(type);
		othersaleservice.save(sale);
	}

}
