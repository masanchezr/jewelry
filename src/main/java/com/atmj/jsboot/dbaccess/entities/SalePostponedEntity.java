package com.atmj.jsboot.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.atmj.jsboot.utils.constants.Constants;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = Constants.SALESPOSTPONED)
public class SalePostponedEntity {

	@Id
	@Column(name = Constants.IDSALEPOSTPONED)
	private Long idsalepostponed;

	@Column(name = "totalamount")
	private BigDecimal totalamount;

	@Column(name = "dateretired")
	private Date dateretired;

	@Column(name = Constants.CREATIONDATE)
	@CreatedDate
	private Date creationdate;

	@Column(name = "deadline")
	private Date deadline;

	@Column(name = "timeout")
	private Boolean timeout;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "IDPLACE", referencedColumnName = "IDPLACE")
	private PlaceEntity place;

	@JoinColumn(name = Constants.IDSALEPOSTPONED)
	@OneToMany(cascade = CascadeType.ALL)
	private List<SalePostPonedJewel> sjewels;

	@JoinColumn(name = Constants.IDSALEPOSTPONED)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
