package com.atmj.jsboot.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import com.atmj.jsboot.utils.constants.Constants;

/**
 * The Class WorkshopEntity.
 */
@Entity
@Table(name = "workshop")
public class WorkshopEntity {

	/** The idworkshop. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDWORKSHOP")
	private Long idworkshop;

	/** The amount. */
	@Column(name = Constants.AMOUNT)
	private BigDecimal amount;

	/** The description. */
	@Column(name = Constants.DESCRIPTION)
	private String description;

	/** The creationdate. */
	@Temporal(TemporalType.DATE)
	@Column(name = Constants.CREATIONDATE)
	@CreatedDate
	private Date creationdate;

	/** The gramsgold. */
	@Column(name = Constants.GRAMS)
	private BigDecimal grams;

	/** The material. */
	@ManyToOne
	@JoinColumn(name = "IDMETAL", referencedColumnName = "IDMETAL")
	private MetalEntity metal;

	/**
	 * Gets the idworkshop.
	 *
	 * @return the idworkshop
	 */
	public Long getIdworkshop() {
		return idworkshop;
	}

	/**
	 * Sets the idworkshop.
	 *
	 * @param idworkshop the new idworkshop
	 */
	public void setIdworkshop(Long idworkshop) {
		this.idworkshop = idworkshop;
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

	public BigDecimal getGrams() {
		return grams;
	}

	public void setGrams(BigDecimal grams) {
		this.grams = grams;
	}

	public MetalEntity getMetal() {
		return metal;
	}

	public void setMetal(MetalEntity material) {
		this.metal = material;
	}

}
