package com.je.services.sets;

import java.util.List;

import com.je.dbaccess.entities.JewelEntity;

/**
 * The Class Set.
 */
public class Set {

	/** The jewels. */
	private List<JewelEntity> jewels;

	/** The idset. */
	private Long idset;

	/**
	 * Gets the jewels.
	 *
	 * @return the jewels
	 */
	public List<JewelEntity> getJewels() {
		return jewels;
	}

	/**
	 * Sets the jewels.
	 *
	 * @param jewels
	 *            the new jewels
	 */
	public void setJewels(List<JewelEntity> jewels) {
		this.jewels = jewels;
	}

	/**
	 * Gets the idset.
	 *
	 * @return the idset
	 */
	public Long getIdset() {
		return idset;
	}

	/**
	 * Sets the idset.
	 *
	 * @param idset
	 *            the new idset
	 */
	public void setIdset(Long idset) {
		this.idset = idset;
	}
}
