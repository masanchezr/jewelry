package com.je.admin.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.admin.forms.ShoppingSearch;
import com.je.admin.validators.NewShoppingFormValidator;
import com.je.admin.validators.ShoppingFormValidator;
import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.entities.ObjectShopEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.forms.SearchForm;
import com.je.services.material.MetalService;
import com.je.services.nations.NationService;
import com.je.services.places.PlaceService;
import com.je.services.shoppings.QuarterMetal;
import com.je.services.shoppings.Shopping;
import com.je.services.shoppings.ShoppingService;
import com.je.services.tracks.TrackService;
import com.je.utils.constants.Constants;
import com.je.utils.string.Util;
import com.je.validators.SearchFormValidator;

/**
 * The Class ShoppingsAdminController.
 */
@Controller
public class ShoppingsAdminController {

	/** The shopping service. */
	@Autowired
	private ShoppingService shoppingService;

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

	/** The shopping form validator. */
	@Autowired
	private ShoppingFormValidator shoppingFormValidator;

	@Autowired
	private NewShoppingFormValidator newshoppingFormValidator;

	@Autowired
	private SearchFormValidator adminSearchValidator;

	/**
	 * Search shoppings.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/searchShoppings")
	public ModelAndView searchShoppings() {
		ModelAndView model = new ModelAndView("searchshoppings");
		model.addObject("shoppingForm", new ShoppingSearch());
		model.addObject("adminForm", new AdminForm());
		model.addObject("places", placeService.getAllPlaces());
		return model;
	}

	/**
	 * Result shoppings.
	 *
	 * @param shopping
	 *            the shopping
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/resultShoppings")
	public ModelAndView resultShoppings(@ModelAttribute("shoppingForm") ShoppingSearch shopping, BindingResult result) {
		ModelAndView model = new ModelAndView();
		shoppingFormValidator.validate(shopping, result);
		if (result.hasErrors()) {
			model.addObject("shopping", shopping);
			model.addObject("places", placeService.getAllPlaces());
			model.setViewName("searchshoppings");
		} else {
			List<Shopping> shoppings = shoppingService.searchShoppings(shopping.getDatefrom(), shopping.getDateuntil(),
					shopping.getPlace(), shopping.getNumshop());
			model.addObject("shoppings", shoppings);
			model.addObject("shoppingForm", new Shopping());
			model.setViewName("resultshoppings");
		}
		model.addObject("adminForm", new AdminForm());
		return model;
	}

	/**
	 * hay que codificar este metodo para devolver la compra que se va a modificar.
	 *
	 * @param shopping
	 *            the shopping
	 * @return the model and view
	 */
	@RequestMapping(value = "/updateShoppings")
	public ModelAndView updateShoppings(@ModelAttribute("shoppingForm") Shopping shopping) {
		ModelAndView model = new ModelAndView();
		Long idshop = shopping.getId();
		if (idshop == null) {
			model.addObject("shoppingForm", new ShoppingSearch());
			model.addObject("places", placeService.getAllPlaces());
			model.setViewName("searchshoppings");
		} else {
			shopping = shoppingService.findShopByPK(idshop);
			model.addObject("shoppingForm", shopping);
			model.setViewName("updateshopping");
			model.addObject("materials", materialService.getAllMetals());
		}
		model.addObject("adminForm", new AdminForm());
		return model;
	}

	@RequestMapping(value = "/updateShopping{id}")
	public ModelAndView updateShopping(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("updateshopping");
		Shopping shopping = shoppingService.findShopByPK(id);
		model.addObject("shoppingForm", shopping);
		model.addObject("adminForm", new AdminForm());
		model.addObject("materials", materialService.getAllMetals());
		return model;
	}

