package com.je.services.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.entities.AddressEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.forms.Sale;
import com.je.services.sales.Addresses;
import com.je.services.sales.SaleService;
import com.je.utils.constants.Constants;

/**
 * The Class SaleServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
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
	 * Find all test.
	 */
	@Test
	public void findAllTest() {
		List<Sale> sales = saleService.searchAllSales();
		if (sales != null) {
			Iterator<Sale> isales = sales.iterator();
			while (isales.hasNext()) {
				Sale sale = isales.next();
				System.out.println("id de la operacion:" + sale.getIdsale() + " numero de joyas:"
						+ sale.getJewels().size() + " total:" + sale.getTotal());
				System.out.println("Joyas:");
				for (JewelEntity jewels : sale.getJewels()) {
					System.out.println("id: " + jewels.getIdjewel() + " descripcion: " + jewels.getDescription());
				}
			}
		}
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

	@Test
	public void searchByDatesAndPlaceTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(24002L);
		Map<String, Object> sales = saleService.searchByDatesAndPlace("01-04-2018", "11-07-2018", place);
		sales.get(Constants.SALES);
	}
}
