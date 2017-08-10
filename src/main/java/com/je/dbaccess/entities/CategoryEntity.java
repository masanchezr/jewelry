package com.je.dbaccess.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class CategoryEntity.
 */
@Entity
@Table(name = "categories")
public class CategoryEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The idcategory. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDCATEGORY")
	private Long idcategory;

	/** The namecategory. */
	@Column(name = "NAMECATEGORY")
	private String namecategory;

	/** The active. */
	@Column(name = "ACTIVE")
	private Boolean active;

	/** The keyword. */
	@Column(name = "KEYWORD")
	private String keyword;

	/**
	 * Gets the idcategory.
	 * 
	 * @return the idcategory
	 */
	public Long getIdcategory() {
		return idcategory;
	}

	/**
	 * Sets the idcategory.
	 * 
	 * @param idcategory
	 *            the new idcategory
	 */
	public void setIdcategory(Long idcategory) {
		this.idcategory = idcategory;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 * 
	 *         public String getDescription() { return description; }
	 * 
	 *         /** Sets the description. the namecategory
	 */
	public String getNamecategory() {
		return namecategory;
	}

	/**
	 * Sets the namecategory.
	 * 
	 * @param namecategory
	 *            the new namecategory
	 */
	public void setNamecategory(String namecategory) {
		this.namecategory = namecategory;
	}

	/**
	 * Gets the keyword.
	 *
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * Sets the keyword.
	 *
	 * @param keyword
	 *            the new keyword
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
