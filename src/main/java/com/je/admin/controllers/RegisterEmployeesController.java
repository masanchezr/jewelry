package com.je.admin.controllers;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.dbaccess.entities.RegisterEntity;
import com.je.forms.SearchForm;
import com.je.services.registers.RegisterService;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;
import com.je.validators.SearchFormValidator;

@Controller
public class RegisterEmployeesController {

	@Autowired
	private RegisterService registerService;

	@Autowired
	private SearchFormValidator adminSearchValidator;

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(ShoppingsAdminController.class);

	@RequestMapping(value = "/searchRegisterEmployees")
	public ModelAndView searchRegisterEmployees() {
		ModelAndView model = new ModelAndView("searchregisteremployees");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		model.addObject("searchDateForm", new SearchForm());
		return model;
	}

	@RequestMapping(value = "/registeremployees")
	public ModelAndView registeremployees(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchForm form,
			BindingResult result, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		adminSearchValidator.validate(form, result);
		if (result.hasErrors()) {
			model.addObject(ConstantsJsp.FORMSEARCH, new SearchForm());
			model.setViewName("searchregisteremployees");
		} else {
			List<RegisterEntity> register = registerService.findByDates(form.getDatefrom(), form.getDateuntil());
			String path = System.getenv(Constants.OPENSHIFT_DATA_DIR);
			File file = new File(path.concat("register.pdf"));
			model.addObject("register", register);
			model.setViewName("registeremployees");
			model.addObject("searchDateForm", form);
			registerService.generatePdf(register, file);
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment; filename=register.pdf");
			try (InputStream inputStream = new FileInputStream(file)) {
				IOUtils.copy(inputStream, response.getOutputStream());
				response.flushBuffer();
			} catch (IOException e) {
				logger.error(java.util.logging.Level.SEVERE.getName());
			}
		}
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		return model;
	}
}
