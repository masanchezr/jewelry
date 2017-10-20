package com.je.dbaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "salespostponedjewels")
public class SalePostPonedJewel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idsalepostponedjewel")
	private Long idsalepostponedjewel;

	@ManyToOne
	@JoinColumn(name = "idsalepostponed")
	private SalePostponedEntity salepostponed;

	/** The jewel. */
	@OneToOne
	@JoinColumn(name = "IDJEWEL")
	private JewelEntity jewel;

	public Long getIdsalepostponedjewel() {
		return idsalepostponedjewel;
	}

	public void setIdsalepostponedjewel(Long idsalepostponedjewel) {
		this.idsalepostponedjewel = idsalepostponedjewel;
	}

	public SalePostponedEntity getSalepostponed() {
		return salepostponed;
	}

	public void setSalepostponed(SalePostponedEntity salepostponed) {
		this.salepostponed = salepostponed;
	}

	public JewelEntity getJewel() {
		return jewel;
	}

	public void setJewel(JewelEntity jewel) {
		this.jewel = jewel;
	}

}
