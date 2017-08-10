package com.je.dbaccess.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The Class SetsEntity.
 */
@Entity
@Table(name = "sets")
public class SetEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The idset. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDSET")
	private Long idset;

	/** The idring. */
	@JoinColumn(name = "IDRING", referencedColumnName = "IDJEWEL")
	@OneToOne
	private JewelEntity ring;

	/** colgante. */
	@JoinColumn(name = "IDPENDANT", referencedColumnName = "IDJEWEL")
	@OneToOne
	private JewelEntity pendant;

	/** pendientes. */
	@JoinColumn(name = "IDEARRINGS", referencedColumnName = "IDJEWEL")
	@OneToOne
	private JewelEntity earrings;

	/** The idbracelet. */
	@JoinColumn(name = "IDBRACELET", referencedColumnName = "IDJEWEL")
	@OneToOne
	private JewelEntity bracelet;

	/** The idchocker. */
	@JoinColumn(name = "IDCHOKER", referencedColumnName = "IDJEWEL")
	@OneToOne
	private JewelEntity chocker;

	/** The idtiepin. */
	@JoinColumn(name = "IDTIEPIN", referencedColumnName = "IDJEWEL")
	@OneToOne
	private JewelEntity tiepin;

	/** The idcufflins. */
	@JoinColumn(name = "IDCUFFLINKS", referencedColumnName = "IDJEWEL")
	@OneToOne
	private JewelEntity cufflins;

	/** The idcufflins. */
	@JoinColumn(name = "IDDIAMONDRING", referencedColumnName = "IDJEWEL")
	@OneToOne
	private JewelEntity diamondring;

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
	 *            the idset to set
	 */
	public void setIdset(Long idset) {
		this.idset = idset;
	}

	/**
	 * Gets the idring.
	 *
	 * @return the idring
	 */
	public JewelEntity getRing() {
		return ring;
	}

	/**
	 * Sets the idring.
	 *
	 * @param idring
	 *            the idring to set
	 */
	public void setRing(JewelEntity idring) {
		this.ring = idring;
	}

	/**
	 * Gets the idpendant.
	 *
	 * @return the idpendant
	 */
	public JewelEntity getPendant() {
		return pendant;
	}

	/**
	 * Sets the idpendant.
	 *
	 * @param idpendant
	 *            the idpendant to set
	 */
	public void setPendant(JewelEntity idpendant) {
		this.pendant = idpendant;
	}

	/**
	 * Gets the idearrings.
	 *
	 * @return the idearrings
	 */
	public JewelEntity getEarrings() {
		return earrings;
	}

	/**
	 * Sets the idearrings.
	 *
	 * @param idearrings
	 *            the idearrings to set
	 */
	public void setEarrings(JewelEntity idearrings) {
		this.earrings = idearrings;
	}

	/**
	 * Gets the idbracelet.
	 *
	 * @return the idbracelet
	 */
	public JewelEntity getBracelet() {
		return bracelet;
	}

	/**
	 * Sets the idbracelet.
	 *
	 * @param idbracelet
	 *            the idbracelet to set
	 */
	public void setBracelet(JewelEntity idbracelet) {
		this.bracelet = idbracelet;
	}

	/**
	 * Gets the idchocker.
	 *
	 * @return the idchocker
	 */
	public JewelEntity getChocker() {
		return chocker;
	}

	/**
	 * Sets the idchocker.
	 *
	 * @param idchocker
	 *            the new idchocker
	 */
	public void setChocker(JewelEntity idchocker) {
		this.chocker = idchocker;
	}

	/**
	 * Gets the idtiepin.
	 *
	 * @return the idtiepin
	 */
	public JewelEntity getTiepin() {
		return tiepin;
	}

	/**
	 * Sets the idtiepin.
	 *
	 * @param idtiepin
	 *            the new idtiepin
	 */
	public void setTiepin(JewelEntity idtiepin) {
		this.tiepin = idtiepin;
	}

	/**
	 * Gets the idcufflins.
	 *
	 * @return the idcufflins
	 */
	public JewelEntity getCufflins() {
		return cufflins;
	}

	/**
	 * Sets the idcufflins.
	 *
	 * @param idcufflins
	 *            the new idcufflins
	 */
	public void setCufflins(JewelEntity idcufflins) {
		this.cufflins = idcufflins;
	}

	public void setDiamondring(JewelEntity diamondring) {
		this.diamondring = diamondring;

	}

	public JewelEntity getDiamondring() {
		return diamondring;
	}

}
