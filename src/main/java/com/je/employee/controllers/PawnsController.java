package com.je.employee.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.je.services.pawns.RenovationDates;
import com.je.services.places.PlaceService;
import com.je.services.tracks.TrackService;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;
import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

/**
 * The Class PawnsController.
 */
@Controller
public class PawnsController {

	@Autowired
	private MetalService materialService;

	@Autowired
	private NationService nationservice;

	/** The pawn service. */
	@Autowired
	private PawnService pawnService;

	@Autowired
	private PlaceService placeService;

	@Autowired
	private TrackService trackservice;

	/** The new pawn form validator. */
	@Autowired
	private NewPawnFormValidator newPawnFormValidator;

	/** The pawn form validator. */
	@Autowired
	private PawnFormValidator pawnFormValidator;

	private static final String VIEWNEWPAWN = "employee/pawns/newpawn/newPawn";
	private static final String VIEWSEARCHCLIENT = "employee/pawns/newpawn/searchclient";
	private static final String FORMSEARCHPAWN = "searchPawnForm";
	private static final String VIEWSEARCHREMOVEPAWN = "employee/pawns/removepawn/searchpawn";

	/**
	 * Save pawn.
	 *
	 * @param pawn   the pawn
	 * @param result the result
	 * @return the string
	 */
	@PostMapping("/employee/savePawn")
	public ModelAndView savePawn(@ModelAttribute(ConstantsJsp.PAWNFORM) NewPawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		newPawnFormValidator.validate(pawn, result);
		if (result.hasErrors()) {
			List<MetalEntity> materials = materialService.getAllMetalsActive();
			Iterator<MetalEntity> imaterials = materials.iterator();
			List<ObjectPawnEntity> lop = new ArrayList<>();
			while (imaterials.hasNext()) {
				ObjectPawnEntity op = new ObjectPawnEntity();
				op.setMetal(imaterials.next());
				lop.add(op);
			}
			pawn.setObjects(lop);
			model.addObject(ConstantsJsp.PAWNFORM, pawn);
			model.addObject(Constants.TRACKS, trackservice.getTracks());
			model.addObject(Constants.NATIONS, nationservice.getNations());
			model.setViewName(VIEWNEWPAWN);
		} else {
			Calendar c = Calendar.getInstance();
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			String numpawn = pawn.getNumpawn();
			pawn.setUser(user);
			pawn.setRetired(false);
			boolean repeat = pawnService.isRepeatNumber(numpawn, user, c.get(Calendar.YEAR));
			if (repeat) {
				List<MetalEntity> materials = materialService.getAllMetalsActive();
				Iterator<MetalEntity> imaterials = materials.iterator();
				List<ObjectPawnEntity> lop = new ArrayList<>();
				while (imaterials.hasNext()) {
					ObjectPawnEntity op = new ObjectPawnEntity();
					op.setMetal(imaterials.next());
					lop.add(op);
				}
				pawn.setObjects(lop);
				model.addObject(ConstantsJsp.PAWNFORM, pawn);
				model.addObject(Constants.TRACKS, trackservice.getTracks());
				model.addObject(Constants.NATIONS, nationservice.getNations());
				model.setViewName(VIEWNEWPAWN);
				result.rejectValue(Constants.NUMPAWN, "numrepeated");
			} else {
				String sdate = pawn.getCreationdate();
				if (Util.isEmpty(sdate)) {
					sdate = DateUtil.getStringDateddMMyyyy(new Date());
				}
				model.addObject(ConstantsJsp.DAILY, pawnService.save(pawn));
				model.setViewName(ConstantsJsp.VIEWDAILYARROW);
				model.addObject(ConstantsJsp.DATEDAILY, sdate);
			}
		}
		return model;
	}

