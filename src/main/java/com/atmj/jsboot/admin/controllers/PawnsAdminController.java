package com.atmj.jsboot.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.admin.forms.AdminForm;
import com.atmj.jsboot.admin.validators.UpdatePawnFormValidator;
import com.atmj.jsboot.forms.SearchForm;
import com.atmj.jsboot.services.metal.MetalService;
import com.atmj.jsboot.services.nations.NationService;
import com.atmj.jsboot.services.pawns.NewPawn;
import com.atmj.jsboot.services.pawns.Pawn;
import com.atmj.jsboot.services.pawns.PawnService;
import com.atmj.jsboot.services.pawns.Quarter;
import com.atmj.jsboot.services.pawns.RenovationDates;
import com.atmj.jsboot.services.places.PlaceService;
import com.atmj.jsboot.services.tracks.TrackService;
import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.string.Util;
import com.atmj.jsboot.validators.SearchFormValidator;

import jakarta.validation.Valid;

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

	@Autowired
	private UpdatePawnFormValidator updatePawnFormValidator;

	@Autowired
	private SearchFormValidator searchFormValidator;

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
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		model.addObject(ConstantsViews.PAWNFORM, new Pawn());
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
	public ModelAndView resultPawns(Pawn pawn) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		if (Util.isEmpty(pawn.getNumpawn())) {
			model.setViewName(VIEWSEARCHPAWN);
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
			model.addObject(ConstantsViews.PAWNFORM, new Pawn());
		} else {
			model.setViewName("admin/pawns/searchpawns/resultpawn");
			model.addObject(ConstantsViews.PAWNS, pawnService.searchByNumpawn(pawn));
			model.addObject(ConstantsViews.PAWNFORM, pawn);
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
	public ModelAndView updatePawn(@ModelAttribute(ConstantsViews.PAWNFORM) Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		Long idpawn = pawn.getId();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		if (idpawn == null) {
			model.setViewName("admin/pawns/searchpawns/errorupdatepawn");
		} else {
			NewPawn p = pawnService.findByIdpawn(idpawn);
			model.setViewName(VIEWUPDATEPAWN);
			model.addObject(ConstantsViews.PAWNFORM, p);
			model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
			model.addObject(Constants.NATIONS, nationservice.getNations());
			model.addObject(Constants.TRACKS, trackservice.getTracks());
		}
		return model;
	}

	@GetMapping("/searchpawn{id}")
	public ModelAndView searchPawn(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		NewPawn p = pawnService.findByIdpawn(id);
		model.setViewName(VIEWUPDATEPAWN);
		model.addObject(ConstantsViews.PAWNFORM, p);
		model.addObject(Constants.NATIONS, nationservice.getNations());
		model.addObject(Constants.TRACKS, trackservice.getTracks());
		model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
		return model;
	}

	@PostMapping("/resultrenovations")
	public ModelAndView resultrenovations(@ModelAttribute(ConstantsViews.PAWNFORM) Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		Long idpawn = pawn.getId();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
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
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
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
	public ModelAndView savePawn(@ModelAttribute(ConstantsViews.PAWNFORM) NewPawn pawn, BindingResult result) {
		ModelAndView model;
		updatePawnFormValidator.validate(pawn, result);
		if (result.hasErrors()) {
			model = new ModelAndView();
			model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
			model.setViewName(VIEWUPDATEPAWN);
			model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
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
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		model.addObject(ConstantsViews.PAWNFORM, new Pawn());
		return model;
	}

	@PostMapping("/resultRenovationsPawns")
	public ModelAndView resultRenovationsPawns(@Valid Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		if (result.hasErrors()) {
			model.setViewName(VIEWSEARCHPAWN);
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
			model.addObject(ConstantsViews.PAWNFORM, new Pawn());
		} else {
			model.setViewName("admin/pawns/searchrenovations/resultpawn");
			model.addObject(ConstantsViews.PAWNS, pawnService.searchByNumpawn(pawn));
		}
		return model;
	}

	@GetMapping("/searchquarterpawns")
	public ModelAndView searchquarterpawns() {
		ModelAndView model = new ModelAndView("admin/pawns/quarters/searchquarter");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.FORMSEARCH, new SearchForm());
		return model;
	}

	@PostMapping("/quarterpawns")
	public ModelAndView quarterpawns(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchForm search,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		searchFormValidator.validate(search, result);
		if (result.hasErrors()) {
			model.setViewName("admin/pawns/quarters/searchquarter");
		} else {
			Quarter quarter = pawnService.searchGramsByDates(search.getDatefrom(), search.getDateuntil());
			model.setViewName("admin/pawns/quarters/quarter");
			model.addObject("quarter", quarter);
		}
		model.addObject(ConstantsViews.FORMSEARCH, search);
		return model;
	}

	@GetMapping("/searchcommissions")
	public ModelAndView searchCommissions() {
		ModelAndView model = new ModelAndView("admin/pawns/commissions/searchcommissions");
		modelComun(model);
		return model;
	}

	@PostMapping("/commissions")
	public ModelAndView commisions(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchForm search, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		searchFormValidator.validate(search, result);
		if (result.hasErrors()) {
			model.addObject(ConstantsViews.FORMSEARCH, search);
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
	public ModelAndView searchPawnsoutofdate(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchForm search) {
		ModelAndView model = new ModelAndView("admin/pawns/outofdate/resultpawn");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.PAWNS, pawnService.pawnsOutofdate(search.getPlace()));
		model.addObject(ConstantsViews.PAWNFORM, new Pawn());
		return model;
	}

	@GetMapping("/investedmoney")
	public ModelAndView searchInvestedMoney() {
		ModelAndView model = new ModelAndView("admin/pawns/investedmoney/searchinvestedmoney");
		model.addObject(ConstantsViews.PAWNFORM, new Pawn());
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		return model;
	}

	@PostMapping("/resultinvestedmoney")
	public ModelAndView resultInvestedMoney(@ModelAttribute(ConstantsViews.PAWNFORM) Pawn pawn) {
		ModelAndView model = new ModelAndView("admin/pawns/investedmoney/investedmoney");
		pawnService.sumPawnsActiveByPlace(pawn.getPlace());
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject("investedmoney", pawnService.sumPawnsActiveByPlace(pawn.getPlace()));
		return model;
	}

	private void modelComun(ModelAndView model) {
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject(ConstantsViews.FORMSEARCH, new SearchForm());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
	}
}
