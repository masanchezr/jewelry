package com.atmj.jsboot.workshop.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.admin.forms.AdminForm;
import com.atmj.jsboot.admin.validators.SelectCategoryValidator;
import com.atmj.jsboot.dbaccess.entities.JewelEntity;
import com.atmj.jsboot.forms.Jewel;
import com.atmj.jsboot.services.categories.CategoriesService;
import com.atmj.jsboot.services.jewels.JewelService;
import com.atmj.jsboot.services.metal.MetalService;
import com.atmj.jsboot.services.places.PlaceService;
import com.atmj.jsboot.utils.constants.ConstantsViews;

@Controller
@SessionAttributes({ "placeselected" })
public class JewelController {
	/** The categories service. */
	@Autowired
	private CategoriesService categoriesService;

	/** The jewel service. */
	@Autowired
	private JewelService jewelService;

	/** The material service. */
	@Autowired
	private MetalService materialService;

	/** The place service. */
	@Autowired
	private PlaceService placeService;

	@Autowired
	private SelectCategoryValidator validator;

	@Autowired
	private ModelMapper mapper;
	private static final String FORMJEWEL = "jewelForm";
	private static final String VIEWNEWJEWEL = "workshop/jewels/newjewel/newJewel";

	/**
	 * New jewel.
	 *
	 * @return the model and view para crear nuevas joyas
	 */
	@GetMapping("/work/newjewel")
	public ModelAndView newJewelEntity() {
		ModelAndView model = new ModelAndView(VIEWNEWJEWEL);
		JewelEntity jewel = new JewelEntity();
		jewel.setActive(true);
		model.addObject(FORMJEWEL, jewel);
		model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	/**
	 * Adds the jewels.
	 *
	 * @param jewelForm the jewel form
	 * @param result    the result
	 * @return the string
	 */
	@PostMapping("/work/saveJewel")
	public ModelAndView addJewelEntity(@ModelAttribute(FORMJEWEL) Jewel jewelForm, BindingResult result, Model m) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		validator.validate(jewelForm, result);
		if (result.hasErrors()) {
			model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
			model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
			model.setViewName(VIEWNEWJEWEL);
		} else {
			// primero miro a ver si existe ya esa joya,
			JewelEntity jewelf = mapper.map(jewelForm, JewelEntity.class);
			JewelEntity jewel = jewelService.searchByReferenceCategoryMetalPlace(jewelf);
			if (jewel != null && !jewel.getIdjewel().equals(jewelForm.getIdjewel())) {
				model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
				model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
				model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
				model.setViewName(VIEWNEWJEWEL);
				result.rejectValue(ConstantsViews.REFERENCE, "selectotherreference");
			} else {
				jewelf.setPlace(placeService.getPlace(jewelForm.getPlace().getIdplace()));
				jewelf = jewelService.addObject(jewelf);
				jewelf.setCategory(categoriesService.findById(jewelf.getCategory().getIdcategory()));
				model.setViewName("workshop/jewels/newjewel/successjewel");
				model.addObject("jewel", jewelf);
				m.addAttribute("placeselected", jewelf.getPlace());
			}
		}
		return model;
	}
}