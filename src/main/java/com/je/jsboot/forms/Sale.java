package com.je.jsboot.forms;

import java.math.BigDecimal;
import java.util.List;

import com.je.jsboot.dbaccess.entities.AddressEntity;
import com.je.jsboot.services.users.Client;

/**
 * The Class Sale.
 */
public class Sale extends SaleParent {

	/** The client. */
	private Client client;

	private List<Long> jewelstocancel;

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

	private Long iddiscount;

	private Long numsalechange;

	private Integer year;

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
	 * @param client the new client
	 */
	public void setClient(Client client) {
		this.client = client;
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
	 * @param mailing the mailing to set
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
	 * @param invoice the invoice to set
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
	 * @param saledate the new saledate
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
	 * @param discount the new discount
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Long getNumsale() {
		return numsale;
	}

	public void setNumsale(Long numsale) {
		this.numsale = numsale;
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

	public List<Long> getJewelstocancel() {
		return jewelstocancel;
	}

	public void setJewelstocancel(List<Long> jewelstocancel) {
		this.jewelstocancel = jewelstocancel;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}
