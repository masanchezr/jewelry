package com.je.dbaccess.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.je.dbaccess.entities.AddressEntity;
import com.je.dbaccess.entities.Addresses;
import com.je.dbaccess.entities.ClientEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.SaleEntity;
import com.je.dbaccess.entities.SalesJewels;
import com.je.dbaccess.entities.SalesPayments;
import com.je.dbaccess.managers.SaleManager;

/**
 * The Class SaleManagerTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class SaleManagerTest {

	/** The sale manager. */
	@Autowired
	private SaleManager saleManager;

	/**
	 * Save test.
	 */
	@Test
	public void saveTest() {
		SalesJewels saleJewels = new SalesJewels();
		SaleEntity sale = new SaleEntity();
		JewelEntity jewel = new JewelEntity();
		PaymentEntity payment = new PaymentEntity();
		PlaceEntity place = new PlaceEntity();
		List<SalesJewels> jewels = new ArrayList<SalesJewels>();
		List<SalesPayments> payments = new ArrayList<SalesPayments>();
		SalesPayments sp = new SalesPayments();
		sp.setPay(payment);
		sp.setSale(sale);
		payments.add(sp);
		place.setIdplace(24003L);
		jewel.setIdjewel(2L);
		sale.setIdsale(97L);
		payment.setIdpayment(2L);
		saleJewels.setJewelEntity(jewel);
		sale.setSpayments(payments);
		sale.setCreationdate(new Date());
		saleJewels.setSale(sale);
		jewels.add(saleJewels);
		sale.setSjewels(jewels);
		sale.setTotal(BigDecimal.valueOf(189));
		sale.setPlace(place);
		// saleManager.buy(sale);
		System.out.println("fin savetest");
	}

	/**
	 * Find all test.
	 */
	@Test
	public void findAllTest() {
		Iterable<SaleEntity> sales = saleManager.searchAllSales();
		if (sales != null) {
			Iterator<SaleEntity> isales = sales.iterator();
			while (isales != null && isales.hasNext()) {
				SaleEntity sale = isales.next();
				System.out.println("ID: " + sale.getIdsale());
				List<SalesJewels> jewels = sale.getSjewels();
				if (jewels != null) {
					Iterator<SalesJewels> isj = jewels.iterator();
					while (isj != null && isj.hasNext()) {
						System.out.println("joya:" + isj.next().getJewelEntity().getName());
					}
				}
			}
		}
		System.out.println("fin findAllTest");
	}

	/**
	 * Search address by client.
	 */
	@Test
	public void searchAddressByClient() {
		ClientEntity client = new ClientEntity();
		client.setNifclient("71222013Y");
		Addresses addresses = saleManager.searchAddressByClient(client);
		if (addresses != null) {
			List<AddressEntity> addressesmailing = addresses.getAdressesmailing();
			if (addressesmailing != null) {
				Iterator<AddressEntity> iamailing = addressesmailing.iterator();
				while (iamailing.hasNext()) {
					System.out.println(iamailing.next().getAddress());
				}
				System.out.println("size:" + addressesmailing.size());
			} else {
				System.out.println("nulo1");
			}
		} else {
			System.out.println("nulo2");
		}
	}

	@Test
	public void numsalepostponedTest() {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(28017L);
		System.out.println(saleManager.numsalepostponed(place));
	}
}
