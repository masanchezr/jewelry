package com.atmj.jsboot.dbaccess.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * The Class SalesJewels.
 */
@Entity
@Table(name = "salesjewels")
public class SalesJewels {

	/** The sale. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDSALESJEWELS")
	private Long idsalesjewels;

	/** The sale. */
	@ManyToOne
	@JoinColumn(name = "IDSALE")
	private SaleEntity sale;

	/** The jewel. */
	@OneToOne
	@JoinColumn(name = "IDJEWEL")
	private JewelEntity jewel;

	/**
	 * Gets the sale.
	 * 
	 * @return the sale
	 */
	public SaleEntity getSale() {
		return sale;
	}

	/**
	 * Sets the sale.
	 * 
	 * @param sale the sale to set
	 */
	public void setSale(SaleEntity sale) {
		this.sale = sale;
	}

	/**
	 * Gets the jewel.
	 * 
	 * @return the jewel
	 */
	public JewelEntity getJewelEntity() {
		return jewel;
	}

	/**
	 * Sets the jewel.
	 * 
	 * @param jewel the jewel to set
	 */
	public void setJewelEntity(JewelEntity jewel) {
		this.jewel = jewel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object salesJewels) {
		if (salesJewels instanceof SalesJewels) {
			SalesJewels saleJewel = (SalesJewels) salesJewels;
			return getSale().equals(saleJewel.getSale()) && getJewelEntity().equals(saleJewel.getJewelEntity());
		} else {
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return getJewelEntity().hashCode() + getSale().hashCode();
	}

	public Long getIdsalesjewels() {
		return idsalesjewels;
	}

	public void setIdsalesjewels(Long idsalesjewels) {
		this.idsalesjewels = idsalesjewels;
	}
}
