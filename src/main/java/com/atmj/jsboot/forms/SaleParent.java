package com.atmj.jsboot.forms;

import java.math.BigDecimal;
import java.util.List;

import com.atmj.jsboot.dbaccess.entities.JewelEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;

public class SaleParent {

	private Long idsale;

	/** The jewels. */
	private List<JewelEntity> jewels;

	/** The payment. */
	private Payment payment;

	/** The place. */
	private PlaceEntity place;

	private BigDecimal optionalpayment;

	private String payments;

	/** The total. */
	private BigDecimal total;

	/**
	 * Gets the place.
	 *
	 * @return the place
	 */
	public PlaceEntity getPlace() {
		return place;
	}

	public String getPayments() {
		return payments;
	}

	public void setPayments(String payments) {
		this.payments = payments;
	}

	/**
	 * Sets the place.
	 *
	 * @param place the new place
	 */
	public void setPlace(PlaceEntity place) {
		this.place = place;
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
	 * @param jewels the new jewels
	 */
	public void setJewels(List<JewelEntity> jewels) {
		this.jewels = jewels;
	}

	/**
	 * Sets the idsale.
	 *
	 * @param id the new idsale
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
	 * @param total the total to set
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * Gets the jewels.
	 *
	 * @return the jewels
	 */
	public List<JewelEntity> getJewels() {
		return jewels;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public BigDecimal getOptionalpayment() {
		return optionalpayment;
	}

	public void setOptionalpayment(BigDecimal optionalpayment) {
		this.optionalpayment = optionalpayment;
	}
}
