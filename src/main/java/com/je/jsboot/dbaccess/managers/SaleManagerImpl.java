package com.je.jsboot.dbaccess.managers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.je.jsboot.dbaccess.entities.AddressEntity;
import com.je.jsboot.dbaccess.entities.Addresses;
import com.je.jsboot.dbaccess.entities.CancelSaleEntity;
import com.je.jsboot.dbaccess.entities.ClientEntity;
import com.je.jsboot.dbaccess.entities.DiscountEntity;
import com.je.jsboot.dbaccess.entities.JewelEntity;
import com.je.jsboot.dbaccess.entities.OtherSaleEntity;
import com.je.jsboot.dbaccess.entities.PaymentEntity;
import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.entities.SaleEntity;
import com.je.jsboot.dbaccess.entities.SalePostponedEntity;
import com.je.jsboot.dbaccess.entities.SalesJewels;
import com.je.jsboot.dbaccess.repositories.CancelSaleRepository;
import com.je.jsboot.dbaccess.repositories.DiscountsRepository;
import com.je.jsboot.dbaccess.repositories.JewelRepository;
import com.je.jsboot.dbaccess.repositories.OtherSaleRepository;
import com.je.jsboot.dbaccess.repositories.SalesPostponedRepository;
import com.je.jsboot.dbaccess.repositories.SalesRepository;
import com.je.jsboot.utils.constants.Constants;
import com.je.jsboot.utils.constants.ConstantsViews;

/**
 * The Class SaleManagerImpl.
 */
@Service
public class SaleManagerImpl implements SaleManager {

	/** The cancel sale repository. */
	@Autowired
	private CancelSaleRepository cancelSaleRepository;

	@Autowired
	private DiscountsRepository discountsRepository;

	/** The jewel repository. */
	@Autowired
	private JewelRepository jewelRepository;

	@Autowired
	private OtherSaleRepository othersalerepository;

	/** The sale repository. */
	@Autowired
	private SalesRepository saleRepository;

	@Autowired
	private SalesPostponedRepository salespostponedrepository;

