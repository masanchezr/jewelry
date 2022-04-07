package com.je.jsboot.forms;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.je.jsboot.dbaccess.entities.MetalEntity;
import com.je.jsboot.dbaccess.entities.PlaceEntity;
import com.je.jsboot.dbaccess.entities.WorkshopEntity;
import com.je.jsboot.utils.constants.ConstantsViews;

public class Jewel {

	private Long idjewel;

	private String name;
	private String description;

	@DecimalMin(value = "0.1")
	private BigDecimal price;

	private Boolean active;

	private String img;

	private PlaceEntity place;

	private Category category;

	private WorkshopEntity work;

	@NotEmpty(message = ConstantsViews.ERRORSELECTREFERENCE)
	@NotNull(message = ConstantsViews.ERRORSELECTREFERENCE)
	private String reference;

	private MetalEntity metal;

	private Date creationdate;

	private Date saledate;

	private BigDecimal cost;

	private Boolean revised;

	private BigDecimal grams;

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
	 * Gets the idjewel.
	 *
	 * @return the idjewel
	 */
	public Long getIdjewel() {
		return idjewel;
	}

	/**
	 * Sets the idjewel.
	 *
	 * @param idjewel the new idjewel
	 */
	public void setIdjewel(Long idjewel) {
		this.idjewel = idjewel;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * Gets the img.
	 *
	 * @return the img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * Sets the img.
	 *
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
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
	 * Gets the category.
	 *
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Gets the reference.
	 *
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Sets the reference.
	 *
	 * @param reference the new reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
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
	public void setMetal(MetalEntity metal) {
		this.metal = metal;
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

	/**
	 * Gets the saledate.
	 *
	 * @return the saledate
	 */
	public Date getSaledate() {
		return saledate;
	}

	/**
	 * Sets the saledate.
	 *
	 * @param saledate the new saledate
	 */
	public void setSaledate(Date saledate) {
		this.saledate = saledate;
	}

	/**
	 * Gets the cost.
	 *
	 * @return the cost
	 */
	public BigDecimal getCost() {
		return cost;
	}

	/**
	 * Sets the cost.
	 *
	 * @param cost the new cost
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getRevised() {
		return revised;
	}

	public void setRevised(Boolean revised) {
		this.revised = revised;
	}

	public BigDecimal getGrams() {
		return grams;
	}

	public void setGrams(BigDecimal grams) {
		this.grams = grams;
	}

	public WorkshopEntity getWork() {
		return work;
	}

	public void setWork(WorkshopEntity work) {
		this.work = work;
	}
}
