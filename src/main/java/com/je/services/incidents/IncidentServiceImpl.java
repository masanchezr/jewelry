package com.je.services.incidents;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.dbaccess.entities.IncidentEntity;
import com.je.dbaccess.entities.UserEntity;
import com.je.dbaccess.repositories.IncidentRepository;
import com.je.forms.SearchForm;
import com.je.services.mails.MailService;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

public class IncidentServiceImpl implements IncidentService {

	@Autowired
	private IncidentRepository incidentRepository;

	@Autowired
	private Mapper mapper;

	@Override
	public void save(Incident incident) {
		MailService mailIncidentService;
		IncidentEntity entity = mapper.map(incident, IncidentEntity.class);
		entity.setCreationdate(new Date());
		entity = incidentRepository.save(entity);
		incident.setIdincident(entity.getIdincident());
		mailIncidentService = new MailService("Número de incidencia: " + entity.getIdincident() + " usuario: "
				+ incident.getUser() + " descripción: " + incident.getDescription(), null, "NUEVA INCIDENCIA");
		mailIncidentService.start();
	}

	@Override
	public void resolve(Incident incident) {
		IncidentEntity entity = incidentRepository.findById(incident.getIdincident()).orElse(null);
		if (entity != null) {
			entity.setDescription(incident.getDescription());
			entity.setState(Boolean.TRUE);
			incidentRepository.save(entity);
		}
	}

	@Override
	public List<Incident> searchAllIncidents() {
		return mapper(incidentRepository.findAll());
	}

	@Override
	public List<Incident> searchPending() {
		return mapper(incidentRepository.findByState(Boolean.FALSE));
	}

	@Override
	public List<Incident> searchByUser(String user) {
		UserEntity entity = new UserEntity();
		entity.setUsername(user);
		return mapper(incidentRepository.findByUsername(entity));
	}

	private List<Incident> mapper(Iterable<IncidentEntity> entities) {
		List<Incident> incidents = new ArrayList<>();
		Iterator<IncidentEntity> ientities = entities.iterator();
		Incident incident;
		IncidentEntity entity;
		while (ientities.hasNext()) {
			entity = ientities.next();
			incident = new Incident();
			mapper.map(entity, incident);
			incident.setDate(entity.getCreationdate());
			incident.setUser(entity.getUsername().getUsername());
			incidents.add(incident);
		}
		return incidents;
	}

	@Override
	public Incident searchIncident(Incident incident) {
		IncidentEntity entity = incidentRepository.findById(incident.getIdincident()).orElse(null);
		if (entity != null) {
			return mapper.map(entity, Incident.class);
		} else {
			return null;
		}
	}

	@Override
	public List<Incident> searchPendingByUser(String user) {
		UserEntity entity = new UserEntity();
		entity.setUsername(user);
		return mapper(incidentRepository.findByUsernameAndState(entity, Boolean.FALSE));
	}

	@Override
	public List<Incident> searchByUserAndDates(String user, SearchForm form) {
		UserEntity entity = new UserEntity();
		Date from = DateUtil.getDate(form.getDatefrom());
		Date until;
		String suntil = form.getDateuntil();
		if (Util.isEmpty(suntil)) {
			until = new Date();
		} else {
			until = DateUtil.getDate(suntil);
		}
		entity.setUsername(user);
		return mapper(incidentRepository.findByUsernameAndCreationdateBetween(entity, from, until));
	}
}
