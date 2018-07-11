package com.je.dbaccess.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cancelsalespayment")
public class CancelSalePaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
