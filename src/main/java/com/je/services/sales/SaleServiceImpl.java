package com.je.services.sales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.admin.forms.SearchMissingNumbers;
import com.je.dbaccess.entities.AddressEntity;
import com.je.dbaccess.entities.CancelSaleEntity;
import com.je.dbaccess.entities.CancelSalePaymentEntity;
import com.je.dbaccess.entities.ClientEntity;
import com.je.dbaccess.entities.DiscountEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.entities.SaleEntity;
import com.je.dbaccess.entities.SalesJewels;
import com.je.dbaccess.entities.SalesPayments;
import com.je.dbaccess.managers.SaleManager;
import com.je.dbaccess.managers.UsersManager;
import com.je.dbaccess.repositories.DiscountsRepository;
import com.je.forms.Sale;
import com.je.services.mails.MailService;
import com.je.services.users.Client;
import com.je.utils.constants.Constants;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

/**
 * The Class SaleServiceImpl.
 */
public class SaleServiceImpl implements SaleService {

	@Autowired
	private DiscountsRepository discountsRepository;

	/** The sale manager. */
	@Autowired
	private SaleManager saleManager;

	/** The users manager. */
	@Autowired
	private UsersManager usersManager;

	/** The mapper. */
	@Autowired
	private Mapper mapper;

	@Override
	public Long buy(Sale sale) {
		// Tengo que crear un saleentity y varios salesjewels
		SaleEntity saleEntity = new SaleEntity();
		MailService mailService;
		List<SalesJewels> salesJewels = new ArrayList<>();
		List<SalesPayments> payments = new ArrayList<>();
		Client saleclient = sale.getClient();
		saleEntity.setNumsale(sale.getNumsale());
		if (saleclient != null) {
			ClientEntity client = mapper.map(sale.getClient(), ClientEntity.class);
			client.setCreationdate(new Date());
			saleEntity.setClient(client);
		}
		if (Util.isEmpty(sale.getSaledate())) {
			saleEntity.setCreationdate(new Date());
		} else {
			saleEntity.setCreationdate(DateUtil.getDate(sale.getSaledate()));
		}
		Iterator<JewelEntity> ijewels = sale.getJewels().iterator();
		BigDecimal importeTotal = getTotalAmount(salesJewels, saleEntity, ijewels);
		setAddresses(sale, saleEntity);
		BigDecimal discount = sale.getDiscount();
		Long iddiscount = sale.getIddiscount();
		if (iddiscount != null) {
			DiscountEntity discountEntity = discountsRepository.findById(iddiscount).orElse(null);
			if (discount == null) {
				discount = BigDecimal.ZERO;
			}
			if (discountEntity != null) {
				discount = discount.add(discountEntity.getDiscount());
				discountEntity.setNumsaleaplication(sale.getNumsale());
				discountEntity.setModificationdate(new Date());
				discountsRepository.save(discountEntity);
			}
		}
		if (discount != null) {
			saleEntity.setTotal(importeTotal.subtract(discount));
		} else {
			saleEntity.setTotal(importeTotal);
		}
		BigDecimal optionalpayment = sale.getOptionalpayment();
		PaymentEntity payment = mapper.map(sale.getPayment(), PaymentEntity.class);
		SalesPayments sps = new SalesPayments();
		sps.setPay(payment);
		sps.setSale(saleEntity);
		if (optionalpayment != null && optionalpayment.compareTo(BigDecimal.ZERO) > 0) {
			SalesPayments spsop = new SalesPayments();
			PaymentEntity payop = new PaymentEntity();
			payop.setIdpayment(Constants.CARD);
			spsop.setPay(payop);
			spsop.setSale(saleEntity);
			spsop.setAmount(optionalpayment);
			payments.add(spsop);
			sps.setAmount(saleEntity.getTotal().subtract(optionalpayment));
		} else {
			sps.setAmount(saleEntity.getTotal());
		}
		payments.add(sps);
		saleEntity.setPlace(mapper.map(sale.getPlace(), PlaceEntity.class));
		saleEntity.setSjewels(salesJewels);
		saleEntity.setDiscount(discount);
		saleEntity.setSpayments(payments);
		sale.setTotal(saleEntity.getTotal());
		if (sale.getNumsale() > 0
				&& !saleManager.existSale(sale.getNumsale() - 1, saleEntity.getPlace().getIdplace())) {
			mailService = new MailService(
					"Numero de venta " + sale.getNumsale() + " lugar: " + saleEntity.getPlace().getIdplace(), null,
					"REVISAR NUMERO VENTA.");
			mailService.start();
		}
		return saleManager.buy(saleEntity);
	}

