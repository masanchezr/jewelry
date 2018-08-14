package com.je.dbaccess.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.je.utils.constants.Constants;

@Entity
@Table(name = Constants.TRACKS)
public class TrackEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4994736949155350112L;

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
