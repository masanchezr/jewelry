package com.je.dbaccess.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class MetalEntity.
 */
@Entity
@Table(name = "metals")
public class MetalEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The idmaterial. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDMETAL")
	private Long idmetal;

	/** The description. */
	@Column(name = "DESCRIPTION")
	private String description;

	/** The creationdate. */
	@Column(name = "CREATIONDATE")
	private Date creationdate;

	/** The active. */
	@Column(name = "ACTIVE")
	private Boolean active;

	/**
	 * Gets the idmaterial.
	 *
	 * @return the idMetal
	 */
	public Long getIdmetal() {
		return idmetal;
	}

	/**
	 * Sets the idmaterial.
	 *
	 * @param idmaterial
	 *            the new idmaterial
	 */
	public void setIdmetal(Long idmaterial) {
		this.idmetal = idmaterial;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}