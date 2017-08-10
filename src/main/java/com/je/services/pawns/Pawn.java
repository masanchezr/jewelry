package com.je.services.pawns;

import java.math.BigDecimal;
import java.util.Date;

import com.je.dbaccess.entities.PlaceEntity;

/**
 * The Class Pawn.
 */
public class Pawn {

	/** The idpawn. */
	private Long idpawn;

	/** The description. */
	private String description;

	private String user;

	/** The numpawn. */
	private String numpawn;

	/** The creationdate. */
	private String creationdate;

	/** The amount. */
	private BigDecimal amount;

	/** The percent. */
	private double percent;

	/** The retired. */
	private Date dateretired;

	/** The months. */
	private int months;

	private PlaceEntity place;

	/**
	 * Gets the idpawn.
	 *
	 * @return the idpawn
	 */
	public Long getIdpawn() {
		return idpawn;
	}

	/**
	 * Sets the idpawn.
	 *
	 * @param idpawn
	 *            the idpawn to set
	 */
	public void setIdpawn(Long idpawn) {
		this.idpawn = idpawn;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the numpawn.
	 *
	 * @return the numpawn
	 */
	public String getNumpawn() {
		return numpawn;
	}

	/**
	 * Sets the numpawn.
	 *
	 * @param numpawn
	 *            the new numpawn
	 */
	public void setNumpawn(String numpawn) {
		this.numpawn = numpawn;
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
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount
	 *            the new amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * Gets the percent.
	 *
	 * @return the percent
	 */
	public double getPercent() {
		return percent;
	}

	/**
	 * Sets the percent.
	 *
	 * @param percent
	 *            the new percent
	 */
	public void setPercent(double percent) {
		this.percent = percent;
	}

	/**
	 * Checks if is retired.
	 *
	 * @return true, if is retired
	 */
	public Date getDateretired() {
		return dateretired;
	}

	/**
	 * Sets the retired.
	 *
	 * @param retired
	 *            the new retired
	 */
	public void setDateretired(Date dateretired) {
		this.dateretired = dateretired;
	}

	/**
	 * Gets the months.
	 *
	 * @return the months
	 */
	public int getMonths() {
		return months;
	}

	/**
	 * Sets the months.
	 *
	 * @param months
	 *            the new months
	 */
	public void setMonths(int months) {
		this.months = months;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public PlaceEntity getPlace() {
		return place;
	}

	public void setPlace(PlaceEntity place) {
		this.place = place;
	}
}