	/**
	 * Result search pawn.
	 *
	 * @param idpawn the idpawn
	 * @return the model and view
	 */
	@PostMapping("/employee/resultSearchPawn")
	public ModelAndView resultSearchPawn(@RequestParam(Constants.IDPAWN) Long idpawn) {
		Pawn pawn = pawnService.searchByIdpawn(idpawn);
		ModelAndView model = new ModelAndView("employee/pawns/searchpawns/resultpawns");
		model.addObject("pawn", pawn);
		return model;
	}

	/**
	 * Segunda pantalla
	 *
	 * @return the model and view
	 */
	@PostMapping("/employee/newPawn")
	public ModelAndView newPawn(@ModelAttribute(ConstantsJsp.PAWNFORM) NewPawn pawn, BindingResult errors) {
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		String dni = Util.refactorNIF(pawn.getNif());
		ModelAndView model = new ModelAndView();
		if (dni != null && dni.length() > 13) {
			errors.rejectValue(ConstantsJsp.NIF, "niftoolong");
			model.setViewName(VIEWSEARCHCLIENT);
		} else if (!Util.isNifNie(dni)) {
			errors.rejectValue(ConstantsJsp.NIF, "nifnotvalid");
			model.setViewName(VIEWSEARCHCLIENT);
		} else {
			pawn = pawnService.searchClient(dni);
			pawn.setUser(user);
			List<MetalEntity> materials = materialService.getAllMetalsActive();
			Iterator<MetalEntity> imaterials = materials.iterator();
			List<ObjectPawnEntity> lop = new ArrayList<>();
			while (imaterials.hasNext()) {
				ObjectPawnEntity op = new ObjectPawnEntity();
				op.setMetal(imaterials.next());
				lop.add(op);
			}
			pawn.setObjects(lop);
			model.addObject(Constants.TRACKS, trackservice.getTracks());
			model.addObject(Constants.NATIONS, nationservice.getNations());
			model.setViewName(VIEWNEWPAWN);
		}
		model.addObject(ConstantsJsp.PAWNFORM, pawn);
		return model;
	}

	/**
	 * Primera Pantalla
	 * 
	 * @return ModelAndView
	 */
	@GetMapping("/employee/searchclientpawn")
	public ModelAndView searchClientPawn() {
		ModelAndView model = new ModelAndView(VIEWSEARCHCLIENT);
		NewPawn pawn = new NewPawn();
		model.addObject(ConstantsJsp.PAWNFORM, pawn);
		return model;
	}

	/**
	 * Removes the pawn.
	 *
	 * @return the model and view
	 */
	@GetMapping("/employee/removePawn")
	public ModelAndView removePawn() {
		ModelAndView model = new ModelAndView(VIEWSEARCHREMOVEPAWN);
		model.addObject(FORMSEARCHPAWN, new Pawn());
		return model;
	}

	/**
	 * Renew pawn.
	 *
	 * @return the model and view
	 */
	@GetMapping("/employee/renewPawn")
	public ModelAndView renewPawn() {
		ModelAndView model = new ModelAndView("employee/pawns/renewpawn/searchpawn");
		model.addObject(FORMSEARCHPAWN, new Pawn());
		return model;
	}

	/**
	 * Search renew pawn.
	 *
	 * @param pawn   the pawn
	 * @param result the result
	 * @return the model and view
	 */
	@GetMapping("/employee/searchRenewPawn")
	public ModelAndView searchRenewPawn(@ModelAttribute("searchPawnForm") Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		pawnFormValidator.validate(pawn, result);
		if (result.hasErrors()) {
			model.addObject(FORMSEARCHPAWN, new Pawn());
			model.setViewName("employee/pawns/renewpawn/searchpawn");
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			pawn.setUser(user);
			List<Pawn> pawns = pawnService.searchRenewByNumpawn(pawn);
			model.addObject(ConstantsJsp.PAWNFORM, new Pawn());
			model.addObject(ConstantsJsp.PAWNS, pawns);
			model.setViewName("employee/pawns/renewpawn/renewpawn");
		}
		return model;
	}

