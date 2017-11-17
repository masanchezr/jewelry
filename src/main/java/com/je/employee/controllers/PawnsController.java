package com.je.employee.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.entities.ObjectPawnEntity;
import com.je.employee.validators.NewPawnFormValidator;
import com.je.employee.validators.PawnFormValidator;
import com.je.services.metal.MetalService;
import com.je.services.nations.NationService;
import com.je.services.pawns.NewPawn;
import com.je.services.pawns.Pawn;
import com.je.services.pawns.PawnService;
import com.je.services.tracks.TrackService;
import com.je.utils.constants.Constants;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

/**
 * The Class PawnsController.
 */
@Controller
public class PawnsController {

	/** The pawn service. */
	@Autowired
	private PawnService pawnService;

	@Autowired
	private NationService nationservice;

	@Autowired
	private TrackService trackservice;

	/** The new pawn form validator. */
	@Autowired
	private NewPawnFormValidator newPawnFormValidator;

	/** The pawn form validator. */
	@Autowired
	private PawnFormValidator pawnFormValidator;

	@Autowired
	private MetalService metalService;

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(PawnsController.class);

	/**
	 * Save pawn.
	 *
	 * @param pawn
	 *            the pawn
	 * @param result
	 *            the result
	 * @return the string
	 */
	@RequestMapping(value = "/employee/savePawn")
	public ModelAndView savePawn(@ModelAttribute("pawnForm") NewPawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		newPawnFormValidator.validate(pawn, result);
		if (result.hasErrors()) {
			List<MetalEntity> metals = metalService.getAllMetalsActive();
			Iterator<MetalEntity> imetals = metals.iterator();
			List<ObjectPawnEntity> lop = new ArrayList<ObjectPawnEntity>();
			while (imetals.hasNext()) {
				ObjectPawnEntity op = new ObjectPawnEntity();
				op.setMetal(imetals.next());
				lop.add(op);
			}
			pawn.setObjects(lop);
			model.addObject("pawnForm", pawn);
			model.addObject("tracks", trackservice.getTracks());
			model.addObject("nations", nationservice.getNations());
			model.setViewName("newPawn");
		} else {
			Calendar c = Calendar.getInstance();
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			String numpawn = pawn.getNumpawn();
			log.info("usuario conectado:" + user);
			pawn.setUser(user);
			pawn.setRetired(false);
			boolean repeat = pawnService.isRepeatNumber(numpawn, user, c.get(Calendar.YEAR));
			if (repeat) {
				List<MetalEntity> metals = metalService.getAllMetalsActive();
				Iterator<MetalEntity> imetals = metals.iterator();
				List<ObjectPawnEntity> lop = new ArrayList<ObjectPawnEntity>();
				while (imetals.hasNext()) {
					ObjectPawnEntity op = new ObjectPawnEntity();
					op.setMetal(imetals.next());
					lop.add(op);
				}
				pawn.setObjects(lop);
				model.addObject("pawnForm", pawn);
				model.addObject("tracks", trackservice.getTracks());
				model.addObject("nations", nationservice.getNations());
				model.setViewName("newPawn");
				result.rejectValue("numpawn", "numrepited");
			} else {
				/*
				 * boolean isCorrectNumber = pawnService.isCorrectNumber(numpawn, user,
				 * c.get(Calendar.YEAR)); if (!isCorrectNumber) { List<MetalEntity> metals =
				 * metalService.getAllMetalsActive(); Iterator<MetalEntity> imetals =
				 * metals.iterator(); List<ObjectPawnEntity> lop = new
				 * ArrayList<ObjectPawnEntity>(); while (imetals.hasNext()) { ObjectPawnEntity
				 * op = new ObjectPawnEntity(); op.setMetal(imetals.next()); lop.add(op); }
				 * pawn.setObjects(lop); model.addObject("pawnForm", pawn);
				 * model.setViewName("newPawn"); result.rejectValue("numpawn", "wrongnumber"); }
				 * else {
				 */
				Date date = DateUtil.getDate(pawn.getCreationdate());
				if (date == null) {
					date = new Date();
				}
				model.addObject("daily", pawnService.save(pawn));
				model.setViewName("daily");
				model.addObject("datedaily", date);
				// }
			}
		}
		return model;
	}

