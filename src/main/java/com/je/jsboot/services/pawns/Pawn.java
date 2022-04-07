package com.je.jsboot.services.pawns;

import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.forms.OperationForm;
import com.je.jsboot.utils.constants.ConstantsViews;

/**
 * The Class Pawn.
 */
public class Pawn extends OperationForm {

	/** The numpawn. */

	@NotEmpty(message = ConstantsViews.IDPAWN)
	private String numpawn;

	/** The percent. */
	@DecimalMin(value = "0.1", message = "{errorpercent}")
	@DecimalMax(value = "100", message = "{errorpercent}")
	private double percent;

	/** The retired. */
	private Date dateretired;

	/** The months. */
	private int months;

	/**
	 * Número de veces a renovar
	 */
	private Integer numrenovations;

	private PlaceEntity place;

	/**
	 * Informa el id del empeño que se ha vuelto a empeñar
	 */
	private Long idreturnpawn;

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
	 * @param numpawn the new numpawn
	 */
	public void setNumpawn(String numpawn) {
		this.numpawn = numpawn;
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
	 * @param percent the new percent
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
	 * @param retired the new retired
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
	 * @param months the new months
	 */
	public void setMonths(int months) {
		this.months = months;
	}

	public PlaceEntity getPlace() {
		return place;
	}

	public void setPlace(PlaceEntity place) {
		this.place = place;
	}

	public Integer getNumrenovations() {
		return numrenovations;
	}

	public void setNumrenovations(Integer renovations) {
		this.numrenovations = renovations;
	}

	public Long getIdreturnpawn() {
		return idreturnpawn;
	}

	public void setIdreturnpawn(Long idreturnpawn) {
		this.idreturnpawn = idreturnpawn;
	}
}
