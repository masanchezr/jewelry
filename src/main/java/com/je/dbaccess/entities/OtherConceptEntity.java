package com.je.dbaccess.entities;

import java.math.BigDecimal;
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
 * The Class OtherConceptEntity.
 */
@Entity
@Table(name = "otherconcepts")
public class OtherConceptEntity {

	/** The idotherconcept. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDOTHERCONCEPT")
	private Long idotherconcept;

	/** The description. */
	@Column(name = "DESCRIPTION")
	private String description;

	/** The amount. */
	@Column(name = "AMOUNT")
	private BigDecimal amount;

	/** The creationdate. */
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATIONDATE")
	private Date creationdate;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "IDPLACE", referencedColumnName = "IDPLACE")
	private PlaceEntity place;

	/**
	 * Gets the idotherconcept.
	 *
	 * @return the idotherconcept
	 */
	public Long getIdotherconcept() {
		return idotherconcept;
	}

	/**
	 * Sets the idotherconcept.
	 *
	 * @param idotherconcept the new idotherconcept
	 */
	public void setIdotherconcept(Long idotherconcept) {
		this.idotherconcept = idotherconcept;
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
}