	/**
	 * Result search pawn.
	 *
	 * @param idpawn
	 *            the idpawn
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/resultSearchPawn")
	public ModelAndView resultSearchPawn(@RequestParam("idpawn") Long idpawn) {
		Pawn pawn = pawnService.searchByIdpawn(idpawn);
		ModelAndView model = new ModelAndView("resultsearchpawn");
		model.addObject("pawn", pawn);
		return model;
	}

	/**
	 * New pawn.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/newPawn")
	public ModelAndView newPawn(@ModelAttribute("pawnForm") NewPawn pawn, BindingResult errors) {
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		String dni = Util.refactorNIF(pawn.getNif());
		ModelAndView model = new ModelAndView();
		if (dni != null && dni.length() > 12) {
			errors.rejectValue("nif", "niftoolong");
			model.setViewName("searchclient");
		} else if (!Util.isNifNie(dni)) {
			errors.rejectValue("nif", "nifnotvalid");
			model.setViewName("searchclient");
		} else {
			pawn = pawnService.searchClient(dni);
			pawn.setUser(user);
			List<MetalEntity> metals = metalService.getAllMetalsActive();
			Iterator<MetalEntity> imetals = metals.iterator();
			List<ObjectPawnEntity> lop = new ArrayList<ObjectPawnEntity>();
			while (imetals.hasNext()) {
				ObjectPawnEntity op = new ObjectPawnEntity();
				op.setMetal(imetals.next());
				lop.add(op);
			}
			pawn.setObjects(lop);
			model.addObject("tracks", trackservice.getTracks());
			model.addObject("nations", nationservice.getNations());
			model.setViewName("newPawn");
		}
		model.addObject("pawnForm", pawn);
		return model;
	}

	@RequestMapping(value = "/employee/searchclientpawn")
	public ModelAndView searchClientPawn() {
		ModelAndView model = new ModelAndView("searchclient");
		NewPawn pawn = new NewPawn();
		model.addObject("pawnForm", pawn);
		return model;
	}

	/**
	 * Removes the pawn.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/removePawn")
	public ModelAndView removePawn() {
		ModelAndView model = new ModelAndView("searchremovepawn");
		model.addObject("searchPawnForm", new Pawn());
		return model;
	}

	/**
	 * Renew pawn.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/renewPawn")
	public ModelAndView renewPawn() {
		ModelAndView model = new ModelAndView("searchrenewpawn");
		model.addObject("searchPawnForm", new Pawn());
		return model;
	}

	/**
	 * Search renew pawn.
	 *
	 * @param pawn
	 *            the pawn
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/searchRenewPawn")
	public ModelAndView searchRenewPawn(@ModelAttribute("searchPawnForm") Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		pawnFormValidator.validate(pawn, result);
		if (result.hasErrors()) {
			model.addObject("searchPawnForm", new Pawn());
			model.setViewName("searchrenewpawn");
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			pawn.setUser(user);
			List<Pawn> pawns = pawnService.searchRenewByNumpawn(pawn);
			model.addObject("pawnForm", new Pawn());
			model.addObject("pawns", pawns);
			model.setViewName("renewpawn");
		}
		return model;
	}

	/**
	 * Renew.
	 *
	 * @param pawn
	 *            the pawn
	 * @return the string
	 */
	@RequestMapping(value = "/employee/renewpawn")
	public ModelAndView renew(@ModelAttribute("pawnForm") Pawn pawn) {
		ModelAndView model = new ModelAndView();
		model.addObject("daily", pawnService.renew(pawn));
		model.setViewName("daily");
		model.addObject("datedaily", new Date());
		return model;
	}

	/**
	 * Removes the.
	 *
	 * @param pawn
	 *            the pawn
	 * @return the string
	 */
	@RequestMapping(value = "/employee/removepawn")
	public ModelAndView remove(@ModelAttribute("pawnForm") Pawn pawn) {
		ModelAndView model = new ModelAndView();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> collection = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> itcollection = collection.iterator();
		String role = null;
		while (itcollection.hasNext()) {
			role = itcollection.next().getAuthority();
		}
		if ((Constants.ROLE_NRA.equals(role) && pawn.getMonths() <= 0) || pawn.getId() == null) {
			model.setViewName("noselected");
		} else {
			model.addObject("daily", pawnService.remove(pawn));
			model.setViewName("daily");
			model.addObject("datedaily", new Date());
		}
		return model;
	}

	/**
	 * Search remove pawn.
	 *
	 * @param pawn
	 *            the pawn
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/searchRemovePawn")
	public ModelAndView searchRemovePawn(@ModelAttribute("pawnForm") Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		pawnFormValidator.validate(pawn, result);
		if (result.hasErrors()) {
			model.addObject("pawnForm", new Pawn());
			model.setViewName("searchremovepawn");
		} else {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String user = authentication.getName();
			Collection<? extends GrantedAuthority> collection = authentication.getAuthorities();
			Iterator<? extends GrantedAuthority> itcollection = collection.iterator();
			String role = null;
			while (itcollection.hasNext()) {
				role = itcollection.next().getAuthority();
			}
			pawn.setUser(user);
			List<Pawn> pawns = pawnService.searchRenewByNumpawn(pawn);
			model.addObject("pawns", pawns);
			if (Constants.ROLE_NRA.equals(role)) {
				model.setViewName("removepawnfive");
			} else {
				model.setViewName("removepawn");
			}
		}
		return model;
	}
}
