package com.je.jsboot.employee.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

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

import com.je.jsboot.admin.forms.AdminForm;
import com.je.jsboot.dbaccess.entities.MetalEntity;
import com.je.jsboot.dbaccess.entities.ObjectPawnEntity;
import com.je.jsboot.employee.validators.NewPawnFormValidator;
import com.je.jsboot.employee.validators.ReturnPawnFormValidator;
import com.je.jsboot.services.metal.MetalService;
import com.je.jsboot.services.nations.NationService;
import com.je.jsboot.services.pawns.NewPawn;
import com.je.jsboot.services.pawns.Pawn;
import com.je.jsboot.services.pawns.PawnService;
import com.je.jsboot.services.pawns.RenovationDates;
import com.je.jsboot.services.places.PlaceService;
import com.je.jsboot.services.tracks.TrackService;
import com.je.jsboot.utils.constants.Constants;
import com.je.jsboot.utils.constants.ConstantsViews;
import com.je.jsboot.utils.date.DateUtil;
import com.je.jsboot.utils.string.Util;

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

	@Autowired
	private NewPawnFormValidator newPawnFormValidator;

	@Autowired
	private ReturnPawnFormValidator returnPawnFormValidator;

	private static final String VIEWNEWPAWN = "employee/pawns/newpawn/newPawn";
	private static final String VIEWSEARCHCLIENT = "employee/pawns/newpawn/searchclient";
	private static final String FORMSEARCHPAWN = "searchPawnForm";
	private static final String VIEWSEARCHREMOVEPAWN = "employee/pawns/removepawn/searchpawn";

	/**
	 * Primera Pantalla NUEVO EMPEÑO
	 * 
	 * @return ModelAndView
	 */
	@GetMapping("/employee/searchclientpawn")
	public ModelAndView searchClientPawn() {
		ModelAndView model = new ModelAndView(VIEWSEARCHCLIENT);
		NewPawn pawn = new NewPawn();
		model.addObject(ConstantsViews.PAWNFORM, pawn);
		return model;
	}

	/**
	 * Primera Pantalla REEMPEÑO
	 * 
	 * @return ModelAndView
	 */
	@GetMapping("/employee/searchclientreturnpawn")
	public ModelAndView searchClientReturnPawn() {
		ModelAndView model = new ModelAndView("employee/pawns/returnpawn/searchclient");
		NewPawn pawn = new NewPawn();
		model.addObject(ConstantsViews.PAWNFORM, pawn);
		return model;
	}

	@PostMapping("/employee/searchreturnpawn")
	public ModelAndView searchreturnpawn(@ModelAttribute(ConstantsViews.PAWNFORM) NewPawn pawn, BindingResult errors) {
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		String dni = Util.refactorNIF(pawn.getNif());
		ModelAndView model = new ModelAndView();
		if (dni != null && dni.length() > 13) {
			errors.rejectValue(ConstantsViews.NIF, "niftoolong");
			model.setViewName(VIEWSEARCHCLIENT);
		} else if (!Util.isNifNie(dni)) {
			errors.rejectValue(ConstantsViews.NIF, "nifnotvalid");
			model.setViewName(VIEWSEARCHCLIENT);
		} else {
			model.addObject("pawns", pawnService.getByNIFAndUserAndRetiredAndReturn(dni, user));
			model.setViewName("employee/pawns/returnpawn/resultpawn");
		}
		return model;
	}

	@PostMapping("/employee/newreturnpawn")
	public ModelAndView newreturnpawn(@ModelAttribute(ConstantsViews.PAWNFORM) Pawn pawn) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		NewPawn p = pawnService.findByIdpawn(pawn.getId());
		model.setViewName("employee/pawns/returnpawn/newPawn");
		model.addObject(ConstantsViews.PAWNFORM, p);
		model.addObject(Constants.NATIONS, nationservice.getNations());
		model.addObject(Constants.TRACKS, trackservice.getTracks());
		model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
		return model;
	}

	@PostMapping("/employee/savereturnpawn")
	public ModelAndView savereturnPawn(@ModelAttribute(ConstantsViews.PAWNFORM) NewPawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		returnPawnFormValidator.validate(pawn, result);
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
			model.addObject(ConstantsViews.PAWNFORM, pawn);
			model.addObject(Constants.TRACKS, trackservice.getTracks());
			model.addObject(Constants.NATIONS, nationservice.getNations());
			model.setViewName("employee/pawns/returnpawn/newPawn");
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			pawn.setUser(user);
			pawn.setRetired(false);
			String sdate = pawn.getCreationdate();
			if (Util.isEmpty(sdate)) {
				sdate = DateUtil.getStringDateddMMyyyy(new Date());
			}
			model.addObject(ConstantsViews.DAILY, pawnService.saveReturnPawn(pawn));
			model.setViewName(ConstantsViews.VIEWDAILYARROW);
			model.addObject(ConstantsViews.DATEDAILY, sdate);
		}
		return model;
	}

	/**
	 * Save pawn.
	 *
	 * @param pawn   the pawn
	 * @param result the result
	 * @return the string
	 */
	@PostMapping("/employee/savePawn")
	public ModelAndView savePawn(@ModelAttribute(ConstantsViews.PAWNFORM) NewPawn pawn, BindingResult result) {
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
			model.addObject(ConstantsViews.PAWNFORM, pawn);
			model.addObject(Constants.TRACKS, trackservice.getTracks());
			model.addObject(Constants.NATIONS, nationservice.getNations());
			model.setViewName(VIEWNEWPAWN);
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			pawn.setUser(user);
			pawn.setRetired(false);
			String sdate = pawn.getCreationdate();
			if (Util.isEmpty(sdate)) {
				sdate = DateUtil.getStringDateddMMyyyy(new Date());
			}
			model.addObject(ConstantsViews.DAILY, pawnService.save(pawn));
			model.setViewName(ConstantsViews.VIEWDAILYARROW);
			model.addObject(ConstantsViews.DATEDAILY, sdate);
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
	public ModelAndView newPawn(@ModelAttribute(ConstantsViews.PAWNFORM) NewPawn pawn, BindingResult errors) {
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		String dni = Util.refactorNIF(pawn.getNif());
		ModelAndView model = new ModelAndView();
		if (dni != null && dni.length() > 13) {
			errors.rejectValue(ConstantsViews.NIF, "niftoolong");
			model.setViewName(VIEWSEARCHCLIENT);
		} else if (!Util.isNifNie(dni)) {
			errors.rejectValue(ConstantsViews.NIF, "nifnotvalid");
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
		model.addObject(ConstantsViews.PAWNFORM, pawn);
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
	@PostMapping("/employee/searchRenewPawn")
	public ModelAndView searchRenewPawn(@Valid Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.addObject(FORMSEARCHPAWN, new Pawn());
			model.setViewName("employee/pawns/renewpawn/searchpawn");
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			pawn.setUser(user);
			List<Pawn> pawns = pawnService.searchRenewByNumpawn(pawn);
			model.addObject(ConstantsViews.PAWNFORM, new Pawn());
			model.addObject(ConstantsViews.PAWNS, pawns);
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
	@PostMapping("/employee/renewpawn")
	public ModelAndView renew(@ModelAttribute(ConstantsViews.PAWNFORM) Pawn pawn) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		pawn.setUser(user);
		model.addObject(ConstantsViews.DAILY, pawnService.renew(pawn));
		model.setViewName(ConstantsViews.VIEWDAILYARROW);
		model.addObject(ConstantsViews.DATEDAILY, DateUtil.getStringDateddMMyyyy(new Date()));
		return model;
	}

	/**
	 * Removes the.
	 *
	 * @param pawn the pawn
	 * @return the string
	 */
	@PostMapping("/employee/removepawn")
	public ModelAndView remove(@ModelAttribute(ConstantsViews.PAWNFORM) Pawn pawn) {
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
			model.addObject(ConstantsViews.DAILY, pawnService.remove(pawn));
			model.setViewName(ConstantsViews.VIEWDAILYARROW);
			model.addObject(ConstantsViews.DATEDAILY, DateUtil.getStringDateddMMyyyy(new Date()));
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
	public ModelAndView searchRemovePawn(@Valid Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.addObject(ConstantsViews.PAWNFORM, new Pawn());
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
			model.addObject(ConstantsViews.PAWNS, pawns);
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
		model.addObject(ConstantsViews.PAWNFORM, new Pawn());
		return model;
	}

	@PostMapping("/employee/resultRenovationsPawns")
	public ModelAndView resultRenovationsPawns(@Valid Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.setViewName("employee/pawns/searchrenovations/searchpawn");
			model.addObject(ConstantsViews.PAWNFORM, pawn);
		} else {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String user = authentication.getName();
			pawn.setPlace(placeService.getPlaceUser(user));
			model.setViewName("employee/pawns/searchrenovations/resultpawn");
			model.addObject(ConstantsViews.PAWNS, pawnService.searchByNumpawn(pawn));
		}
		return model;
	}

	@PostMapping("/employee/resultrenovations")
	public ModelAndView resultrenovations(@ModelAttribute(ConstantsViews.PAWNFORM) Pawn pawn, BindingResult result) {
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
