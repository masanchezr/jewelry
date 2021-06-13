package com.je.jsboot.dbaccess.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.je.jsboot.utils.constants.Constants;

@Entity
@Table(name = "salespostponedjewels")
public class SalePostPonedJewel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idsalepostponedjewel")
	private Long idsalepostponedjewel;

	@ManyToOne
	@JoinColumn(name = Constants.IDSALEPOSTPONED)
	private SalePostponedEntity salepostponed;

	/** The jewel. */
	@OneToOne(cascade = CascadeType.MERGE)
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
