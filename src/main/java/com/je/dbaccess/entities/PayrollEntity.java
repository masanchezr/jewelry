package com.je.dbaccess.entities;

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

import com.je.utils.constants.Constants;

@Entity
@Table(name = "payroll")
public class PayrollEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idpayroll")
	private Long idpayroll;

	@Column(name = Constants.AMOUNT)
	private BigDecimal amount;

	@Temporal(TemporalType.DATE)
	@Column(name = Constants.CREATIONDATE)
	private Date creationdate;

	@Column(name = "MONTH")
	private Integer month;

	@Column(name = "YEAR")
	private Integer year;

	/** The place. */
	@ManyToOne
	@JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
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
