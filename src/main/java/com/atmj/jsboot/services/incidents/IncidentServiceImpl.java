package com.atmj.jsboot.services.incidents;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.IncidentEntity;
import com.atmj.jsboot.dbaccess.entities.UserEntity;
import com.atmj.jsboot.dbaccess.repositories.IncidentRepository;
import com.atmj.jsboot.dbaccess.repositories.UsersRepository;
import com.atmj.jsboot.forms.SearchForm;
import com.atmj.jsboot.utils.date.DateUtil;
import com.atmj.jsboot.utils.string.Util;
import com.atmj.services.EmailService;

@Service
public class IncidentServiceImpl implements IncidentService {

	@Autowired
	private IncidentRepository incidentRepository;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private ModelMapper mapper;

	@Override
	public void save(Incident incident) {
		IncidentEntity entity = mapper.map(incident, IncidentEntity.class);
		entity.setCreationdate(new Date());
		entity.setUsername(usersRepository.findByUsername(incident.getUser()));
		entity = incidentRepository.save(entity);
		incident.setIdincident(entity.getIdincident());
		emailService.sendSimpleMessage("mangeles.sanchez0807@gmail.com", "NUEVA INCIDENCIA",
				"Número de incidencia: " + entity.getIdincident() + " usuario: " + incident.getUser() + " descripción: "
						+ incident.getDescription());
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
		UserEntity entity = usersRepository.findByUsername(user);
		return mapper(incidentRepository.findByUsernameAndState(entity, Boolean.FALSE));
	}

	@Override
	public List<Incident> searchByUserAndDates(String user, SearchForm form) {
		Date from = DateUtil.getDate(form.getDatefrom());
		Date until;
		String suntil = form.getDateuntil();
		if (Util.isEmpty(suntil)) {
			until = new Date();
		} else {
			until = DateUtil.getDate(suntil);
		}
		return mapper(incidentRepository.findByUsernameAndCreationdateBetween(usersRepository.findByUsername(user),
				from, until));
	}
}
