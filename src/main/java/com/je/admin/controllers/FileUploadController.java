package com.je.admin.controllers;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.dbaccess.entities.JewelEntity;
import com.je.services.jewels.JewelService;
import com.je.utils.constants.Constants;

/**
 * The Class FileUploadController.
 */
@Controller
public class FileUploadController {

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	private JewelService jewelService;

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView provideUploadInfo() {
		ModelAndView model = new ModelAndView("uploadimg");
		model.addObject("adminForm", new AdminForm());
		log.warn("provideUploadInfo()");
		return model;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
		ModelAndView model = provideUploadInfo();
		log.warn("Inicio handleFileUpload");
		if (!file.isEmpty()) {
			try {
				JewelEntity jewel = jewelService.selectProduct(Long.valueOf(name));
				byte[] bytes = file.getBytes();
				String env = System.getenv("OPENSHIFT_DATA_DIR");
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(env.concat(name).concat(Constants.JPG)));
				log.warn("Entorno: ".concat(env));
				stream.write(bytes);
				stream.close();
				model.addObject("message", "You successfully uploaded ");
				jewel.setImg((name).concat(Constants.JPG));
				jewelService.updateJewelEntity(jewel);
			} catch (Exception e) {
				model.addObject("message", "You failed to upload ");
				log.error(e.getMessage());
			}
		} else {
			log.warn("El fichero esta vacio");
			model.addObject("message", "You failed to upload because the file was empty.");
		}
		return model;
	}
}