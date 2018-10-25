package com.je.dbaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class PaymentEntity.
 */
@Entity
@Table(name = "payment")
public class PaymentEntity {

	/** The idpayment. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDPAYMENT")
	private Long idpayment;

	/** The name. */
	@Column(name = "NAME")
	private String name;

	/** The active. */
	@Column(name = "ACTIVE")
	private boolean active;

	/**
	 * Gets the idpayment.
	 *
	 * @return the idpayment
	 */
	public Long getIdpayment() {
		return idpayment;
	}

	/**
	 * Sets the idpayment.
	 *
	 * @param idpayment the idpayment to set
	 */
	public void setIdpayment(Long idpayment) {
		this.idpayment = idpayment;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Checks if is active.
	 *
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
}