	/**
	 * Save shopping.
	 *
	 * @param shoppingForm
	 *            the shopping form
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/saveShopping")
	public ModelAndView saveShopping(@ModelAttribute("shoppingForm") Shopping shoppingForm) {
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		shoppingForm.setUser(user);
		shoppingService.update(shoppingForm);
		return searchShoppings();
	}

	/**
	 * Save shopping.
	 *
	 * @param shoppingForm
	 *            the shopping form
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping(value = "/saveshop")
	public ModelAndView saveShop(@ModelAttribute("shopform") Shopping shoppingForm, BindingResult result) {
		ModelAndView model = new ModelAndView();
		newshoppingFormValidator.validate(shoppingForm, result);
		if (result.hasErrors()) {
			List<MetalEntity> materials = materialService.getAllMetalsActive();
			Iterator<MetalEntity> imaterials = materials.iterator();
			List<ObjectShopEntity> lop = new ArrayList<ObjectShopEntity>();
			while (imaterials.hasNext()) {
				ObjectShopEntity op = new ObjectShopEntity();
				op.setMetal(imaterials.next());
				lop.add(op);
			}
			shoppingForm.setObjects(lop);
			model = new ModelAndView();
			model.addObject("shopform", shoppingForm);
			model.addObject("adminForm", new AdminForm());
			model.addObject("tracks", trackservice.getTracks());
			model.addObject("nations", nationservice.getNations());
			model.setViewName("newshop");
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			shoppingForm.setUser(user);
			shoppingService.save(shoppingForm);
			model = searchClient();
		}
		return model;
	}

	@RequestMapping(value = "/searchquarter")
	public ModelAndView searchquarter() {
		ModelAndView model = new ModelAndView("searchquarter");
		model.addObject("adminForm", new AdminForm());
		model.addObject("shoppingForm", new SearchForm());
		model.addObject("places", placeService.getAllPlaces());
		return model;
	}

	@RequestMapping(value = "/searchquartermaterial")
	public ModelAndView searchQuarterMetal() {
		ModelAndView model = new ModelAndView("searchquartermaterial");
		model.addObject("adminForm", new AdminForm());
		model.addObject("shoppingForm", new SearchForm());
		model.addObject("places", placeService.getAllPlaces());
		return model;
	}

	@RequestMapping(value = "/quartermaterial")
	public ModelAndView quarterMetal(@ModelAttribute("shoppingForm") SearchForm shopping, BindingResult result) {
		ModelAndView model = new ModelAndView();
		adminSearchValidator.validate(shopping, result);
		if (result.hasErrors()) {
			model.setViewName("searchquartermaterial");
			model.addObject("places", placeService.getAllPlaces());
		} else {
			List<QuarterMetal> quarters = shoppingService.searchGramsByMetal(shopping.getDatefrom(),
					shopping.getDateuntil(), shopping.getPlace());
			model.addObject("quarters", quarters);
			model.setViewName("quartermaterial");
			shopping.setPlace(placeService.getPlace(shopping.getPlace().getIdplace()));
		}
		model.addObject("shoppingForm", shopping);
		model.addObject("adminForm", new AdminForm());
		return model;
	}

	@RequestMapping(value = "/searchgramsnull")
	public ModelAndView searchGramsNull() {
		ModelAndView model = new ModelAndView("searchgramsnull");
		model.addObject("adminForm", new AdminForm());
		model.addObject("adminSearchForm", new SearchForm());
		model.addObject("places", placeService.getAllPlaces());
		return model;
	}

	@RequestMapping(value = "/resultgramsnull")
	public ModelAndView resultGramsNull(@ModelAttribute("adminForm") SearchForm form, BindingResult result) {
		ModelAndView model = new ModelAndView();
		adminSearchValidator.validate(form, result);
		model.addObject("adminForm", new AdminForm());
		if (result.hasErrors()) {
			model.setViewName("searchgramsnull");
			model.addObject("adminSearchForm", form);
			model.addObject("places", placeService.getAllPlaces());
		} else {
			List<Long> numshops = shoppingService.searchGramsNull(form.getDatefrom(), form.getDateuntil(),
					form.getPlace());
			model.addObject("nummissing", numshops);
			model.setViewName("resultmissingshoppings");
		}
		return model;
	}

	@RequestMapping(value = "/tomelloso")
	public ModelAndView searchClient() {
		ModelAndView model = new ModelAndView("searchclientadmin");
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		Shopping pawn = new Shopping();
		pawn.setNumshop(shoppingService.getNextNumber(user));
		pawn.setUser(user);
		model.addObject("shopform", pawn);
		model.addObject("adminForm", new AdminForm());
		return model;
	}

	/**
	 * New pawn.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/newshop")
	public ModelAndView newshop(@ModelAttribute("shopform") Shopping pawn, BindingResult errors) {
		String dni = pawn.getNif();
		ModelAndView model = new ModelAndView();
		if (dni != null && dni.length() > 12) {
			errors.rejectValue("nif", "niftoolong");
			model.setViewName("searchclientadmin");
		} else if (!Util.isNifNie(dni)) {
			errors.rejectValue("nif", "nifnotvalid");
			model.setViewName("searchclientadmin");
		} else {
			Shopping client = shoppingService.searchClient(Util.refactorNIF(pawn.getNif()));
			List<MetalEntity> materials = materialService.getAllMetalsActive();
			Iterator<MetalEntity> imaterials = materials.iterator();
			List<ObjectShopEntity> lop = new ArrayList<ObjectShopEntity>();
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
			model.setViewName("newshop");
			model.addObject("tracks", trackservice.getTracks());
			model.addObject("nations", nationservice.getNations());
		}
		model.addObject("shopform", pawn);
		model.addObject("adminForm", new AdminForm());
		return model;
	}

	@RequestMapping(value = "/exceltomelloso")
	public ModelAndView excelTomelloso() {
		ModelAndView model = new ModelAndView("exceltomelloso");
		model.addObject("adminForm", new AdminForm());
		model.addObject("searchForm", new SearchForm());
		return model;
	}

	@RequestMapping(value = "/downloadexcel")
	public ModelAndView downloadexcel(@ModelAttribute("searchForm") SearchForm form, BindingResult result,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("exceltomelloso");
		adminSearchValidator.validate(form, result);
		if (result.hasErrors()) {
			model.addObject("searchForm", new SearchForm());
		} else {
			PlaceEntity place = new PlaceEntity();
			place.setIdplace(13700L);
			String path = System.getenv(Constants.OPENSHIFT_DATA_DIR);
			XSSFWorkbook myWorkBook = shoppingService.generateExcel(form.getDatefrom(), form.getDateuntil(), place);
			try {
				File file = new File(path.concat("workbook.xlsx"));
				FileOutputStream out = new FileOutputStream(file);
				// write operation workbook using file out object
				myWorkBook.write(out);
				myWorkBook.close();
				out.close();
				InputStream inputStream = new FileInputStream(file);
				response.setContentType("application/force-download");
				response.setHeader("Content-Disposition", "attachment; filename=workbook.xlsx");
				IOUtils.copy(inputStream, response.getOutputStream());
				response.flushBuffer();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		model.addObject("adminForm", new AdminForm());
		return model;
	}
}
