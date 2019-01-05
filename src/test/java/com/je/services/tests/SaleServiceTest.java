package com.je.services.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.AddressEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.forms.Sale;
import com.je.services.sales.Addresses;
import com.je.services.sales.SaleService;

/**
 * The Class SaleServiceTest.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class SaleServiceTest {

	/** The sale service. */
	@Autowired
	private SaleService saleService;

	/**
	 * Buy test.
	 */
	@Test
	// funciona
	public void buyTest() {
		Sale sale = new Sale();
		PaymentEntity payment = new PaymentEntity();
		List<JewelEntity> jewels = new ArrayList<JewelEntity>();
		JewelEntity jewel = new JewelEntity();
		jewel.setIdjewel(2342L);
		jewels.add(jewel);
		payment.setIdpayment(2L);
		sale.setIdsale(90L);
		sale.setJewels(jewels);
		// saleService.buy(sale);
	}

	/**
	 * Search address by client test.
	 */
	@Test
	public void searchAddressByClientTest() {
		Addresses addresses = saleService.searchAddressByClient("71222013Y");
		if (addresses != null) {
			List<AddressEntity> addressesmailing = addresses.getAddressesMailing();
			List<AddressEntity> addressesbilling = addresses.getAddressesBilling();
			if (addressesmailing != null) {
				Iterator<AddressEntity> iamailing = addressesmailing.iterator();
				while (iamailing.hasNext()) {
					System.out.println("address: " + iamailing.next().getAddress());
				}
			} else {
				System.out.println("nulo1");
			}
			if (addressesbilling != null) {
				Iterator<AddressEntity> iabilling = addressesbilling.iterator();
				while (iabilling.hasNext()) {
					System.out.println("address: " + iabilling.next().getAddress());
				}
			} else {
				System.out.println("nulo2");
			}
		}
	}

	/**
	 * Removes the sale.
	 */
	@Test
	public void removeSale() {
		Sale removeSaleForm = new Sale();
		removeSaleForm.setIdsale(123L);
		// saleService.removeSale(removeSaleForm);
	}

	@Test
	public void removeParcialSale() {
		Sale removeSaleForm = new Sale();
		removeSaleForm.setIdsale(123L);
	}
}
