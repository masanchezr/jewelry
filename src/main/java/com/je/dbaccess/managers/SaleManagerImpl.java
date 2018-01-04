package com.je.dbaccess.managers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.je.dbaccess.entities.AddressEntity;
import com.je.dbaccess.entities.Addresses;
import com.je.dbaccess.entities.BatteryEntity;
import com.je.dbaccess.entities.CancelSaleEntity;
import com.je.dbaccess.entities.ClientEntity;
import com.je.dbaccess.entities.DiscountEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.RecordingEntity;
import com.je.dbaccess.entities.SaleEntity;
import com.je.dbaccess.entities.SalePostponedEntity;
import com.je.dbaccess.entities.SalesJewels;
import com.je.dbaccess.entities.StrapEntity;
import com.je.dbaccess.repositories.BatteriesRepository;
import com.je.dbaccess.repositories.CancelSaleRepository;
import com.je.dbaccess.repositories.DiscountsRepository;
import com.je.dbaccess.repositories.JewelRepository;
import com.je.dbaccess.repositories.RecordingRepository;
import com.je.dbaccess.repositories.SaleRepository;
import com.je.dbaccess.repositories.SalesPostponedRepository;
import com.je.dbaccess.repositories.StrapsRepository;

/**
 * The Class SaleManagerImpl.
 */
public class SaleManagerImpl implements SaleManager {

	@Autowired
	private DiscountsRepository discountsRepository;

	/** The sale repository. */
	@Autowired
	private SaleRepository saleRepository;

	/** The jewel repository. */
	@Autowired
	private JewelRepository jewelRepository;

	/** The cancel sale repository. */
	@Autowired
	private CancelSaleRepository cancelSaleRepository;

	@Autowired
	private BatteriesRepository batteriesRepository;

	@Autowired
	private StrapsRepository strapsRepository;

	@Autowired
	private RecordingRepository recordingRepository;

	@Autowired
	private SalesPostponedRepository salespostponedrepository;

	@Override
	@Transactional
	public Long buy(SaleEntity sale) {
		List<SalesJewels> lsj = new ArrayList<SalesJewels>();
		Iterator<SalesJewels> isj = sale.getSjewels().iterator();
		Long orderNumber = null;
		while (isj.hasNext()) {
			SalesJewels sj = isj.next();
			JewelEntity je = jewelRepository.findById(sj.getJewelEntity().getIdjewel()).get();
			if (je != null) {
				System.out.println("jewel no es nulo");
				je.setActive(false);
				je.setSaledate(sale.getCreationdate());
				sj.setJewelEntity(je);
			} else {
				System.out.println("jewel es nulo");
			}
			System.out.println("antes de guardar sj");
			lsj.add(sj);
		}
		sale.setSjewels(lsj);
		// podría devolver la compra entera, de momento no me hace falta, solo
		// voy a mandar el numero de pedido
		sale = saleRepository.save(sale);
		if (sale != null) {
			orderNumber = sale.getIdsale();
		}
		return orderNumber;
	}

	@Override
	public Iterable<SaleEntity> searchAllSales() {
		return saleRepository.findAll();
	}

	@Override
	public Iterable<SaleEntity> searchByDate(Date date) {
		return saleRepository.findByDate(date);
	}

	@Override
	public Addresses searchAddressByClient(ClientEntity client) {
		Addresses addresses = null;
		Iterable<SaleEntity> sales = saleRepository.findByClient(client);
		if (sales != null && sales.iterator().hasNext()) {
			List<AddressEntity> addressesMailing = new ArrayList<AddressEntity>();
			List<AddressEntity> addressesBilling = new ArrayList<AddressEntity>();
			addresses = new Addresses();
			Iterator<SaleEntity> isales = sales.iterator();
			SaleEntity sale;
			AddressEntity addressbilling;
			while (isales.hasNext()) {
				sale = isales.next();
				addressesMailing.add(sale.getAddressmailing());
				addressbilling = sale.getAddressinvoice();
				if (addressbilling != null) {
					addressesBilling.add(addressbilling);
				}
			}
			addresses.setAdressesbilling(addressesBilling);
			addresses.setAdressesmailing(addressesMailing);
		}
		return addresses;
	}

	@Override
	public List<SaleEntity> searchByCreationDateAndPlace(Date date, PlaceEntity place) {
		return saleRepository.findByCreationdateAndPlace(date, place);
	}

	@Override
	public boolean cancelSale(CancelSaleEntity cancel, List<SalesJewels> salesjewels, DiscountEntity discount) {
		Iterator<SalesJewels> isalesjewels = salesjewels.iterator();
		JewelEntity jewel;
		SalesJewels entity;
		boolean exit = true;
		while (isalesjewels.hasNext() && exit) {
			entity = isalesjewels.next();
			jewel = entity.getJewelEntity();
			if (jewel.getActive()) {
				exit = false;
			} else {
				jewel.setActive(true);
				jewelRepository.save(jewel);
			}
		}
		if (exit) {
			cancelSaleRepository.save(cancel);
			if (discount != null) {
				discount.setDiscount(cancel.getAmount());
				discountsRepository.save(discount);
			}
		}
		return exit;
	}

