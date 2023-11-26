package com.atmj.jsboot.dbaccess.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "connections")
public class ConnectionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDCONNECTION")
	private Long idconnection;

	@ManyToOne
	@JoinColumn(name = "IDIP", referencedColumnName = "IDIP")
	private IPEntity ip;

	@CreatedDate
	@Column(name = "CREATIONDATE")
	private Date creationdate;

	public Long getIdconnection() {
		return idconnection;
	}

	public void setIdconnection(Long idconnection) {
		this.idconnection = idconnection;
	}

	public IPEntity getIp() {
		return ip;
	}

	public void setIp(IPEntity ip) {
		this.ip = ip;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

}
