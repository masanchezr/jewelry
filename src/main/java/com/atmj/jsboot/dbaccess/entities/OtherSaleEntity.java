package com.atmj.jsboot.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "othersales")
public class OtherSaleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDOTHERSALES")
	private Long idothersale;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

	@Column(name = "CREATIONDATE")
	@CreatedDate
	private Date creationdate;

	/** The idsale. */
	@Column(name = "NUMSALE")
	private Long numsale;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "IDPLACE", referencedColumnName = "IDPLACE")
	private PlaceEntity place;

	/** The pay. */
	@ManyToOne
	@JoinColumn(name = "IDPAYMENT")
	private PaymentEntity pay;

	@ManyToOne
	@JoinColumn(name = "IDTYPEOFSALE")
	private TypeOfSaleEntity type;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "YEAR")
	private Integer year;

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the creationdate
	 */
	public Date getCreationdate() {
		return creationdate;
	}

	/**
	 * @param creationdate the creationdate to set
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * @return the numsale
	 */
	public Long getNumsale() {
		return numsale;
	}

	/**
	 * @param numsale the numsale to set
	 */
	public void setNumsale(Long numsale) {
		this.numsale = numsale;
	}

	/**
	 * @return the place
	 */
	public PlaceEntity getPlace() {
		return place;
	}

	/**
	 * @param place the place to set
	 */
	public void setPlace(PlaceEntity place) {
		this.place = place;
	}

	/**
	 * @return the pay
	 */
	public PaymentEntity getPay() {
		return pay;
	}

	/**
	 * @param pay the pay to set
	 */
	public void setPay(PaymentEntity pay) {
		this.pay = pay;
	}

	/**
	 * @return the type
	 */
	public TypeOfSaleEntity getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TypeOfSaleEntity type) {
		this.type = type;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the idothersale
	 */
	public Long getIdothersale() {
		return idothersale;
	}

	/**
	 * @param idothersale the idothersale to set
	 */
	public void setIdothersale(Long idothersale) {
		this.idothersale = idothersale;
	}

}