	/**
	 * Cancel sale.
	 *
	 * @param sale
	 *            the sale
	 * @param idjewels
	 *            the idjewels
	 * @return true, if successful
	 */
	@Override
	public boolean cancelParcialSale(CancelSaleEntity cancel, List<JewelEntity> jewelsToCancel,
			DiscountEntity discount) {
		Iterator<JewelEntity> isalesjewels = jewelsToCancel.iterator();
		JewelEntity jewel;
		boolean exit = true;
		BigDecimal amount = BigDecimal.ZERO;
		Long idjewel;
		while (isalesjewels.hasNext() && exit) {
			jewel = isalesjewels.next();
			idjewel = jewel.getIdjewel();
			if (idjewel != null) {
				jewel = jewelRepository.findById(idjewel).get();
				if (!jewel.getActive()) {
					amount = amount.add(jewel.getPrice());
					jewel.setActive(true);
					jewelRepository.save(jewel);
					exit = true;
				} else {
					exit = false;
				}
			}
		}
		if (exit) {
			cancel.getSpayments().get(0).setAmount(amount);
			cancel.setAmount(amount);
			cancelSaleRepository.save(cancel);
			if (discount != null) {
				discount.setDiscount(amount);
				discountsRepository.save(discount);
			}
		}
		return exit;
	}

	@Override
	public SaleEntity searchByNumsaleAndPlace(Long numsale, Long idplace) {
		PlaceEntity place = new PlaceEntity();
		place.setIdplace(idplace);
		return saleRepository.findByNumsaleAndPlace(numsale, place);
	}

	@Override
	public Iterable<SaleEntity> searchByDatesAndPayment(Date from, Date until, PaymentEntity pay) {
		return saleRepository.findByCreationdateBetweenPay(from, until, pay);
	}

	@Override
	public Map<String, Object> searchByDatesAndPlace(Date from, Date until, PlaceEntity place) {
		List<SaleEntity> sales = new ArrayList<SaleEntity>();
		List<CancelSaleEntity> cancels;
		Iterable<SaleEntity> isales = saleRepository.findByCreationdateBetweenAndPlaceOrderByIdsaleAsc(from, until,
				place);
		Iterator<SaleEntity> itsales = isales.iterator();
		Map<String, Object> map = new HashMap<String, Object>();
		SaleEntity sale;
		CancelSaleEntity cancelSale;
		Long idsale;
		boolean iscancel = false;
		BigDecimal costjewel, cost = BigDecimal.ZERO, total = BigDecimal.ZERO;
		while (itsales.hasNext()) {
			sale = itsales.next();
			idsale = sale.getIdsale();
			cancels = cancelSaleRepository.findByNumsaleAndPlace(idsale, place);
			if (cancels != null && !cancels.isEmpty()) {
				Iterator<CancelSaleEntity> icancels = cancels.iterator();
				while (icancels.hasNext()) {
					cancelSale = icancels.next();
					if (cancelSale.getNumsale().equals(idsale)) {
						iscancel = true;
						total = total.subtract(cancelSale.getAmount());
					}
				}
			}
			if (!iscancel) {
				sales.add(sale);
				total = total.add(sale.getTotal());
			}
			iscancel = false;
			List<SalesJewels> sjewels = sale.getSjewels();
			Iterator<SalesJewels> isjewels = sjewels.iterator();
			while (isjewels.hasNext()) {
				costjewel = isjewels.next().getJewelEntity().getCost();
				if (costjewel != null) {
					cost = cost.add(costjewel);
				}
			}
		}
		map.put("sales", sales);
		map.put("total", total);
		map.put("cost", cost);
		return map;
	}

	@Override
	public List<Long> calculateNumberMissing(Long numFrom, Long numUntil, PlaceEntity place) {
		List<Long> numbers = new ArrayList<Long>();
		SaleEntity sale;
		for (long i = numFrom; i <= numUntil; i++) {
			sale = saleRepository.findByNumsaleAndPlace(i, place);
			if (sale == null) {
				List<BatteryEntity> batteries = batteriesRepository.findByNumsaleAndPlace(i, place);
				if (batteries == null || batteries.isEmpty()) {
					List<StrapEntity> straps = strapsRepository.findByNumsaleAndPlace(i, place);
					if (straps == null || straps.isEmpty()) {
						List<RecordingEntity> recordings = recordingRepository.findByNumsaleAndPlace(i, place);
						if (recordings == null || recordings.isEmpty()) {
							DiscountEntity discount = discountsRepository.findById(new Long(i)).orElse(null);
							if (discount == null) {
								numbers.add(i);
							}
						}
					}
				}
			}
		}
		return numbers;
	}

	@Override
	public SaleEntity searchByPK(Long idsale) {
		return saleRepository.findById(idsale).get();
	}

	@Override
	public Long numsalepostponed(PlaceEntity place) {
		Calendar c = Calendar.getInstance();
		Long num = null;
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 6);
		List<SaleEntity> sales = saleRepository.findByPlaceAndCreationdateBeforeAndNumsaleLessThan(place, c.getTime(),
				0L);
		Iterator<SaleEntity> isales = sales.iterator();
		SaleEntity sale;
		SalePostponedEntity sp;
		while (isales.hasNext() && num == null) {
			sale = isales.next();
			num = sale.getNumsale() + 1;
			sale = saleRepository.findByNumsaleAndPlace(num, place);
			if (sale == null) {
				sp = salespostponedrepository.findById(Math.abs(num)).get();
				if (sp != null) {
					num = null;
				}
			} else {
				num = null;
			}
		}
		return num;
	}
}