package com.je.dbaccess.test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.je.dbaccess.entities.AddressEntity;
import com.je.dbaccess.entities.ClientEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.SaleEntity;
import com.je.dbaccess.repositories.SaleRepository;
import com.je.utils.constants.ConstantsJsp;

/**
 * The Class SaleRepositoryTest.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class SaleRepositoryTest {

	/** The sale repository. */
	@Autowired
	private SaleRepository saleRepository;

	/**
	 * Save test.
	 */
	@Test
	public void saveTest() {
		ClientEntity user = new ClientEntity();
		user.setEmail("mangeles.sanchez0807@gmail.com");
		user.setName("maria");
		user.setNifclient("71222013Y");
		user.setCreationdate(new Date());
		SaleEntity sale = new SaleEntity();
		sale.setIdsale(1L);
		sale.setClient(user);
		sale.setCreationdate(new Date());
		AddressEntity addressmailing = new AddressEntity();
		addressmailing.setAddress("ezequiel solana 12");
		addressmailing.setDatecreation(new Date());
		addressmailing.setCity("Madrid");
		addressmailing.setPostalcode(28017L);
		addressmailing.setCountry("España");
		sale.setAddressmailing(addressmailing);
		sale.setTotal(BigDecimal.valueOf(400));
		// descomentar para que el test sea real
		// saleRepository.save(sale);
	}

	/**
	 * Find by client.
	 */
	@Test
	public void findByClient() {
		ClientEntity client = new ClientEntity();
		client.setNifclient("71222013Y");
		Iterable<SaleEntity> sales = saleRepository.findByClient(client);
		if (sales != null) {
			Iterator<SaleEntity> isales = sales.iterator();
			if (isales != null) {
				while (isales.hasNext()) {
					SaleEntity sale = isales.next();
					System.out.println(
							ConstantsJsp.IDSALE + sale.getIdsale() + "client:" + sale.getClient().getNifclient());
				}
			}
		} else {
			System.out.println("es vacio");
		}
	}

	@Test
	public void findByCreationdateBetweenPayTest() {
		PaymentEntity pay = new PaymentEntity();
		Calendar c = Calendar.getInstance();
		c.set(2015, 1, 1);
		pay.setIdpayment(3L);
		Iterable<SaleEntity> sales = saleRepository.findByCreationdateBetweenPay(c.getTime(), new Date(), pay);
		if (sales != null) {

		}
	}

	/*
	 * @Test public void deleteAll() { saleRepository.deleteAll(); }
	 * 
	 * @Test public void deleteBuy() { saleRepository.delete(91L); }
	 */

	@Test
	public void findByNumsaleLessThanTest() {
		PlaceEntity place = new PlaceEntity();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 6);
		place.setIdplace(28017L);
		System.out.println(c.getTime());
		List<SaleEntity> sales = saleRepository.findByPlaceAndCreationdateBeforeAndNumsaleLessThan(place, c.getTime(),
				0L);
		Iterator<SaleEntity> isales = sales.iterator();
		while (isales.hasNext()) {
			System.out.println(isales.next());
		}

	}

}
