package com.atmj.jsboot.admin.forms;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * The Class FileUploadForm.
 */
public class FileUploadForm {

	/** The fichero. */
	CommonsMultipartFile fichero;

	/**
	 * Gets the fichero.
	 * 
	 * @return the fichero
	 */
	public CommonsMultipartFile getFichero() {
		return fichero;
	}

	/**
	 * Sets the fichero.
	 * 
	 * @param fichero
	 *            the new fichero
	 */
	public void setFichero(CommonsMultipartFile fichero) {
		this.fichero = fichero;
	}
}