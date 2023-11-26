package com.atmj.jsboot.admin.forms;

import org.springframework.web.multipart.MultipartFile;

/**
 * The Class FileUploadForm.
 */
public class FileUploadForm {

	/** The fichero. */
	MultipartFile fichero;

	/**
	 * Gets the fichero.
	 * 
	 * @return the fichero
	 */
	public MultipartFile getFichero() {
		return fichero;
	}

	/**
	 * Sets the fichero.
	 * 
	 * @param fichero the new fichero
	 */
	public void setFichero(MultipartFile fichero) {
		this.fichero = fichero;
	}
}