	private BigDecimal getTotalAmount(List<SalesJewels> salesJewels, SaleEntity saleEntity,
			Iterator<JewelEntity> ijewels) {
		BigDecimal importeTotal = BigDecimal.ZERO;
		while (ijewels.hasNext()) {
			// Deshabilito el objeto comprado
			JewelEntity jewel = ijewels.next();
			jewel.setActive(false);
			importeTotal = importeTotal.add(jewel.getPrice());
			SalesJewels e = new SalesJewels();
			e.setSale(saleEntity);
			e.setJewelEntity(jewel);
			salesJewels.add(e);
		}
		return importeTotal;
	}

	private void setAddresses(Sale sale, SaleEntity saleEntity) {
		List<AddressEntity> addresses = new ArrayList<>();
		AddressEntity invoice = sale.getInvoice();
		if (invoice != null) {
			invoice.setDatecreation(new Date());
			saleEntity.setAddressinvoice(invoice);
			addresses.add(invoice);
		}
		AddressEntity mailing = sale.getMailing();
		if (mailing != null) {
			mailing.setDatecreation(new Date());
			addresses.add(mailing);
			saleEntity.setAddressmailing(mailing);
		}
	}

	@Override
	public List<Sale> searchAllSales() {
		Iterable<SaleEntity> sales = saleManager.searchAllSales();
		if (sales != null) {
			return mapper(sales.iterator());
		} else {
			return Collections.emptyList();
		}
	}

	/**
	 * Mapper.
	 * 
	 * @param salesJewels the sales jewels
	 * @return the list
	 */
	private List<Sale> mapper(Iterator<SaleEntity> isales) {
		List<Sale> sales = new ArrayList<>();
		while (isales.hasNext()) {
			SaleEntity saleEntity = isales.next();
			Sale sale = mapper.map(saleEntity, Sale.class);
			sale.setJewels(mapperListJewels(saleEntity.getSjewels()));
			sales.add(sale);
		}
		return sales;
	}

	/**
	 * Mapper list jewels.
	 * 
	 * @param sjewels the sjewels
	 * @return the list
	 */
	private List<JewelEntity> mapperListJewels(List<SalesJewels> sjewels) {
		Iterator<SalesJewels> ijewels = sjewels.iterator();
		List<JewelEntity> jewels = new ArrayList<>();
		while (ijewels != null && ijewels.hasNext()) {
			jewels.add(mapper.map(ijewels.next().getJewelEntity(), JewelEntity.class));
		}
		return jewels;
	}

