package com.atmj.jsboot.dbaccess.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ips")
public class IPEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDIP")
	private Long idip;

	@Column(name = "IPNAME")
	private String ipname;

	@Column(name = "IP")
	private String ip;

	public Long getIdip() {
		return idip;
	}

	public void setIdip(Long idip) {
		this.idip = idip;
	}

	public String getIpname() {
		return ipname;
	}

	public void setIpname(String ipname) {
		this.ipname = ipname;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
