package com.je.services.incidents;

import java.util.List;

import com.je.forms.SearchForm;

public interface IncidentService {

	public void save(Incident incident);

	public List<Incident> searchAllIncidents();

	public List<Incident> searchByUser(String user);

	public void resolve(Incident incident);

	public Incident searchIncident(Incident incident);

	public List<Incident> searchPending();

	public List<Incident> searchPendingByUser(String user);

	public List<Incident> searchByUserAndDates(String user, SearchForm form);
}
