package com.atmj.jsboot.employee.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.dbaccess.entities.MetalEntity;
import com.atmj.jsboot.dbaccess.entities.ObjectShopEntity;
import com.atmj.jsboot.employee.validators.ShoppingsValidator;
import com.atmj.jsboot.services.metal.MetalService;
import com.atmj.jsboot.services.nations.NationService;
import com.atmj.jsboot.services.shoppings.Shopping;
import com.atmj.jsboot.services.shoppings.ShoppingService;
import com.atmj.jsboot.services.tracks.TrackService;
import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.string.Util;

/**
 * The Class ShoppingsController.
 */
@Controller
public class ShoppingsController {

	@Autowired
	private MetalService materialService;

	@Autowired
	private NationService nationservice;

	/** The shopping service. */
	@Autowired
	private ShoppingService shoppingService;

	@Autowired
	private TrackService trackservice;

	@Autowired
	private ShoppingsValidator validator;

	private static final String VIEWNEWSHOPPING = "employee/newshop/newshop";
	private static final String VIEWSEARCHCLIENT = "employee/newshop/searchclient";
	private static final String FORMSHOP = "shopform";

	@GetMapping("/employee/searchclientshop")
	public ModelAndView searchClient() {
		ModelAndView model = new ModelAndView(VIEWSEARCHCLIENT);
		model.addObject(FORMSHOP, new Shopping());
		return model;
	}

	@PostMapping("/employee/newshop")
	public ModelAndView newshop(@ModelAttribute(FORMSHOP) Shopping pawn, BindingResult errors) {
		String dni = pawn.getNif();
		ModelAndView model = new ModelAndView();
		if (dni != null && dni.length() > 12) {
			errors.rejectValue(ConstantsViews.NIF, "niftoolong");
			model.setViewName(VIEWSEARCHCLIENT);
		} else if (dni.isEmpty() || !Util.isNifNie(dni)) {
			errors.rejectValue(ConstantsViews.NIF, "nifnotvalid");
			model.setViewName(VIEWSEARCHCLIENT);
		} else {
			Shopping client = shoppingService.searchClient(Util.refactorNIF(pawn.getNif()));
			List<MetalEntity> materials = materialService.getAllMetalsActive();
			Iterator<MetalEntity> imaterials = materials.iterator();
			List<ObjectShopEntity> lop = new ArrayList<>();
			while (imaterials.hasNext()) {
				ObjectShopEntity op = new ObjectShopEntity();
				op.setMetal(imaterials.next());
				lop.add(op);
			}
			pawn.setAddress(client.getAddress());
			pawn.setNif(client.getNif());
			pawn.setSurname(client.getSurname());
			pawn.setTown(client.getTown());
			pawn.setName(client.getName());
			pawn.setNation(client.getNation());
			pawn.setTrack(client.getTrack());
			pawn.setObjects(lop);
			model.setViewName(VIEWNEWSHOPPING);
			model.addObject(Constants.TRACKS, trackservice.getTracks());
			model.addObject(Constants.NATIONS, nationservice.getNations());
		}
		model.addObject("shoppingForm", pawn);
		return model;
	}

	/**
	 * Save shopping.
	 *
	 * @param shoppingForm the shopping form
	 * @param result       the result
	 * @return the model and view
	 */
	@PostMapping("/employee/saveShopping")
	public ModelAndView saveShopping(Shopping shoppingForm, Errors result) {
		ModelAndView model = new ModelAndView();
		validator.validate(shoppingForm, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWNEWSHOPPING);
			List<ObjectShopEntity> los = shoppingForm.getObjects();
			List<ObjectShopEntity> nlos = new ArrayList<>();
			Iterator<ObjectShopEntity> ilos = los.iterator();
			while (ilos.hasNext()) {
				ObjectShopEntity os = ilos.next();
				os.setMetal(materialService.findById(os.getMetal().getIdmetal()));
				nlos.add(os);
			}
			shoppingForm.setObjects(nlos);
			model.addObject("shoppingForm", shoppingForm);
			model.addObject(Constants.TRACKS, trackservice.getTracks());
			model.addObject(Constants.NATIONS, nationservice.getNations());
		} else {
			Calendar c = Calendar.getInstance();
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			Long numshop = shoppingForm.getNumshop();
			shoppingForm.setUser(user);
			boolean repeat = shoppingService.isRepeatNumber(String.valueOf(numshop), user, c.get(Calendar.YEAR));
			if (repeat) {
				model.setViewName(VIEWNEWSHOPPING);
				List<ObjectShopEntity> los = shoppingForm.getObjects();
				List<ObjectShopEntity> newlos = new ArrayList<>();
				Iterator<ObjectShopEntity> ilos = los.iterator();
				ObjectShopEntity os;
				while (ilos.hasNext()) {
					os = ilos.next();
					os.setMetal(materialService.findById(os.getMetal().getIdmetal()));
					newlos.add(os);
				}
				shoppingForm.setObjects(newlos);
				model.addObject("shoppingForm", shoppingForm);
				result.rejectValue(ConstantsViews.NUMSHOP, "numrepeated");
				model.addObject(Constants.TRACKS, trackservice.getTracks());
				model.addObject(Constants.NATIONS, nationservice.getNations());
			} else {
				model.addObject(ConstantsViews.DAILY, shoppingService.save(shoppingForm));
				model.setViewName(ConstantsViews.VIEWDAILYARROW);
			}
		}
		return model;
	}
}
