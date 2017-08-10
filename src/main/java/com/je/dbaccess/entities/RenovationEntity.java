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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class RenovationEntity.
 */
@Entity
@Table(name = "renovations")
public class RenovationEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The idrenovation. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDRENOVATION")
	private Long idrenovation;

	/** The pawn. */
	@ManyToOne
	@JoinColumn(name = "IDPAWN")
	private PawnEntity pawn;

	/** The nextrenovationdate. */
	@Temporal(TemporalType.DATE)
	@Column(name = "NEXTRENOVATIONDATE")
	private Date nextrenovationdate;

	/** The creationdate. */
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATIONDATE")
	private Date creationdate;

	/**
	 * Gets the nextrenovationdate.
	 *
	 * @return the nextrenovationdate
	 */
	public Date getNextrenovationdate() {
		return nextrenovationdate;
	}

	/**
	 * Sets the nextrenovationdate.
	 *
	 * @param nextrenovationdate
	 *            the new nextrenovationdate
	 */
	public void setNextrenovationdate(Date nextrenovationdate) {
		this.nextrenovationdate = nextrenovationdate;
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
	 * Gets the idrenovation.
	 *
	 * @return the idrenovation
	 */
	public Long getIdrenovation() {
		return idrenovation;
	}

	/**
	 * Sets the idrenovation.
	 *
	 * @param idrenovation
	 *            the new idrenovation
	 */
	public void setIdrenovation(Long idrenovation) {
		this.idrenovation = idrenovation;
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
