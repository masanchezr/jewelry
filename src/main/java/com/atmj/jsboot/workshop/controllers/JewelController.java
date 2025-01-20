package com.atmj.jsboot.workshop.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.jsboot.admin.validators.SelectCategoryValidator;
import com.atmj.jsboot.dbaccess.entities.JewelEntity;
import com.atmj.jsboot.forms.Jewel;
import com.atmj.jsboot.forms.SearchForm;
import com.atmj.jsboot.services.categories.CategoriesService;
import com.atmj.jsboot.services.jewels.JewelService;
import com.atmj.jsboot.services.metal.MetalService;
import com.atmj.jsboot.services.places.PlaceService;
import com.atmj.jsboot.utils.constants.ConstantsViews;
import com.atmj.jsboot.validators.SearchFormValidator;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes({ ConstantsViews.FORMSEARCH, "placeselected" })
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
	private SearchFormValidator searchFormValidator;

	@Autowired
	private SelectCategoryValidator categoryvalidator;

	@Autowired
	private ModelMapper mapper;

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(JewelController.class);
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
		categoryvalidator.validate(jewelForm, result);
		if (result.hasErrors()) {
			model.addObject(ConstantsViews.CATEGORIES, categoriesService.getAllCategoriesOrderByName());
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
			model.addObject(ConstantsViews.MATERIALS, materialService.getAllMetals());
			model.setViewName(VIEWNEWJEWEL);
		} else {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			jewelForm.setUser(user.getUsername());
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

	@GetMapping("/work/searchjewels")
	public ModelAndView searchJewels() {
		ModelAndView model = new ModelAndView("workshop/jewels/print/search.html");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchForm());
		model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		return model;
	}

	@PostMapping("/work/printjewels")
	public ModelAndView printJewels(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchForm form, BindingResult result,
			Model m) {
		String sfrom = form.getDatefrom();
		String suntil = form.getDateuntil();
		ModelAndView model = new ModelAndView();
		searchFormValidator.validate(form, result);
		if (result.hasErrors()) {
			model.setViewName("workshop/jewels/print/search.html");
			model.addObject(ConstantsViews.FORMSEARCH, form);
			model.addObject(ConstantsViews.PLACES, placeService.getAllPlacesActive());
		} else {
			model.setViewName("workshop/jewels/print/resultsearch.html");
			model.addAllObjects(jewelService.searchByPlaceAndDates(form.getPlace(), sfrom, suntil));
			m.addAttribute(ConstantsViews.FORMSEARCH, form);
		}
		return model;
	}

	@PostMapping("/work/printpdf")
	public void printpdf(
			@Validated(SearchFormValidator.class) @ModelAttribute(ConstantsViews.FORMSEARCH) SearchForm form,
			HttpServletResponse response) {
		File file = jewelService.generatePdf(form.getPlace(), form.getDatefrom(), form.getDateuntil());
		try (InputStream inputStream = new FileInputStream(file)) {
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment; filename=".concat(file.getName()));
			IOUtils.copy(inputStream, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			logger.error(java.util.logging.Level.SEVERE.getName());
		}
	}
}