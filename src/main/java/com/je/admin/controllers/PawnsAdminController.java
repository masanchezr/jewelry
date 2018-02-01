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
import com.je.services.material.MetalService;
import com.je.services.nations.NationService;
import com.je.services.pawns.NewPawn;
import com.je.services.pawns.Pawn;
import com.je.services.pawns.PawnService;
import com.je.services.pawns.Quarter;
import com.je.services.pawns.RenovationDates;
import com.je.services.places.PlaceService;
import com.je.services.tracks.TrackService;
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

	/**
	 * Search pawns.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/searchPawns")
	public ModelAndView searchPawns() {
		ModelAndView model = new ModelAndView("searchpawn");
		model.addObject("adminForm", new AdminForm());
		model.addObject("places", placeService.getAllPlaces());
		model.addObject("pawnForm", new Pawn());
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
	public ModelAndView resultPawns(@ModelAttribute("pawnForm") Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject("adminForm", new AdminForm());
		pawnFormValidator.validate(pawn, result);
		if (result.hasErrors()) {
			model.setViewName("searchpawn");
			model.addObject("places", placeService.getAllPlaces());
			model.addObject("pawnForm", new Pawn());
		} else {
			model.setViewName("resultpawns");
			model.addObject("pawns", pawnService.searchByNumpawn(pawn));
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
	public ModelAndView updatePawn(@ModelAttribute("pawnForm") Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		Long idpawn = pawn.getId();
		model.addObject("adminForm", new AdminForm());
		if (idpawn == null) {
			model.setViewName("errorupdatepawn");
		} else {
			NewPawn p = pawnService.findByIdpawn(idpawn);
			model.setViewName("updatepawn");
			model.addObject("pawnForm", p);
			model.addObject("materials", materialService.getAllMetals());
			model.addObject("nations", nationservice.getNations());
			model.addObject("tracks", trackservice.getTracks());
		}
		return model;
	}

	@RequestMapping(value = "/searchpawn{id}")
	public ModelAndView searchPawn(@PathVariable long id) {
		ModelAndView model = new ModelAndView();
		model.addObject("adminForm", new AdminForm());
		NewPawn p = pawnService.findByIdpawn(id);
		model.setViewName("updatepawn");
		model.addObject("pawnForm", p);
		model.addObject("nations", nationservice.getNations());
		model.addObject("tracks", trackservice.getTracks());
		return model;
	}

	@RequestMapping(value = "/resultrenovations")
	public ModelAndView resultrenovations(@ModelAttribute("pawnForm") Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		Long idpawn = pawn.getId();
		model.addObject("adminForm", new AdminForm());
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
		model.addObject("adminForm", new AdminForm());
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
	public ModelAndView savePawn(@ModelAttribute("pawnForm") NewPawn pawn, BindingResult result) {
		ModelAndView model;
		updatePawnFormValidator.validate(pawn, result);
		if (result.hasErrors()) {
			model = new ModelAndView();
			model.addObject("adminForm", new AdminForm());
			model.setViewName("updatepawn");
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
		model.addObject("adminForm", new AdminForm());
		model.addObject("places", placeService.getAllPlaces());
		model.addObject("pawnForm", new Pawn());
		return model;
	}

	@RequestMapping(value = "/resultRenovationsPawns")
	public ModelAndView resultRenovationsPawns(@ModelAttribute("pawnForm") Pawn pawn, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.addObject("adminForm", new AdminForm());
		pawnFormValidator.validate(pawn, result);
		if (result.hasErrors()) {
			model.setViewName("searchpawn");
			model.addObject("places", placeService.getAllPlaces());
			model.addObject("pawnForm", new Pawn());
		} else {
			model.setViewName("resultpawnsrenovations");
			model.addObject("pawns", pawnService.searchByNumpawn(pawn));
		}
		return model;
	}

	@RequestMapping(value = "/searchquarterpawns")
	public ModelAndView searchquarterpawns() {
		ModelAndView model = new ModelAndView("searchquarterpawns");
		model.addObject("adminForm", new AdminForm());
		model.addObject("searchForm", new SearchForm());
		model.addObject("places", placeService.getAllPlaces());
		return model;
	}

	@RequestMapping(value = "/quarterpawns")
	public ModelAndView quarterpawns(@ModelAttribute("searchForm") SearchForm search, BindingResult result) {
		ModelAndView model = new ModelAndView();
		adminSearchFormValidator.validate(search, result);
		model.addObject("adminForm", new AdminForm());
		if (result.hasErrors()) {
			model.setViewName("searchquarterpawns");
		} else {
			Quarter quarter = pawnService.searchGramsByDates(search.getDatefrom(), search.getDateuntil(),
					search.getPlace());
			model.setViewName("quarterpawns");
			model.addObject("quarter", quarter);
			search.setPlace(placeService.getPlace(search.getPlace().getIdplace()));
		}
		model.addObject("searchForm", search);
		return model;
	}

	@RequestMapping(value = "/searchcommissions")
	public ModelAndView searchCommissions() {
		ModelAndView model = new ModelAndView("searchcommissions");
		modelComun(model);
		return model;
	}

	@RequestMapping(value = "/commissions")
	public ModelAndView commisions(@ModelAttribute("searchForm") SearchForm search, BindingResult result) {
		ModelAndView model = new ModelAndView();
		adminSearchFormValidator.validate(search, result);
		model.addObject("adminForm", new AdminForm());
		if (result.hasErrors()) {
			model.addObject("searchForm", search);
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
	public ModelAndView searchPawnsoutofdate(@ModelAttribute("searchForm") SearchForm search) {
		ModelAndView model = new ModelAndView("resultpawnsoutofdate");
		model.addObject("adminForm", new AdminForm());
		model.addObject("pawns", pawnService.pawnsOutofdate(search.getPlace()));
		model.addObject("pawnForm", new Pawn());
		return model;
	}

	@RequestMapping(value = "/investedmoney")
	public ModelAndView searchInvestedMoney() {
		ModelAndView model = new ModelAndView("searchinvestedmoney");
		model.addObject("pawnForm", new Pawn());
		model.addObject("adminForm", new AdminForm());
		model.addObject("places", placeService.getAllPlaces());
		return model;
	}

	@RequestMapping(value = "/resultinvestedmoney")
	public ModelAndView resultInvestedMoney(@ModelAttribute("pawnForm") Pawn pawn) {
		ModelAndView model = new ModelAndView("investedmoney");
		pawnService.sumPawnsActiveByPlace(pawn.getPlace());
		model.addObject("adminForm", new AdminForm());
		model.addObject("investedmoney", pawnService.sumPawnsActiveByPlace(pawn.getPlace()));
		return model;
	}

	private void modelComun(ModelAndView model) {
		model.addObject("adminForm", new AdminForm());
		model.addObject("searchForm", new SearchForm());
		model.addObject("places", placeService.getAllPlaces());
	}
}
