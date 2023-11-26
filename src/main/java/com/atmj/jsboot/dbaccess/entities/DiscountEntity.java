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
import org.springframework.data.annotation.LastModifiedDate;

import com.atmj.jsboot.utils.constants.Constants;

@Entity
@Table(name = "discounts")
public class DiscountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = Constants.IDDISCOUNT)
	private Long iddiscount;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "IDPLACE", referencedColumnName = "IDPLACE")
	private PlaceEntity place;

	/** The creationdate. */
	@CreatedDate
	@Column(name = "CREATIONDATE")
	private Date creationdate;

	@LastModifiedDate
	@Column(name = "modificationdate")
	private Date modificationdate;

	@Column(name = "discount")
	private BigDecimal discount;

	@Column(name = "numsalecancel")
	private Long numsalecancel;

	@Column(name = "numsalechange")
	private Long numsalechange;

	@Column(name = "numsaleaplication")
	private Long numsaleaplication;

	@Column(name = "numsale")
	private Long numsale;

	@Column(name = "YEAR")
	private Integer year;

	public Long getIddiscount() {
		return iddiscount;
	}

	public void setIddiscount(Long iddiscount) {
		this.iddiscount = iddiscount;
	}

	public PlaceEntity getPlace() {
		return place;
	}

	public void setPlace(PlaceEntity place) {
		this.place = place;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Date getModificationdate() {
		return modificationdate;
	}

	public void setModificationdate(Date modificationdate) {
		this.modificationdate = modificationdate;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Long getNumsalecancel() {
		return numsalecancel;
	}

	public void setNumsalecancel(Long numsalecancel) {
		this.numsalecancel = numsalecancel;
	}

	public Long getNumsalechange() {
		return numsalechange;
	}

	public void setNumsalechange(Long numsalechange) {
		this.numsalechange = numsalechange;
	}

	public Long getNumsaleaplication() {
		return numsaleaplication;
	}

	public void setNumsaleaplication(Long numsaleaplication) {
		this.numsaleaplication = numsaleaplication;
	}

	public Long getNumsale() {
		return numsale;
	}

	public void setNumsale(Long numsale) {
		this.numsale = numsale;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
