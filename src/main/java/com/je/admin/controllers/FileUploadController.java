package com.je.admin.controllers;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.je.admin.forms.AdminForm;
import com.je.dbaccess.entities.JewelEntity;
import com.je.services.jewels.JewelService;
import com.je.utils.constants.Constants;
import com.je.utils.constants.ConstantsJsp;

/**
 * The Class FileUploadController.
 */
@Controller
public class FileUploadController {

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	private JewelService jewelService;

	private static final String MESSAGE = "message";

	@GetMapping(value = "/upload")
	public ModelAndView provideUploadInfo() {
		ModelAndView model = new ModelAndView("uploadimg");
		model.addObject(ConstantsJsp.ADMINFORM, new AdminForm());
		log.warn("provideUploadInfo()");
		return model;
	}

	@PostMapping(value = "/upload")
	public ModelAndView handleFileUpload(@RequestParam(Constants.NAME) String name,
			@RequestParam("file") MultipartFile file) {
		ModelAndView model = provideUploadInfo();
		log.warn("Inicio handleFileUpload");
		if (!file.isEmpty()) {
			JewelEntity jewel = jewelService.selectProduct(Long.valueOf(name));
			try (BufferedOutputStream stream = new BufferedOutputStream(getFile(name, model))) {
				byte[] bytes = file.getBytes();
				stream.write(bytes);
			} catch (IOException io) {
				model.addObject(MESSAGE, "You successfully uploaded ");
			} finally {
				jewel.setImg((name).concat(Constants.JPG));
				jewelService.updateJewelEntity(jewel);
			}
		} else {
			log.warn("El fichero esta vacio");
			model.addObject(MESSAGE, "You failed to upload because the file was empty.");
		}
		return model;
	}

	private FileOutputStream getFile(String name, ModelAndView model) {
		String env = System.getenv("OPENSHIFT_DATA_DIR");
		FileOutputStream file = null;
		try {
			file = new FileOutputStream(env.concat(name).concat(Constants.JPG));
		} catch (FileNotFoundException e) {
			model.addObject(MESSAGE, "The file can not be opened ");
		}
		return file;
	}

	@GetMapping("/endfileupload")
	public String goodbye(SessionStatus status) {
		status.setComplete();
		return "goodbye";
	}
}