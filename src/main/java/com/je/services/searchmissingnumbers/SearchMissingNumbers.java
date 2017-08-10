package com.je.services.searchmissingnumbers;

import com.je.dbaccess.entities.PlaceEntity;

public class SearchMissingNumbers {

	private Long numfrom;

	private Long numuntil;

	private int year;

	private PlaceEntity place;

	public Long getNumfrom() {
		return numfrom;
	}

	public void setNumfrom(Long numfrom) {
		this.numfrom = numfrom;
	}

	public Long getNumuntil() {
		return numuntil;
	}

	public void setNumuntil(Long numuntil) {
		this.numuntil = numuntil;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public PlaceEntity getPlace() {
		return place;
	}

	public void setPlace(PlaceEntity place) {
		this.place = place;
	}

}
