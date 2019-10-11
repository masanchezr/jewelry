package com.je.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.admin.validators.UpdatePawnFormValidator;
import com.je.employee.validators.PawnFormValidator;
import com.je.forms.SearchForm;
import com.je.services.metal.MetalService;
import com.je.services.nations.NationService;
import com.je.services.pawns.NewPawn;
import com.je.services.pawns.Pawn;
import com.je.services.pawns.PawnService;
import com.je.services.pawns.Quarter;
import com.je.services.pawns.RenovationDates;
import com.je.services.places.PlaceService;
import com.je.services.tracks.TrackService;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;
import com.je.utils.string.Util;
import com.je.validators.SearchFormValidator;

/**
 * The Class PawnsAdminController.
 */
@Controller
public class PawnsAdminController {

	/** The pawn service. */
	@Autowired
	private PawnService pawnService;

	/** The place service. */
	@Autowired
	private PlaceService placeService;

	/** The place service. */
	@Autowired
	private MetalService materialService;

	@Autowired
	private NationService nationservice;

	@Autowired
	private TrackService trackservice;

	/** The pawn form validator. */
	@Autowired
	private PawnFormValidator pawnFormValidator;

	/** The new pawn form validator. */
	@Autowired
	private UpdatePawnFormValidator updatePawnFormValidator;

	@Autowired
	private SearchFormValidator adminSearchFormValidator;

	private static final String VIEWSEARCHPAWN = "admin/pawns/searchpawns/searchpawn";
	private static final String VIEWUPDATEPAWN = "admin/pawns/searchpawns/updatepawn";
	private static final String VIEWRENOVATIONS = "admin/pawns/searchrenovations/renovations";

	/**
	 * Search pawns.
	 *
	 * @return the model and view
	 */
	@GetMapping("/searchPawns")
	public ModelAndView searchPawns() {
		ModelAndView model = new ModelAndView(VIEWSEARCHPAWN);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		model.addObject(ConstantsJsp.PAWNFORM, new Pawn());
		return model;
	}

