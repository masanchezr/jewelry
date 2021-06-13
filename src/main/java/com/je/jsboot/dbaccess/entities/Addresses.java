package com.je.jsboot.dbaccess.entities;

import java.util.List;

/**
 * Clase que tiene como atributos dos listas de direcciones, una con las de
 * envio y otra con las de facturacion.
 * 
 * @author masanchez
 */
public class Addresses {

	/** The adressesmailing. */
	private List<AddressEntity> adressesmailing;

	/** The adressesbilling. */
	private List<AddressEntity> adressesbilling;

	/**
	 * Gets the adressesbilling.
	 * 
	 * @return the adressesbilling
	 */
	public List<AddressEntity> getAdressesbilling() {
		return adressesbilling;
	}

	/**
	 * Sets the adressesbilling.
	 * 
	 * @param adressesbilling
	 *            the adressesbilling to set
	 */
	public void setAdressesbilling(List<AddressEntity> adressesbilling) {
		this.adressesbilling = adressesbilling;
	}

	/**
	 * Gets the adressesmailing.
	 * 
	 * @return the adressesmailing
	 */
	public List<AddressEntity> getAdressesmailing() {
		return adressesmailing;
	}

	/**
	 * Sets the adressesmailing.
	 * 
	 * @param adressesmailing
	 *            the adressesmailing to set
	 */
	public void setAdressesmailing(List<AddressEntity> adressesmailing) {
		this.adressesmailing = adressesmailing;
	}
}