	@Override
	public List<Sale> searchByDate(Date date) {
		Iterable<SaleEntity> sales = saleManager.searchByDate(date);
		if (sales != null) {
			return mapper(sales.iterator());
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public Addresses searchAddressByClient(String nif) {
		ClientEntity client = usersManager.findById(nif);
		Addresses addresses = null;
		if (client != null) {
			com.je.dbaccess.entities.Addresses addressesEntity = saleManager.searchAddressByClient(client);
			if (addressesEntity != null) {
				addresses = new Addresses();
				addresses.setAddressesBilling(addressesEntity.getAdressesbilling());
				addresses.setAddressesMailing(addressesEntity.getAdressesmailing());
			}
		}
		return addresses;
	}

	@Override
	public void removeSale(Sale removeSaleForm) {
		Long iddiscount = removeSaleForm.getIddiscount();
		List<CancelSalePaymentEntity> payments = new ArrayList<>();
		PaymentEntity payment = null;
		CancelSalePaymentEntity csp = new CancelSalePaymentEntity();
		DiscountEntity discount = null;
		BigDecimal optionalpayment = removeSaleForm.getOptionalpayment();
		SaleEntity sale = saleManager.searchByNumsaleAndPlace(removeSaleForm.getNumsale(),
				removeSaleForm.getPlace().getIdplace());
		CancelSaleEntity cancel = new CancelSaleEntity();
		cancel.setIdcancelsale(sale.getIdsale());
		cancel.setNumsale(sale.getNumsale());
		cancel.setCreationdate(new Date());
		cancel.setParcial(Boolean.FALSE);
		cancel.setPlace(sale.getPlace());
		cancel.setAmount(sale.getTotal());
		if (optionalpayment != null) {
			PaymentEntity paymentV = new PaymentEntity();
			CancelSalePaymentEntity cspv = new CancelSalePaymentEntity();
			payment = new PaymentEntity();
			payment.setIdpayment(Constants.EFECTIVO);
			paymentV.setIdpayment(Constants.DISCOUNT);
			cspv.setPay(paymentV);
			cspv.setAmount(removeSaleForm.getOptionalpayment());
			cspv.setCancelsale(cancel);
			payments.add(cspv);
		} else {
			payment = removeSaleForm.getPayment();
		}
		csp.setPay(payment);
		csp.setAmount(removeSaleForm.getTotal());
		csp.setCancelsale(cancel);
		payments.add(csp);
		cancel.setSpayments(payments);
		if (iddiscount != null) {
			discount = new DiscountEntity();
			discount.setIddiscount(iddiscount);
			discount.setCreationdate(new Date());
			discount.setNumsalecancel(removeSaleForm.getNumsale());
			discount.setNumsalechange(removeSaleForm.getNumsalechange());
			discount.setPlace(mapper.map(removeSaleForm.getPlace(), PlaceEntity.class));
		}
		saleManager.cancelSale(cancel, sale.getSjewels(), discount);
	}

	@Override
	public boolean removeSaleParcial(Sale removeSaleForm) {
		SaleEntity sale = saleManager.searchByPK(removeSaleForm.getIdsale());
		List<CancelSalePaymentEntity> payments = new ArrayList<>();
		Long iddiscount = removeSaleForm.getIddiscount();
		DiscountEntity discount = null;
		CancelSaleEntity cancel = new CancelSaleEntity();
		CancelSalePaymentEntity csp = new CancelSalePaymentEntity();
		cancel.setIdcancelsale(sale.getIdsale());
		cancel.setNumsale(sale.getNumsale());
		cancel.setCreationdate(new Date());
		cancel.setParcial(Boolean.TRUE);
		cancel.setPlace(sale.getPlace());
		if (iddiscount != null) {
			discount = new DiscountEntity();
			discount.setIddiscount(iddiscount);
			discount.setCreationdate(new Date());
			discount.setNumsalecancel(removeSaleForm.getNumsale());
			discount.setNumsalechange(removeSaleForm.getNumsalechange());
			discount.setPlace(sale.getPlace());
		}
		csp.setPay(removeSaleForm.getPayment());
		csp.setCancelsale(cancel);
		payments.add(csp);
		cancel.setSpayments(payments);
		return saleManager.cancelParcialSale(cancel, removeSaleForm.getJewelstocancel(), discount);
	}

	@Override
	public Sale searchByNumsaleAndPlace(Long numsale, Long idplace) {
		SaleEntity saleEntity = saleManager.searchByNumsaleAndPlace(numsale, idplace);
		Sale sale = null;
		if (saleEntity != null) {
			sale = mapper.map(saleEntity, Sale.class);
			sale.setJewels(mapperListJewels(saleEntity.getSjewels()));
		}
		return sale;
	}

	@Override
	public Map<String, Object> searchByDatesAndPlace(String sDateFrom, String sDateUntil, PlaceEntity place) {
		Date until = new Date();
		if (!Util.isEmpty(sDateUntil)) {
			until = DateUtil.getDate(sDateUntil);
		}
		Map<String, Object> map = saleManager.searchByDatesAndPlace(DateUtil.getDate(sDateFrom), until,
				mapper.map(place, PlaceEntity.class));
		@SuppressWarnings("unchecked")
		List<SaleEntity> list = (List<SaleEntity>) map.get(Constants.SALES);
		List<Sale> sales = mapper(list.iterator());
		map.put(Constants.SALES, sales);
		return map;
	}

	@Override
	public Sale searchByPK(Long idsale) {
		SaleEntity entity = saleManager.searchByPK(idsale);
		Sale sale = mapper.map(entity, Sale.class);
		sale.setJewels(mapperListJewels(entity.getSjewels()));
		List<SalesPayments> payments = entity.getSpayments();
		sale.setPayment(payments.get(0).getPay());
		if (payments.size() > 1) {
			sale.setOptionalpayment(payments.get(1).getAmount());
		}
		return sale;
	}

	public List<Long> calculateMissingSales(SearchMissingNumbers form) {
		return saleManager.calculateNumberMissing(form.getNumfrom(), form.getNumuntil());
	}

	@Override
	public boolean exists(Long numsale, Long idplace) {
		return saleManager.existSale(numsale, idplace);
	}
}