	/**
	 * Result pawns.
	 *
	 * @param pawn   the pawn
	 * @param result the result
	 * @return the model and view
	 */
	@PostMapping("/resultPawns")
	public ModelAndView resultPawns(@ModelAttribute(ConstantsJsp.PAWNFORM) Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		pawnFormValidator.validate(pawn, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWSEARCHPAWN);
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
			model.addObject(ConstantsJsp.PAWNFORM, new Pawn());
		} else {
			model.setViewName("admin/pawns/searchpawns/resultpawn");
			model.addObject(ConstantsJsp.PAWNS, pawnService.searchByNumpawn(pawn));
		}
		return model;
	}

	/**
	 * Update pawn.
	 *
	 * @param pawn   the pawn
	 * @param result the result
	 * @return the model and view
	 */
	@PostMapping("/updatepawn")
	public ModelAndView updatePawn(@ModelAttribute(ConstantsJsp.PAWNFORM) Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		Long idpawn = pawn.getId();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		if (idpawn == null) {
			model.setViewName("admin/pawns/searchpawns/errorupdatepawn");
		} else {
			NewPawn p = pawnService.findByIdpawn(idpawn);
			model.setViewName(VIEWUPDATEPAWN);
			model.addObject(ConstantsJsp.PAWNFORM, p);
			model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
			model.addObject(Constants.NATIONS, nationservice.getNations());
			model.addObject(Constants.TRACKS, trackservice.getTracks());
		}
		return model;
	}

	@GetMapping("/searchpawn{id}")
	public ModelAndView searchPawn(@PathVariable long id) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		NewPawn p = pawnService.findByIdpawn(id);
		model.setViewName(VIEWUPDATEPAWN);
		model.addObject(ConstantsJsp.PAWNFORM, p);
		model.addObject(Constants.NATIONS, nationservice.getNations());
		model.addObject(Constants.TRACKS, trackservice.getTracks());
		model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
		return model;
	}

	@PostMapping("/resultrenovations")
	public ModelAndView resultrenovations(@ModelAttribute(ConstantsJsp.PAWNFORM) Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		Long idpawn = pawn.getId();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		if (idpawn == null) {
			model.setViewName("admin/pawns/searchpawns/errorupdatepawn");
		} else {
			List<RenovationDates> renovations = pawnService.searchRenovations(idpawn);
			model.setViewName(VIEWRENOVATIONS);
			model.addObject("renovations", renovations);
		}
		return model;
	}

	@GetMapping("/renovations{id}")
	public ModelAndView renovations(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		List<RenovationDates> renovations = pawnService.searchRenovations(id);
		model.setViewName(VIEWRENOVATIONS);
		model.addObject("renovations", renovations);
		return model;
	}

	/**
	 * Save pawn.
	 *
	 * @param pawn   the pawn
	 * @param result the result
	 * @return the model and view
	 */
	@PostMapping("/savePawn")
	public ModelAndView savePawn(@ModelAttribute(ConstantsJsp.PAWNFORM) NewPawn pawn, BindingResult result) {
		ModelAndView model;
		updatePawnFormValidator.validate(pawn, result);
		if (result.hasErrors()) {
			model = new ModelAndView();
			model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
			model.setViewName(VIEWUPDATEPAWN);
			model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
			model.addObject(Constants.NATIONS, nationservice.getNations());
			model.addObject(Constants.TRACKS, trackservice.getTracks());
			model.addObject("pawn", pawn);
		} else {
			model = searchPawns();
			pawn.setNif(Util.refactorNIF(pawn.getNif()));
			pawnService.update(pawn);
		}
		return model;
	}

	@GetMapping("/searchrenovations")
	public ModelAndView searchrenovations() {
		ModelAndView model = new ModelAndView(VIEWRENOVATIONS);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		model.addObject(ConstantsJsp.PAWNFORM, new Pawn());
		return model;
	}

	@PostMapping("/resultRenovationsPawns")
	public ModelAndView resultRenovationsPawns(@ModelAttribute(ConstantsJsp.PAWNFORM) Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		pawnFormValidator.validate(pawn, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWSEARCHPAWN);
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
			model.addObject(ConstantsJsp.PAWNFORM, new Pawn());
		} else {
			model.setViewName("admin/pawns/searchrenovations/resultpawn");
			model.addObject(ConstantsJsp.PAWNS, pawnService.searchByNumpawn(pawn));
		}
		return model;
	}

	@GetMapping("/searchquarterpawns")
	public ModelAndView searchquarterpawns() {
		ModelAndView model = new ModelAndView("admin/pawns/quarters/searchquarter");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchForm());
		return model;
	}

	@PostMapping("/quarterpawns")
	public ModelAndView quarterpawns(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchForm search, BindingResult result) {
		ModelAndView model = new ModelAndView();
		adminSearchFormValidator.validate(search, result);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		if (result.hasErrors()) {
			model.setViewName("admin/pawns/quarters/searchquarter");
		} else {
			Quarter quarter = pawnService.searchGramsByDates(search.getDatefrom(), search.getDateuntil());
			model.setViewName("admin/pawns/quarters/quarter");
			model.addObject("quarter", quarter);
		}
		model.addObject(ConstantsJsp.FORMSEARCH, search);
		return model;
	}

	@GetMapping("/searchcommissions")
	public ModelAndView searchCommissions() {
		ModelAndView model = new ModelAndView("admin/pawns/commissions/searchcommissions");
		modelComun(model);
		return model;
	}

	@PostMapping("/commissions")
	public ModelAndView commisions(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchForm search, BindingResult result) {
		ModelAndView model = new ModelAndView();
		adminSearchFormValidator.validate(search, result);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		if (result.hasErrors()) {
			model.addObject(ConstantsJsp.FORMSEARCH, search);
			model.setViewName("admin/pawns/commissions/searchcommissions");
		} else {
			model.addObject("commissions",
					pawnService.getCommissions(search.getDatefrom(), search.getDateuntil(), search.getPlace()));
			model.setViewName("admin/pawns/commissions/commissions");
		}
		return model;
	}

	@GetMapping("/outofdate")
	public ModelAndView outofdate() {
		ModelAndView model = new ModelAndView("admin/pawns/outofdate/search");
		modelComun(model);
		return model;
	}

	@PostMapping("/searchpawnsoutofdate")
	public ModelAndView searchPawnsoutofdate(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchForm search) {
		ModelAndView model = new ModelAndView("admin/pawns/outofdate/resultpawn");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.PAWNS, pawnService.pawnsOutofdate(search.getPlace()));
		model.addObject(ConstantsJsp.PAWNFORM, new Pawn());
		return model;
	}

	@GetMapping("/investedmoney")
	public ModelAndView searchInvestedMoney() {
		ModelAndView model = new ModelAndView("admin/pawns/investedmoney/searchinvestedmoney");
		model.addObject(ConstantsJsp.PAWNFORM, new Pawn());
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
		return model;
	}

	@PostMapping("/resultinvestedmoney")
	public ModelAndView resultInvestedMoney(@ModelAttribute(ConstantsJsp.PAWNFORM) Pawn pawn) {
		ModelAndView model = new ModelAndView("admin/pawns/investedmoney/investedmoney");
		pawnService.sumPawnsActiveByPlace(pawn.getPlace());
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject("investedmoney", pawnService.sumPawnsActiveByPlace(pawn.getPlace()));
		return model;
	}

	private void modelComun(ModelAndView model) {
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchForm());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlacesActive());
	}
}
