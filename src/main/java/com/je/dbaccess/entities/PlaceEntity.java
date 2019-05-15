package com.je.dbaccess.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

/**
 * The Class PlaceEntity.
 */
@Entity
@Table(name = "places")
public class PlaceEntity {

	/** The idplace. */
	@Id
	@Column(name = "IDPLACE")
	private Long idplace;

	/** The creationdate. */
	@Column(name = "CREATIONDATE")
	@CreatedDate
	private Date creationdate;

	/** The description. */
	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "ACTIVE")
	private Boolean active;

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
	 * @param idplace the idplace to set
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
	 * @param creationdate the creationdate to set
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
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		} else {
			PlaceEntity place = (PlaceEntity) o;
			Long id = place.getIdplace();
			if (id != null) {
				return id.equals(getIdplace());
			} else {
				return false;
			}
		}
	}

	public int hashCode() {
		int hash = 1;
		hash = hash * 31 + idplace.hashCode();
		return hash;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean enabled) {
		this.active = enabled;
	}
}