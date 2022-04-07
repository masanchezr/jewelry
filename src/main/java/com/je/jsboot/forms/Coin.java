package com.je.jsboot.forms;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.je.jsboot.dbaccess.entities.MetalEntity;
import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.utils.constants.ConstantsViews;

public class Coin {

	private Long idcoin;

	private PlaceEntity place;

	private MetalEntity metal;

	@NotBlank(message = ConstantsViews.ERRORSELECTDESCRIPTION)
	@NotEmpty(message = ConstantsViews.ERRORSELECTDESCRIPTION)
	@NotNull(message = ConstantsViews.ERRORSELECTDESCRIPTION)
	private String description;

	private Double price;

	private Date creationdate;

	/**
	 * Gets the idcoin.
	 *
	 * @return the idcoin
	 */
	public Long getIdcoin() {
		return idcoin;
	}

	/**
	 * Sets the idcoin.
	 *
	 * @param idcoin the new idcoin
	 */
	public void setIdcoin(Long idcoin) {
		this.idcoin = idcoin;
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
	 * @param place the new place
	 */
	public void setPlace(PlaceEntity place) {
		this.place = place;
	}

	/**
	 * Gets the material.
	 *
	 * @return the material
	 */
	public MetalEntity getMetal() {
		return metal;
	}

	/**
	 * Sets the material.
	 *
	 * @param material the new material
	 */
	public void setMetal(MetalEntity material) {
		this.metal = material;
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
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Gets the creationdate.
	 *
	 * @return the creationdate
	 */
	public Date getCreationdate() {
		return creationdate;
	}

	/**
	 * Sets the creationdate.
	 *
	 * @param creationdate the new creationdate
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}
}