	/**
	 * Renew.
	 *
	 * @param pawn the pawn
	 * @return the string
	 */
	@GetMapping("/employee/renewpawn")
	public ModelAndView renew(@ModelAttribute(ConstantsJsp.PAWNFORM) Pawn pawn) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		pawn.setUser(user);
		model.addObject(ConstantsJsp.DAILY, pawnService.renew(pawn));
		model.setViewName(ConstantsJsp.VIEWDAILYARROW);
		model.addObject(ConstantsJsp.DATEDAILY, DateUtil.getDateFormated(new Date()));
		return model;
	}

	/**
	 * Removes the.
	 *
	 * @param pawn the pawn
	 * @return the string
	 */
	@PostMapping("/employee/removepawn")
	public ModelAndView remove(@ModelAttribute(ConstantsJsp.PAWNFORM) Pawn pawn) {
		ModelAndView model = new ModelAndView();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> collection = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> itcollection = collection.iterator();
		String role = null;
		while (itcollection.hasNext()) {
			role = itcollection.next().getAuthority();
		}
		if ((Constants.ROLE_AR.equals(role) && pawn.getMonths() <= 0) || pawn.getId() == null) {
			model.setViewName("employee/noselected");
		} else {
			model.addObject(ConstantsJsp.DAILY, pawnService.remove(pawn));
			model.setViewName(ConstantsJsp.VIEWDAILYARROW);
			model.addObject(ConstantsJsp.DATEDAILY, DateUtil.getStringDateddMMyyyy(new Date()));
		}
		return model;
	}

	/**
	 * Search remove pawn.
	 *
	 * @param pawn   the pawn
	 * @param result the result
	 * @return the model and view
	 */
	@PostMapping("/employee/searchRemovePawn")
	public ModelAndView searchRemovePawn(@ModelAttribute(ConstantsJsp.PAWNFORM) Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		pawnFormValidator.validate(pawn, result);
		if (result.hasErrors()) {
			model.addObject(ConstantsJsp.PAWNFORM, new Pawn());
			model.setViewName(VIEWSEARCHREMOVEPAWN);
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
			model.addObject(ConstantsJsp.PAWNS, pawns);
			if (Constants.ROLE_AR.equals(role)) {
				model.setViewName("employee/pawns/removepawn/removepawnfive");
			} else {
				model.setViewName("employee/pawns/removepawn/removepawn");
			}
		}
		return model;
	}

	@GetMapping("/employee/searchrenovations")
	public ModelAndView searchrenovations() {
		ModelAndView model = new ModelAndView("employee/pawns/searchrenovations/searchpawn");
		model.addObject(ConstantsJsp.PAWNFORM, new Pawn());
		return model;
	}

	@PostMapping("/employee/resultRenovationsPawns")
	public ModelAndView resultRenovationsPawns(@ModelAttribute(ConstantsJsp.PAWNFORM) Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		pawnFormValidator.validate(pawn, result);
		if (result.hasErrors()) {
			model.setViewName("employee/pawns/searchrenovations/searchpawn");
			model.addObject(ConstantsJsp.PAWNFORM, pawn);
		} else {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String user = authentication.getName();
			pawn.setPlace(placeService.getPlaceUser(user));
			model.setViewName("employee/pawns/searchrenovations/resultpawn");
			model.addObject(ConstantsJsp.PAWNS, pawnService.searchByNumpawn(pawn));
		}
		return model;
	}

	@PostMapping("/employee/resultrenovations")
	public ModelAndView resultrenovations(@ModelAttribute(ConstantsJsp.PAWNFORM) Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		Long idpawn = pawn.getId();
		if (idpawn == null) {
			model.setViewName("errorupdatepawn");
		} else {
			List<RenovationDates> renovations = pawnService.searchRenovations(idpawn);
			model.setViewName("employee/pawns/searchrenovations/renovations");
			model.addObject("renovations", renovations);
		}
		return model;
	}
}
