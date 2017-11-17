package com.je.dbaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tracks")
public class TrackEntity {

	@Id
	@Column(name = "idtrack")
	private Long idtrack;

	@Column(name = "track")
	private String track;

	public Long getIdtrack() {
		return idtrack;
	}

	public void setIdtrack(Long idtrack) {
		this.idtrack = idtrack;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}
}
