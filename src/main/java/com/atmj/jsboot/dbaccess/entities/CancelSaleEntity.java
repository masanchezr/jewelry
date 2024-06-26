package com.atmj.jsboot.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * The Class CancelSaleEntity.
 */
@Entity
@Table(name = "cancelsales")
public class CancelSaleEntity {

	/** The idcancelsave. */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDCANCELSALE")
	private Long idcancelsale;

	/** The amount. */
	@Column(name = "AMOUNT")
	private BigDecimal amount;

	/** The idsale. */
	@Column(name = "NUMSALE")
	private Long numsale;

	/** The creationdate. */

	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATIONDATE")
	private Date creationdate;

	/** The parcial. */
	@Column(name = "PARCIAL")
	private Boolean parcial;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "IDPLACE", referencedColumnName = "IDPLACE")
	private PlaceEntity place;

	@JoinColumn(name = "IDCANCELSALE")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CancelSalePaymentEntity> spayments;

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
	 * @param creationdate the new creationdate
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * Gets the parcial.
	 *
	 * @return the parcial
	 */
	public Boolean getParcial() {
		return parcial;
	}

	/**
	 * Sets the parcial.
	 *
	 * @param parcial the new parcial
	 */
	public void setParcial(Boolean parcial) {
		this.parcial = parcial;
	}

	/**
	 * Gets the place.
	 *
	 * @return the place
	 */
	public PlaceEntity getPlace() {
		return place;
	}

	/**
	 * Sets the place.
	 *
	 * @param place the new place
	 */
	public void setPlace(PlaceEntity place) {
		this.place = place;
	}

	public Long getNumsale() {
		return numsale;
	}

	public void setNumsale(Long numsale) {
		this.numsale = numsale;
	}

	public Long getIdcancelsale() {
		return idcancelsale;
	}

	public void setIdcancelsale(Long idcancelsale) {
		this.idcancelsale = idcancelsale;
	}

	public List<CancelSalePaymentEntity> getSpayments() {
		return spayments;
	}

	public void setSpayments(List<CancelSalePaymentEntity> spayments) {
		this.spayments = spayments;
	}

}
