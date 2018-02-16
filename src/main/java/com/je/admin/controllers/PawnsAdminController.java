package com.je.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

	private static final String VIEWSEARCHPAWN = "searchpawn";
	private static final String VIEWUPDATEPAWN = "updatepawn";

	/**
	 * Search pawns.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/searchPawns")
	public ModelAndView searchPawns() {
		ModelAndView model = new ModelAndView(VIEWSEARCHPAWN);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlaces());
		model.addObject(ConstantsJsp.PAWNFORM, new Pawn());
		return model;
	}

	/**
	 * Result pawns.
	 *
	 * @param pawn
	 *            the pawn
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/resultPawns")
	public ModelAndView resultPawns(@ModelAttribute(ConstantsJsp.PAWNFORM) Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		pawnFormValidator.validate(pawn, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWSEARCHPAWN);
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlaces());
			model.addObject(ConstantsJsp.PAWNFORM, new Pawn());
		} else {
			model.setViewName("resultpawns");
			model.addObject(ConstantsJsp.PAWNS, pawnService.searchByNumpawn(pawn));
		}
		return model;
	}

	/**
	 * Update pawn.
	 *
	 * @param pawn
	 *            the pawn
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/updatepawn")
	public ModelAndView updatePawn(@ModelAttribute(ConstantsJsp.PAWNFORM) Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		Long idpawn = pawn.getId();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		if (idpawn == null) {
			model.setViewName("errorupdatepawn");
		} else {
			NewPawn p = pawnService.findByIdpawn(idpawn);
			model.setViewName(VIEWUPDATEPAWN);
			model.addObject(ConstantsJsp.PAWNFORM, p);
			model.addObject(ConstantsJsp.MATERIALS, materialService.getAllMetals());
			model.addObject("nations", nationservice.getNations());
			model.addObject(Constants.TRACKS, trackservice.getTracks());
		}
		return model;
	}

	@RequestMapping(value = "/searchpawn{id}")
	public ModelAndView searchPawn(@PathVariable long id) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		NewPawn p = pawnService.findByIdpawn(id);
		model.setViewName(VIEWUPDATEPAWN);
		model.addObject(ConstantsJsp.PAWNFORM, p);
		model.addObject(ConstantsJsp.NATIONS, nationservice.getNations());
		model.addObject(Constants.TRACKS, trackservice.getTracks());
		return model;
	}

	@RequestMapping(value = "/resultrenovations")
	public ModelAndView resultrenovations(@ModelAttribute(ConstantsJsp.PAWNFORM) Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		Long idpawn = pawn.getId();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		if (idpawn == null) {
			model.setViewName("errorupdatepawn");
		} else {
			List<RenovationDates> renovations = pawnService.searchRenovations(idpawn);
			model.setViewName("resultrenovations");
			model.addObject("renovations", renovations);
		}
		return model;
	}

	@RequestMapping(value = "/renovations{id}")
	public ModelAndView renovations(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		List<RenovationDates> renovations = pawnService.searchRenovations(id);
		model.setViewName("resultrenovations");
		model.addObject("renovations", renovations);
		return model;
	}

	/**
	 * Save pawn.
	 *
	 * @param pawn
	 *            the pawn
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/savePawn")
	public ModelAndView savePawn(@ModelAttribute(ConstantsJsp.PAWNFORM) NewPawn pawn, BindingResult result) {
		ModelAndView model;
		updatePawnFormValidator.validate(pawn, result);
		if (result.hasErrors()) {
			model = new ModelAndView();
			model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
			model.setViewName(VIEWUPDATEPAWN);
			model.addObject("pawn", pawn);
		} else {
			model = searchPawns();
			pawn.setNif(Util.refactorNIF(pawn.getNif()));
			pawnService.update(pawn);
		}
		return model;
	}

	@RequestMapping(value = "/searchrenovations")
	public ModelAndView searchrenovations() {
		ModelAndView model = new ModelAndView("searchrenovations");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlaces());
		model.addObject(ConstantsJsp.PAWNFORM, new Pawn());
		return model;
	}

	@RequestMapping(value = "/resultRenovationsPawns")
	public ModelAndView resultRenovationsPawns(@ModelAttribute(ConstantsJsp.PAWNFORM) Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		pawnFormValidator.validate(pawn, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWSEARCHPAWN);
			model.addObject(ConstantsJsp.PLACES, placeService.getAllPlaces());
			model.addObject(ConstantsJsp.PAWNFORM, new Pawn());
		} else {
			model.setViewName("resultpawnsrenovations");
			model.addObject(ConstantsJsp.PAWNS, pawnService.searchByNumpawn(pawn));
		}
		return model;
	}

	@RequestMapping(value = "/searchquarterpawns")
	public ModelAndView searchquarterpawns() {
		ModelAndView model = new ModelAndView("searchquarterpawns");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchForm());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlaces());
		return model;
	}

	@RequestMapping(value = "/quarterpawns")
	public ModelAndView quarterpawns(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchForm search, BindingResult result) {
		ModelAndView model = new ModelAndView();
		adminSearchFormValidator.validate(search, result);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		if (result.hasErrors()) {
			model.setViewName("searchquarterpawns");
		} else {
			Quarter quarter = pawnService.searchGramsByDates(search.getDatefrom(), search.getDateuntil(),
					search.getPlace());
			model.setViewName("quarterpawns");
			model.addObject("quarter", quarter);
			search.setPlace(placeService.getPlace(search.getPlace().getIdplace()));
		}
		model.addObject(ConstantsJsp.FORMSEARCH, search);
		return model;
	}

	@RequestMapping(value = "/searchcommissions")
	public ModelAndView searchCommissions() {
		ModelAndView model = new ModelAndView("searchcommissions");
		modelComun(model);
		return model;
	}

	@RequestMapping(value = "/commissions")
	public ModelAndView commisions(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchForm search, BindingResult result) {
		ModelAndView model = new ModelAndView();
		adminSearchFormValidator.validate(search, result);
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		if (result.hasErrors()) {
			model.addObject(ConstantsJsp.FORMSEARCH, search);
			model.setViewName("searchcommissions");
		} else {
			model.addObject("commissions",
					pawnService.getCommissions(search.getDatefrom(), search.getDateuntil(), search.getPlace()));
			model.setViewName("commissions");
		}
		return model;
	}

	@RequestMapping(value = "/outofdate")
	public ModelAndView outofdate() {
		ModelAndView model = new ModelAndView("searchpawnsoutofdate");
		modelComun(model);
		return model;
	}

	@RequestMapping(value = "/searchpawnsoutofdate")
	public ModelAndView searchPawnsoutofdate(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchForm search) {
		ModelAndView model = new ModelAndView("resultpawnsoutofdate");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.PAWNS, pawnService.pawnsOutofdate(search.getPlace()));
		model.addObject(ConstantsJsp.PAWNFORM, new Pawn());
		return model;
	}

	@RequestMapping(value = "/investedmoney")
	public ModelAndView searchInvestedMoney() {
		ModelAndView model = new ModelAndView("searchinvestedmoney");
		model.addObject(ConstantsJsp.PAWNFORM, new Pawn());
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlaces());
		return model;
	}

	@RequestMapping(value = "/resultinvestedmoney")
	public ModelAndView resultInvestedMoney(@ModelAttribute(ConstantsJsp.PAWNFORM) Pawn pawn) {
		ModelAndView model = new ModelAndView("investedmoney");
		pawnService.sumPawnsActiveByPlace(pawn.getPlace());
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject("investedmoney", pawnService.sumPawnsActiveByPlace(pawn.getPlace()));
		return model;
	}

	private void modelComun(ModelAndView model) {
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchForm());
		model.addObject(ConstantsJsp.PLACES, placeService.getAllPlaces());
	}
}
