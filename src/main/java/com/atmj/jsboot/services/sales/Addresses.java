package com.atmj.jsboot.services.sales;

import java.util.List;

import com.atmj.jsboot.dbaccess.entities.AddressEntity;

/**
 * The Class Addresses.
 */
public class Addresses {

	/** The addresses billing. */
	private List<AddressEntity> addressesBilling;

	/** The addresses mailing. */
	private List<AddressEntity> addressesMailing;

	/**
	 * Gets the addresses mailing.
	 *
	 * @return the addresses mailing
	 */
	public List<AddressEntity> getAddressesMailing() {
		return addressesMailing;
	}

	/**
	 * Sets the addresses mailing.
	 *
	 * @param addressesMailing
	 *            the new addresses mailing
	 */
	public void setAddressesMailing(List<AddressEntity> addressesMailing) {
		this.addressesMailing = addressesMailing;
	}

	/**
	 * Gets the addresses billing.
	 *
	 * @return the addresses billing
	 */
	public List<AddressEntity> getAddressesBilling() {
		return addressesBilling;
	}

	/**
	 * Sets the addresses billing.
	 *
	 * @param addressesBilling
	 *            the new addresses billing
	 */
	public void setAddressesBilling(List<AddressEntity> addressesBilling) {
		this.addressesBilling = addressesBilling;
	}
}
