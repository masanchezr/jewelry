package com.je.services.sales;

import java.math.BigDecimal;
import java.util.List;

import com.je.dbaccess.entities.AddressEntity;
import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.PaymentEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.services.users.Client;

/**
 * The Class Sale.
 */
public class Sale {

	private Long idsale;

	/** The client. */
	private Client client;

	/** The jewels. */
	private List<JewelEntity> jewels;

	private List<JewelEntity> jewelstocancel;

	/** The payment. */
	private PaymentEntity payment;

	/** The total. */
	private BigDecimal total;

	/** The invoice. */
	private AddressEntity invoice;

	/** The mailing. */
	private AddressEntity mailing;

	/** The id. */
	private Long numsale;

	/** The saledate. */
	private String saledate;

	/** The discount. */
	private BigDecimal discount;

	/** The place. */
	private PlaceEntity place;

	private BigDecimal optionalpayment;

	private String payments;

	private Long iddiscount;

	private Long numsalechange;

	/**
	 * Gets the client.
	 *
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Sets the client.
	 *
	 * @param client
	 *            the new client
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Gets the jewels.
	 *
	 * @return the jewels
	 */
	public List<JewelEntity> getJewels() {
		return jewels;
	}

	/**
	 * Gets the idsale.
	 *
	 * @return the idsale
	 */
	public Long getIdsale() {
		return idsale;
	}

	/**
	 * Sets the jewels.
	 *
	 * @param jewels
	 *            the new jewels
	 */
	public void setJewels(List<JewelEntity> jewels) {
		this.jewels = jewels;
	}

	/**
	 * Sets the idsale.
	 *
	 * @param id
	 *            the new idsale
	 */
	public void setIdsale(Long id) {
		this.idsale = id;
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public BigDecimal getTotal() {
		return total;
	}

	/**
	 * Sets the total.
	 *
	 * @param total
	 *            the total to set
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * Gets the mailing.
	 *
	 * @return the mailing
	 */
	public AddressEntity getMailing() {
		return mailing;
	}

	/**
	 * Sets the mailing.
	 *
	 * @param mailing
	 *            the mailing to set
	 */
	public void setMailing(AddressEntity mailing) {
		this.mailing = mailing;
	}

	/**
	 * Gets the invoice.
	 *
	 * @return the invoice
	 */
	public AddressEntity getInvoice() {
		return invoice;
	}

	/**
	 * Sets the invoice.
	 *
	 * @param invoice
	 *            the invoice to set
	 */
	public void setInvoice(AddressEntity invoice) {
		this.invoice = invoice;
	}

	/**
	 * Gets the saledate.
	 *
	 * @return the saledate
	 */
	public String getSaledate() {
		return saledate;
	}

	/**
	 * Sets the saledate.
	 *
	 * @param saledate
	 *            the new saledate
	 */
	public void setSaledate(String saledate) {
		this.saledate = saledate;
	}

	/**
	 * Gets the discount.
	 *
	 * @return the discount
	 */
	public BigDecimal getDiscount() {
		return discount;
	}

	/**
	 * Sets the discount.
	 *
	 * @param discount
	 *            the new discount
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	/**
	 * Gets the place.
	 *
	 * @return the place
	 */
	public PlaceEntity getPlace() {
		return place;
	}

	/**
	 * Sets the place.
	 *
	 * @param place
	 *            the new place
	 */
	public void setPlace(PlaceEntity place) {
		this.place = place;
	}

	public Long getNumsale() {
		return numsale;
	}

	public void setNumsale(Long numsale) {
		this.numsale = numsale;
	}

	public BigDecimal getOptionalpayment() {
		return optionalpayment;
	}

	public void setOptionalpayment(BigDecimal optionalpayment) {
		this.optionalpayment = optionalpayment;
	}

	public PaymentEntity getPayment() {
		return payment;
	}

	public void setPayment(PaymentEntity payment) {
		this.payment = payment;
	}

	public String getPayments() {
		return payments;
	}

	public void setPayments(String payments) {
		this.payments = payments;
	}

	public Long getIddiscount() {
		return iddiscount;
	}

	public void setIddiscount(Long iddiscount) {
		this.iddiscount = iddiscount;
	}

	public Long getNumsalechange() {
		return numsalechange;
	}

	public void setNumsalechange(Long numsalechange) {
		this.numsalechange = numsalechange;
	}

	public List<JewelEntity> getJewelstocancel() {
		return jewelstocancel;
	}

	public void setJewelstocancel(List<JewelEntity> jewelstocancel) {
		this.jewelstocancel = jewelstocancel;
	}
}
