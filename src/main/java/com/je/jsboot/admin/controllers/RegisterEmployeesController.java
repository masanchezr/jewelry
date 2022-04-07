package com.je.jsboot.admin.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.jsboot.admin.forms.AdminForm;
import com.je.jsboot.dbaccess.entities.RegisterEntity;
import com.je.jsboot.forms.SearchForm;
import com.je.jsboot.services.registers.RegisterService;
import com.je.jsboot.utils.constants.Constants;
import com.je.jsboot.utils.constants.ConstantsViews;
import com.je.jsboot.utils.date.DateUtil;
import com.je.jsboot.validators.SearchFormValidator;

@Controller
public class RegisterEmployeesController {

	@Autowired
	private RegisterService registerService;

	@Autowired
	private SearchFormValidator searchFormValidator;

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(RegisterEmployeesController.class);

	@GetMapping("/searchRegisterEmployees")
	public ModelAndView searchRegisterEmployees() {
		ModelAndView model = new ModelAndView("admin/register/search");
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject("searchDateForm", new SearchForm());
		return model;
	}

	@PostMapping("/registeremployees")
	public ModelAndView registeremployees(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchForm form,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		String sfrom = form.getDatefrom();
		String suntil = form.getDateuntil();
		searchFormValidator.validate(form, result);
		if (result.hasErrors()) {
			model.addObject(ConstantsViews.FORMSEARCH, new SearchForm());
			model.setViewName("admin/register/search");
		} else {
			model.addObject("register", registerService.findByDates(sfrom, suntil));
			model.setViewName("admin/register/register");
			model.addObject("datefrom", DateUtil.getStringDateddMMyyyy(DateUtil.getDate(sfrom)));
			model.addObject("dateuntil", DateUtil.getStringDateddMMyyyy(DateUtil.getDate(suntil)));
		}
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		return model;
	}

	@GetMapping("/downloadpdf{datefrom}/{dateuntil}")
	public ModelAndView downloadpdf(@PathVariable("datefrom") String from, @PathVariable("dateuntil") String until,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("admin/register/register");
		String path = System.getenv(Constants.OPENSHIFT_DATA_DIR);
		File file = new File(path.concat("register.pdf"));
		List<RegisterEntity> register = registerService.findByDates(from, until);
		registerService.generatePdf(register, file);
		model.addObject(ConstantsViews.ADMINFORM, new AdminForm());
		model.addObject("register", register);
		response.setContentType("application/force-download");
		response.setHeader("Content-Disposition", "attachment; filename=register.pdf");
		try (InputStream inputStream = new FileInputStream(file)) {
			IOUtils.copy(inputStream, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			logger.error(java.util.logging.Level.SEVERE.getName());
		}
		return model;
	}
}
