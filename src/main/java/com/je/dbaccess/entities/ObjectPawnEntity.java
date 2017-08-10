package com.je.dbaccess.entities;

import java.io.Serializable;
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
 * The Class ObjectPawnEntity.
 */
@Entity
@Table(name = "objectspawns")
public class ObjectPawnEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The idobjectpawn. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDOBJECTPAWN")
	private Long idobjectpawn;

	/** The metal. */
	@ManyToOne
	@JoinColumn(name = "IDMETAL", referencedColumnName = "IDMETAL")
	private MetalEntity metal;

	/** The description. */
	@Column(name = "DESCRIPTION")
	private String description;

	/** The pawn. */
	@ManyToOne
	@JoinColumn(name = "IDPAWN")
	private PawnEntity pawn;

	/** The grossgrams. */
	@Column(name = "GROSSGRAMS")
	private BigDecimal grossgrams;

	/** The realgrams. */
	@Column(name = "REALGRAMS")
	private BigDecimal realgrams;

	/**
	 * Gets the idobjectpawn.
	 *
	 * @return the idobjectpawn
	 */
	public Long getIdobjectpawn() {
		return idobjectpawn;
	}

	/**
	 * Sets the idobjectpawn.
	 *
	 * @param idobjectpawn
	 *            the new idobjectpawn
	 */
	public void setIdobjectpawn(Long idobjectpawn) {
		this.idobjectpawn = idobjectpawn;
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
	 * Gets the pawn.
	 *
	 * @return the pawn
	 */
	public PawnEntity getPawn() {
		return pawn;
	}

	/**
	 * Sets the pawn.
	 *
	 * @param pawn
	 *            the new pawn
	 */
	public void setPawn(PawnEntity pawn) {
		this.pawn = pawn;
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
	 * @param grossgrams
	 *            the new grossgrams
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
	 * @param realgrams
	 *            the new realgrams
	 */
	public void setRealgrams(BigDecimal realgrams) {
		this.realgrams = realgrams;
	}
}
