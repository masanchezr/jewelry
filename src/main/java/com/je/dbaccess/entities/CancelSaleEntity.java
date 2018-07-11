package com.je.dbaccess.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * The Class CancelSaleEntity.
 */
@Entity
@Table(name = "cancelsales")
public class CancelSaleEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The idcancelsale. */
	@Id
	@Column(name = "IDCANCELSALE")
	private Long idcancelsale;

	/** The amount. */
	@Column(name = "AMOUNT")
	private BigDecimal amount;

	/** The idsale. */
	@Column(name = "NUMSALE")
	private Long numsale;

	/** The creationdate. */
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
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
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
