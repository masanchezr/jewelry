package com.je.jsboot.dbaccess.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

/**
 * The Class MetalEntity.
 */
@Entity
@Table(name = "metals")
public class MetalEntity {
	/** The metal. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDMETAL")
	private Long idmetal;

	/** The description. */
	@Column(name = "DESCRIPTION")
	private String description;

	/** The creationdate. */
	@CreatedDate
	@Column(name = "CREATIONDATE")
	private Date creationdate;

	/** The active. */
	@Column(name = "ACTIVE")
	private Boolean active;

	/**
	 * Gets the metal.
	 *
	 * @return the idMetal
	 */
	public Long getIdmetal() {
		return idmetal;
	}

	/**
	 * Sets the metal.
	 *
	 * @param metal the new metal
	 */
	public void setIdmetal(Long metal) {
		this.idmetal = metal;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}