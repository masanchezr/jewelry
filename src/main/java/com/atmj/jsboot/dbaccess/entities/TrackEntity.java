package com.atmj.jsboot.dbaccess.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.atmj.jsboot.utils.constants.Constants;

@Entity
@Table(name = Constants.TRACKS)
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
