package com.atmj.jsboot.dbaccess.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class ObjectShopEntity.
 */
@Entity
@Table(name = "objectshoppings")
public class ObjectShopEntity {

	/** The idobjectshop. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDOBJECTSHOP")
	private Long idobjectshop;

	/** The material. */
	@ManyToOne
	@JoinColumn(name = "IDMETAL", referencedColumnName = "IDMETAL")
	private MetalEntity metal;

	/** The description. */
	@Column(name = "DESCRIPTION")
	private String description;

	/** The shop. */
	@ManyToOne
	@JoinColumn(name = "IDSHOP")
	private ShoppingEntity shop;

	/** The netgrams. */
	@Column(name = "NETGRAMS")
	private BigDecimal netgrams;

	/** The grossgrams. */
	@Column(name = "GROSSGRAMS")
	private BigDecimal grossgrams;

	/** The realgrams. */
	@Column(name = "REALGRAMS")
	private BigDecimal realgrams;

	/** The eurograms. */
	@Column(name = "EUROGRAMS")
	private Double eurograms;

	/** The amount. */
	@Column(name = "AMOUNT")
	private BigDecimal amount;

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
	 * Gets the shop.
	 *
	 * @return the shop
	 */
	public ShoppingEntity getShop() {
		return shop;
	}

	/**
	 * Sets the shop.
	 *
	 * @param shop the new shop
	 */
	public void setShop(ShoppingEntity shop) {
		this.shop = shop;
	}

	/**
	 * Gets the netgrams.
	 *
	 * @return the netgrams
	 */
	public BigDecimal getNetgrams() {
		return netgrams;
	}

	/**
	 * Sets the netgrams.
	 *
	 * @param netgrams the new netgrams
	 */
	public void setNetgrams(BigDecimal netgrams) {
		this.netgrams = netgrams;
	}

	/**
	 * Gets the grossgrams.
	 *
	 * @return the grossgrams
	 */
	public BigDecimal getGrossgrams() {
		return grossgrams;
	}

	/**
	 * Sets the grossgrams.
	 *
	 * @param grossgrams the new grossgrams
	 */
	public void setGrossgrams(BigDecimal grossgrams) {
		this.grossgrams = grossgrams;
	}

	/**
	 * Gets the realgrams.
	 *
	 * @return the realgrams
	 */
	public BigDecimal getRealgrams() {
		return realgrams;
	}

	/**
	 * Sets the realgrams.
	 *
	 * @param realgrams the new realgrams
	 */
	public void setRealgrams(BigDecimal realgrams) {
		this.realgrams = realgrams;
	}

	/**
	 * Gets the eurograms.
	 *
	 * @return the eurograms
	 */
	public Double getEurograms() {
		return eurograms;
	}

	/**
	 * Sets the eurograms.
	 *
	 * @param eurograms the new eurograms
	 */
	public void setEurograms(Double eurograms) {
		this.eurograms = eurograms;
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
	 * @param amount the new amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getIdobjectshop() {
		return idobjectshop;
	}

	public void setIdobjectshop(Long idobjectshop) {
		this.idobjectshop = idobjectshop;
	}

}
