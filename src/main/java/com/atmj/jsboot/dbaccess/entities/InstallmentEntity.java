package com.atmj.jsboot.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import com.atmj.jsboot.utils.constants.Constants;

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

@Entity
@Table(name = "installments")
public class InstallmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDINSTALLMENT")
	private Long idinstallment;

	@ManyToOne
	@JoinColumn(name = Constants.IDSALEPOSTPONED)
	private SalePostponedEntity salepostponed;

	/** The pay. */
	@ManyToOne
	@JoinColumn(name = "IDPAYMENT")
	private PaymentEntity pay;

	@Column(name = Constants.AMOUNT)
	private BigDecimal amount;

	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATIONDATE")
	private Date creationdate;

	public Long getIdinstallment() {
		return idinstallment;
	}

	public void setIdinstallment(Long idinstallment) {
		this.idinstallment = idinstallment;
	}

	public SalePostponedEntity getSalepostponed() {
		return salepostponed;
	}

	public void setSalepostponed(SalePostponedEntity salepostponed) {
		this.salepostponed = salepostponed;
	}

	public PaymentEntity getPay() {
		return pay;
	}

	public void setPay(PaymentEntity pay) {
		this.pay = pay;
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
}
