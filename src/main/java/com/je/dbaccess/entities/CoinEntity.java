package com.je.dbaccess.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class CoinEntity.
 */
@Entity
@Table(name = "coins")
public class CoinEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The idcoin. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDCOIN")
	private Long idcoin;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "IDPLACE", referencedColumnName = "IDPLACE")
	private PlaceEntity place;
	/** The metal. */
	@ManyToOne
	@JoinColumn(name = "IDMETAL", referencedColumnName = "IDMETAL")
	private MetalEntity metal;

	/** The description. */
	@Column(name = "DESCRIPTION")
	private String description;

	/** The price. */
	@Column(name = "PRICE")
	private Double price;

	/** The creationdate. */
	@Column(name = "CREATIONDATE")
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
	 * @param idcoin
	 *            the new idcoin
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
	 * @param place
	 *            the new place
	 */
	public void setPlace(PlaceEntity place) {
		this.place = place;
	}

	/**
	 * Gets the metal.
	 *
	 * @return the metal
	 */
	public MetalEntity getMetal() {
		return metal;
	}

	/**
	 * Sets the metal.
	 *
	 * @param metal
	 *            the new metal
	 */
	public void setMetal(MetalEntity metal) {
		this.metal = metal;
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
	 *            the new description
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
	 * @param price
	 *            the new price
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
	 * @param creationdate
	 *            the new creationdate
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}
}