package com.je.dbaccess.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class PlaceEntity.
 */
@Entity
@Table(name = "places")
public class PlaceEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The idplace. */
	@Id
	@Column(name = "IDPLACE")
	private Long idplace;

	/** The creationdate. */
	@Column(name = "CREATIONDATE")
	private Date creationdate;

	/** The description. */
	@Column(name = "DESCRIPTION")
	private String description;

	/**
	 * Gets the idplace.
	 *
	 * @return the idplace
	 */
	public Long getIdplace() {
		return idplace;
	}

	/**
	 * Sets the idplace.
	 *
	 * @param idplace
	 *            the idplace to set
	 */
	public void setIdplace(Long idplace) {
		this.idplace = idplace;
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
	 *            the creationdate to set
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
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
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
