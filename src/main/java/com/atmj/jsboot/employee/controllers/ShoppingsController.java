package com.atmj.jsboot.employee.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.dbaccess.entities.MetalEntity;
import com.atmj.jsboot.dbaccess.entities.ObjectShopEntity;
import com.atmj.jsboot.employee.validators.ShoppingsValidator;
import com.atmj.jsboot.services.metal.MetalService;
import com.atmj.jsboot.services.shoppings.Shopping;
import com.atmj.jsboot.services.shoppings.ShoppingService;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.utils.date.DateUtil;

/**
 * The Class ShoppingsController.
 */
@Controller
public class ShoppingsController {

	@Autowired
	private MetalService materialService;

	/** The shopping service. */
	@Autowired
	private ShoppingService shoppingService;

	@Autowired
	private ShoppingsValidator validator;

	private static final String VIEWNEWSHOPPING = "employee/newshopping";

	/**
	 * New shopping.
	 *
	 * @return the model and view
	 */
	@GetMapping("/employee/newshopping")
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
		model.addObject(ConstantsViews.SHOPPINGFORM, shopping);
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
	public ModelAndView saveShopping(Shopping shoppingForm, BindingResult result) {
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
			model.addObject(ConstantsViews.SHOPPINGFORM, shoppingForm);
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
				model.addObject(ConstantsViews.SHOPPINGFORM, shoppingForm);
				result.rejectValue(ConstantsViews.NUMSHOP, "numrepeated");
			} else {
				model.addObject(ConstantsViews.DAILY, shoppingService.save(shoppingForm));
				model.setViewName(ConstantsViews.VIEWDAILYARROW);
				model.addObject(ConstantsViews.DATEDAILY, DateUtil.getStringDateddMMyyyy(new Date()));
			}
		}
		return model;
	}
}
