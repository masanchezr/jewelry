package com.je.services.shoppings;

import java.math.BigDecimal;
import java.util.List;

import com.je.dbaccess.entities.ObjectShopEntity;

/**
 * The Class Shopping.
 */
public class Shopping {

	/** The idshop. */
	private Long idshop;

	/** The numshop. */
	private Long numshop;

	private BigDecimal cashamount;

	/** The creationdate. */
	private String creationdate;

	/** The wiretransfer. */
	private BigDecimal wiretransfer;

	private String user;

	private BigDecimal totalamount;

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

	/** The nationality. */
	private String nationality;

	private String town;

	private String description;

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
	 * @param numshop
	 *            the new numshop
	 */
	public void setNumshop(Long numshop) {
		this.numshop = numshop;
	}

	/**
	 * Gets the creationdate.
	 *
	 * @return the creationdate
	 */
	public String getCreationdate() {
		return creationdate;
	}

	/**
	 * Sets the creationdate.
	 *
	 * @param creationdate
	 *            the new creationdate
	 */
	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * Gets the idshop.
	 *
	 * @return the idshop
	 */
	public Long getIdshop() {
		return idshop;
	}

	/**
	 * Sets the idshop.
	 *
	 * @param idshop
	 *            the new idshop
	 */
	public void setIdshop(Long idshop) {
		this.idshop = idshop;
	}

	/**
	 * Checks if is wiretransfer.
	 *
	 * @return true, if is wiretransfer
	 */
	public BigDecimal getWiretransfer() {
		return wiretransfer;
	}

	/**
	 * Sets the wiretransfer.
	 *
	 * @param wiretransfer
	 *            the new wiretransfer
	 */
	public void setWiretransfer(BigDecimal wiretransfer) {
		this.wiretransfer = wiretransfer;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<ObjectShopEntity> getObjects() {
		return objects;
	}

	public void setObjects(List<ObjectShopEntity> objects) {
		this.objects = objects;
	}

	public BigDecimal getCashamount() {
		return cashamount;
	}

	public void setCashamount(BigDecimal cashamount) {
		this.cashamount = cashamount;
	}

	public BigDecimal getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(BigDecimal totalamount) {
		this.totalamount = totalamount;
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

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
