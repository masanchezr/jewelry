package com.atmj.jsboot.dbaccess.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.atmj.jsboot.utils.constants.Constants;

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
