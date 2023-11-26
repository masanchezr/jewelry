package com.atmj.jsboot.dbaccess.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cancelsalespayment")
public class CancelSalePaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcancelsalepayment")
	private Long idcancelsalepayment;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

	/** The sale. */
	@ManyToOne
	@JoinColumn(name = "IDCANCELSALE")
	private CancelSaleEntity cancelsale;

	/** The pay. */
	@ManyToOne
	@JoinColumn(name = "IDPAYMENT")
	private PaymentEntity pay;

	public Long getIdcancelsalespayment() {
		return idcancelsalepayment;
	}

	public void setIdcancelsalespayment(Long idcancelsalespayment) {
		this.idcancelsalepayment = idcancelsalespayment;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public CancelSaleEntity getCancelsale() {
		return cancelsale;
	}

	public void setCancelsale(CancelSaleEntity cancelsale) {
		this.cancelsale = cancelsale;
	}

	public PaymentEntity getPay() {
		return pay;
	}

	public void setPay(PaymentEntity pay) {
		this.pay = pay;
	}
}
