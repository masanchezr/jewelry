package com.je.jsboot.forms;

public class Category {

	private Long idcategory;

	private String namecategory;

	private Boolean active;

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
	 * @param idcategory the new idcategory
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
	 * @param namecategory the new namecategory
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
	 * @param keyword the new keyword
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
