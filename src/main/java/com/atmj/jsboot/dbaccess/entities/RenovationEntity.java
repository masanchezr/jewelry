package com.atmj.jsboot.dbaccess.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

/**
 * The Class RenovationEntity.
 */
@Entity
@Table(name = "renovations")
public class RenovationEntity {

	/** The idrenovation. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@CreatedDate
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
	 * @param nextrenovationdate the new nextrenovationdate
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
	 * @param pawn the new pawn
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
	 * @param idrenovation the new idrenovation
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
	 * @param creationdate the new creationdate
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

}
