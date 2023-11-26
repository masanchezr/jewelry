package com.atmj.jsboot.dbaccess.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The Class PaymentEntity.
 */
@Entity
@Table(name = "payment")
public class PaymentEntity {

	/** The idpayment. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
