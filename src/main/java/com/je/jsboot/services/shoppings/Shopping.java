package com.je.jsboot.services.shoppings;

import java.util.List;

import com.je.jsboot.dbaccess.entities.ObjectShopEntity;
import com.je.jsboot.forms.OperationForm;

/**
 * The Class Shopping.
 */
public class Shopping extends OperationForm {

	/** The numshop. */
	private Long numshop;

	private String cashamount;

	/** The wiretransfer. */
	private String wiretransfer;

	private List<ObjectShopEntity> objects;

	private String payments;

	/** The nif. */
	private String nif;

	/** The address. */
	private String address;

	/** The name. */
	private String name;

	/** The surname. */
	private String surname;

	private String town;

	private ObjectShopEntity moreobject;

	/**
	 * Gets the numshop.
	 *
	 * @return the numshop
	 */
	public Long getNumshop() {
		return numshop;
	}

	/**
	 * Sets the numshop.
	 *
	 * @param numshop the new numshop
	 */
	public void setNumshop(Long numshop) {
		this.numshop = numshop;
	}

	/**
	 * Checks if is wiretransfer.
	 *
	 * @return true, if is wiretransfer
	 */
	public String getWiretransfer() {
		return wiretransfer;
	}

	/**
	 * Sets the wiretransfer.
	 *
	 * @param wiretransfer the new wiretransfer
	 */
	public void setWiretransfer(String wiretransfer) {
		this.wiretransfer = wiretransfer;
	}

	public List<ObjectShopEntity> getObjects() {
		return objects;
	}

	public void setObjects(List<ObjectShopEntity> objects) {
		this.objects = objects;
	}

	public String getCashamount() {
		return cashamount;
	}

	public void setCashamount(String cashamount) {
		this.cashamount = cashamount;
	}

	public String getPayments() {
		return payments;
	}

	public void setPayments(String payments) {
		this.payments = payments;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public ObjectShopEntity getMoreobject() {
		return moreobject;
	}

	public void setMoreobject(ObjectShopEntity moreobject) {
		this.moreobject = moreobject;
	}
}