	@Override
	@Transactional
	public Long buy(SaleEntity sale) {
		List<SalesJewels> lsj = new ArrayList<>();
		Iterator<SalesJewels> isj = sale.getSjewels().iterator();
		while (isj.hasNext()) {
			SalesJewels sj = isj.next();
			JewelEntity je = jewelRepository.findById(sj.getJewelEntity().getIdjewel()).orElse(null);
			if (je != null) {
				je.setActive(false);
				je.setSaledate(sale.getCreationdate());
				sj.setJewelEntity(je);
			}
			lsj.add(sj);
		}
		sale.setSjewels(lsj);
		// podría devolver la compra entera, de momento no me hace falta, solo
		// voy a mandar el numero de pedido
		sale = saleRepository.save(sale);
		return sale.getIdsale();
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
			List<AddressEntity> addressesMailing = new ArrayList<>();
			List<AddressEntity> addressesBilling = new ArrayList<>();
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
			if (Boolean.TRUE.equals(jewel.getActive())) {
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
	 * @param sale     the sale
	 * @param idjewels the idjewels
	 * @return true, if successful
	 */
	@Override
	public boolean cancelParcialSale(CancelSaleEntity cancel, List<Long> jewelsToCancel, DiscountEntity discount) {
		Iterator<Long> isalesjewels = jewelsToCancel.iterator();
		Long idjewel;
		JewelEntity jewel;
		boolean exit = true;
		BigDecimal amount = BigDecimal.ZERO;
		while (isalesjewels.hasNext() && exit) {
			idjewel = isalesjewels.next();
			jewel = jewelRepository.findById(idjewel).orElse(null);
			if (jewel != null && !jewel.getActive()) {
				amount = amount.add(jewel.getPrice());
				jewel.setActive(true);
				jewelRepository.save(jewel);
				exit = true;
			} else {
				exit = false;
			}
		}
		if (exit) {
			cancel.getSpayments().get(0).setAmount(amount);
			cancel.setAmount(amount);
			cancelSaleRepository.save(cancel);
			if (discount != null) {
				discount.setDiscount(amount);
				discount.setNumsalecancel(cancel.getNumsale());
				discountsRepository.save(discount);
			}
		}
		return exit;
	}

	@Override
	public boolean existSale(Long numsale) {
		List<SaleEntity> sales = saleRepository.findByNumsale(numsale);
		boolean exists = true;
		if ((sales == null || sales.isEmpty()) && checkAllSales(numsale) != 0) {
			exists = false;
		}
		return exists;
	}

	@Override
	public Iterable<SaleEntity> searchByDatesAndPayment(Date from, Date until, PaymentEntity pay) {
		return saleRepository.findByCreationdateBetweenPay(from, until, pay);
	}

	@Override
	public Map<String, Object> searchByDatesAndPlace(Date from, Date until, PlaceEntity place) {
		List<SaleEntity> sales = new ArrayList<>();
		List<CancelSaleEntity> cancels;
		Iterable<SaleEntity> isales = saleRepository.findByCreationdateBetweenAndPlaceOrderByIdsaleAsc(from, until,
				place);
		List<SalePostponedEntity> salespostponed = salespostponedrepository.findByDateretiredBetweenAndPlace(from,
				until, place);
		List<OtherSaleEntity> othersales = othersalerepository.findByCreationdateBetweenAndPlace(from, until, place);
		BigDecimal sumsalespostponed = salespostponedrepository.sumDateretiredBetweenAndPlace(from, until, place);
		BigDecimal sumothersales = othersalerepository.sumCreationdateBetweenAndPlace(from, until, place);
		Iterator<SaleEntity> itsales = isales.iterator();
		Map<String, Object> map = new HashMap<>();
		SaleEntity sale;
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal cost = BigDecimal.ZERO;
		while (itsales.hasNext()) {
			sale = itsales.next();
			cancels = cancelSaleRepository.findByNumsaleAndPlace(sale.getNumsale(), place);
			if (cancels == null || cancels.isEmpty()) {
				List<SalesJewels> sjewels = sale.getSjewels();
				Iterator<SalesJewels> isjewels = sjewels.iterator();
				cost = cost.add(getCost(isjewels));
				sales.add(sale);
				total = total.add(sale.getTotal());
			}
		}
		if (sumsalespostponed != null) {
			total = total.add(sumsalespostponed);
		}
		if (sumothersales != null) {
			total = total.add(sumothersales);
		}
		map.put(Constants.SALES, sales);
		map.put(Constants.SALESPOSTPONED, salespostponed);
		map.put("othersales", othersales);
		map.put(ConstantsViews.TOTAL, total);
		map.put("cost", cost);
		return map;
	}

	private BigDecimal getCost(Iterator<SalesJewels> isjewels) {
		BigDecimal costjewel;
		BigDecimal cost = BigDecimal.ZERO;
		while (isjewels.hasNext()) {
			costjewel = isjewels.next().getJewelEntity().getCost();
			if (costjewel != null) {
				cost = cost.add(costjewel);
			}
		}
		return cost;
	}

	@Override
	public List<Long> calculateNumberMissing(Long numFrom, Long numUntil) {
		List<Long> numbers = new ArrayList<>();
		List<SaleEntity> sales;
		for (long i = numFrom; i <= numUntil; i++) {
			sales = saleRepository.findByNumsale(i);
			if ((sales == null || sales.isEmpty()) && checkAllSales(i) != 0) {
				numbers.add(i);
			}
		}
		return numbers;
	}

	private long checkAllSales(long i) {
		long num = 0;
		List<OtherSaleEntity> othersales = othersalerepository.findByNumsale(i);
		if (othersales == null || othersales.isEmpty()) {
			DiscountEntity discount = discountsRepository.findById(i).orElse(null);
			if (discount == null) {
				num = i;
			}
		}
		return num;
	}

	@Override
	public SaleEntity searchByPK(Long idsale) {
		return saleRepository.findById(idsale).orElse(null);
	}

	@Override
	public SaleEntity searchByNumsaleAndYear(Long numsale, int year) {
		return saleRepository.findByNumsaleAndYear(numsale, year);
	}
}