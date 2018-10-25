package com.je.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.je.utils.constants.Constants;

/**
 * The Class AdjustmentEntity.
 */
@Entity
@Table(name = Constants.ADJUSTMENTS)
public class AdjustmentEntity {

	/** The idadjustment. */
	@Id
	@Column(name = "IDADJUSTMENT")
	private Long idadjustment;

	/** The description. */
	@Column(name = "DESCRIPTION")
	private String description;

	/** The amount. */
	@Column(name = "AMOUNT")
	private BigDecimal amount;

	/** The creationdate. */
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATIONDATE")
	private Date creationdate;

	/** The recommendedprice. */
	@Column(name = "RECOMMENDEDPRICE")
	private BigDecimal recommendedprice;

	/** The amountwork. */
	@Column(name = "AMOUNTWORK")
	private BigDecimal amountwork;

	/** The carrydate. */
	@Temporal(TemporalType.DATE)
	@Column(name = "CARRYDATE")
	private Date carrydate;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "IDPLACE", referencedColumnName = "IDPLACE")
	private PlaceEntity place;

	/** The grams. */
	@Column(name = "GRAMS")
	private BigDecimal grams;

	@Column(name = "WORK")
	private Boolean work;

	/** The pay. */
	@JoinColumn(name = "IDPAYMENT")
	@ManyToOne
	private PaymentEntity payment;

	/** The pay. */
	@JoinColumn(name = "IDPAYMENTWORK")
	@ManyToOne
	private PaymentEntity paymentwork;

	/**
	 * Gets the idadjustment.
	 *
	 * @return the idadjustment
	 */
	public Long getIdadjustment() {
		return idadjustment;
	}

	/**
	 * Sets the idadjustment.
	 *
	 * @param idadjustment the new idadjustment
	 */
	public void setIdadjustment(Long idadjustment) {
		this.idadjustment = idadjustment;
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
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

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

	/**
	 * Gets the recommendedprice.
	 *
	 * @return the recommendedprice
	 */
	public BigDecimal getRecommendedprice() {
		return recommendedprice;
	}

	/**
	 * Sets the recommendedprice.
	 *
	 * @param recommendedprice the new recommendedprice
	 */
	public void setRecommendedprice(BigDecimal recommendedprice) {
		this.recommendedprice = recommendedprice;
	}

	/**
	 * Gets the amountwork.
	 *
	 * @return the amountwork
	 */
	public BigDecimal getAmountwork() {
		return amountwork;
	}

	/**
	 * Sets the amountwork.
	 *
	 * @param amountwork the new amountwork
	 */
	public void setAmountwork(BigDecimal amountwork) {
		this.amountwork = amountwork;
	}

	/**
	 * Gets the carrydate.
	 *
	 * @return the carrydate
	 */
	public Date getCarrydate() {
		return carrydate;
	}

	/**
	 * Sets the carrydate.
	 *
	 * @param carrydate the new carrydate
	 */
	public void setCarrydate(Date carrydate) {
		this.carrydate = carrydate;
	}

	/**
	 * Gets the grams.
	 *
	 * @return the grams
	 */
	public BigDecimal getGrams() {
		return grams;
	}

	/**
	 * Sets the grams.
	 *
	 * @param grams the new grams
	 */
	public void setGrams(BigDecimal grams) {
		this.grams = grams;
	}

	public Boolean getWork() {
		return work;
	}

	public void setWork(Boolean work) {
		this.work = work;
	}

	public PaymentEntity getPayment() {
		return payment;
	}

	public void setPayment(PaymentEntity payment) {
		this.payment = payment;
	}

	public PaymentEntity getPaymentwork() {
		return paymentwork;
	}

	public void setPaymentwork(PaymentEntity paymentwork) {
		this.paymentwork = paymentwork;
	}
}
