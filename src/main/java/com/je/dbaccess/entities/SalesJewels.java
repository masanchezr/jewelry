package com.je.dbaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The Class SalesJewels.
 */
@Entity
@Table(name = "salesjewels")
public class SalesJewels {

	/** The sale. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
