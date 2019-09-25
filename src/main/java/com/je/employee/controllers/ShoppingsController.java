package com.je.employee.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.entities.ObjectShopEntity;
import com.je.employee.validators.ShoppingsValidator;
import com.je.services.metal.MetalService;
import com.je.services.pawns.PawnService;
import com.je.services.shoppings.Shopping;
import com.je.services.shoppings.ShoppingService;
import com.je.utils.constants.ConstantsJsp;

/**
 * The Class ShoppingsController.
 */
@Controller
public class ShoppingsController {

	/** The shopping service. */
	@Autowired
	private ShoppingService shoppingService;

	@Autowired
	private PawnService pawnService;

	@Autowired
	private MetalService materialService;

	/** The shoppings validator. */
	@Autowired
	private ShoppingsValidator shoppingsValidator;

	private static final String VIEWNEWSHOPPING = "employee/newshopping";

	/**
	 * New shopping.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/newshopping")
	public ModelAndView newShopping() {
		ModelAndView model = new ModelAndView(VIEWNEWSHOPPING);
		Shopping shopping = new Shopping();
		List<ObjectShopEntity> los = new ArrayList<>();
		List<MetalEntity> materials = materialService.getAllMetalsActive();
		Iterator<MetalEntity> imaterials = materials.iterator();
		while (imaterials.hasNext()) {
			ObjectShopEntity os = new ObjectShopEntity();
			os.setMetal(imaterials.next());
			los.add(os);
		}
		shopping.setObjects(los);
		model.addObject(ConstantsJsp.SHOPPINGFORM, shopping);
		return model;
	}

	/**
	 * Save shopping.
	 *
	 * @param shoppingForm the shopping form
	 * @param result       the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/saveShopping")
	public ModelAndView saveShopping(@ModelAttribute(ConstantsJsp.SHOPPINGFORM) Shopping shoppingForm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		shoppingsValidator.validate(shoppingForm, result);
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
			model.addObject(ConstantsJsp.SHOPPINGFORM, shoppingForm);
		} else {
			Calendar c = Calendar.getInstance();
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			Long numshop = shoppingForm.getNumshop();
			shoppingForm.setUser(user);
			boolean repeat = pawnService.isRepeatNumber(String.valueOf(numshop), user, c.get(Calendar.YEAR));
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
				model.addObject(ConstantsJsp.SHOPPINGFORM, shoppingForm);
				result.rejectValue(ConstantsJsp.NUMSHOP, "numrepited");
			} else {
				model.addObject(ConstantsJsp.DAILY, shoppingService.save(shoppingForm));
				model.setViewName(ConstantsJsp.VIEWDAILYARROW);
				model.addObject(ConstantsJsp.DATEDAILY, new Date());
			}
		}
		return model;
	}
}
