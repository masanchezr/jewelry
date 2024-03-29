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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import com.atmj.jsboot.utils.constants.Constants;

@Entity
@Table(name = "payroll")
public class PayrollEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpayroll")
	private Long idpayroll;

	@Column(name = Constants.AMOUNT)
	private BigDecimal amount;

	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(name = Constants.CREATIONDATE)
	private Date creationdate;

	@Column(name = "MONTH")
	private Integer month;

	@Column(name = "YEAR")
	private Integer year;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "USERID", referencedColumnName = "ID")
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "IDPAYROLLTYPE", referencedColumnName = "IDPAYROLLTYPE")
	private PayrolltypeEntity payrolltype;

	public Long getIdpayroll() {
		return idpayroll;
	}

	public void setIdpayroll(Long idpayroll) {
		this.idpayroll = idpayroll;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public PayrolltypeEntity getPayrolltype() {
		return payrolltype;
	}

	public void setPayrolltype(PayrolltypeEntity payrolltype) {
		this.payrolltype = payrolltype;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getMonth() {
		return month;
	}
}
