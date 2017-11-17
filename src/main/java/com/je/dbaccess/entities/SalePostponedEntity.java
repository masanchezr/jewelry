package com.je.dbaccess.entities;

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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "salespostponed")
public class SalePostponedEntity {

	@Id
	@Column(name = "idsalepostponed")
	private Long idsalepostponed;

	@Column(name = "totalamount")
	private BigDecimal totalamount;

	@Column(name = "dateretired")
	private Date dateretired;

	@Column(name = "creationdate")
	private Date creationdate;

	@Column(name = "deadline")
	private Date deadline;

	@Column(name = "timeout")
	private Boolean timeout;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "IDPLACE", referencedColumnName = "IDPLACE")
	private PlaceEntity place;

	@JoinColumn(name = "idsalepostponed")
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<SalePostPonedJewel> sjewels;

	@JoinColumn(name = "idsalepostponed")
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<InstallmentEntity> spayments;

	public Long getIdsalepostponed() {
		return idsalepostponed;
	}

	public void setIdsalepostponed(Long idsalepostponed) {
		this.idsalepostponed = idsalepostponed;
	}

	public BigDecimal getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(BigDecimal amount) {
		this.totalamount = amount;
	}

	public Date getDateretired() {
		return dateretired;
	}

	public void setDateretired(Date dateretired) {
		this.dateretired = dateretired;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public PlaceEntity getPlace() {
		return place;
	}

	public void setPlace(PlaceEntity place) {
		this.place = place;
	}

	public List<SalePostPonedJewel> getSjewels() {
		return sjewels;
	}

	public void setSjewels(List<SalePostPonedJewel> sjewels) {
		this.sjewels = sjewels;
	}

	public List<InstallmentEntity> getSpayments() {
		return spayments;
	}

	public void setSpayments(List<InstallmentEntity> spayments) {
		this.spayments = spayments;
	}

	public Boolean getTimeout() {
		return timeout;
	}

	public void setTimeout(Boolean timeout) {
		this.timeout = timeout;
	}
}
