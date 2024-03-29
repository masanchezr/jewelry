package com.atmj.jsboot.utils.string;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class Util.
 */
public class Util {

	private Util() {

	}

	/**
	 * Checks if is empty.
	 * 
	 * @param string the string
	 * @return true, if is empty
	 */
	public static boolean isEmpty(String string) {
		boolean isEmpty = false;
		if (string == null || string.isEmpty() || string.trim().isEmpty()) {
			isEmpty = true;
		}
		return isEmpty;
	}

	/**
	 * Gets the keyword.
	 *
	 * @param name the name
	 * @return the keyword
	 */
	public static String getKeyword(String name) {
		return name.toLowerCase().replace(" ", "-");
	}

	public static boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException excepcion) {
			return false;
		}
	}

	public static BigDecimal getNumber(String cadena) {
		try {
			return new BigDecimal(cadena.replace(',', '.'));
		} catch (NumberFormatException excepcion) {
			return null;
		}
	}

	public static String refactorNIF(String nif) {
		nif = nif.replace("-", "");
		nif = nif.toUpperCase();
		return nif.replaceAll("\\s", "");
	}

	public static boolean isNifNie(String nif) {
		boolean isValid = false;
		if (Character.isLetter(nif.charAt(nif.length() - 1))) {
			// si es NIE, eliminar la x,y,z inicial para tratarlo como nif
			if (nif.startsWith("X") || nif.startsWith("Y") || nif.startsWith("Z")) {
				String firstLetter = "";
				if (nif.startsWith("X")) {
					firstLetter = "0";
				}
				if (nif.startsWith("Y")) {
					firstLetter = "1";
				}
				if (nif.startsWith("Z")) {
					firstLetter = "2";
				}
				nif = firstLetter.concat(nif.substring(1));
			}
			isValid = matches(nif);
		} else if (Character.isLetter(nif.charAt(0))) {
			isValid = true;
		} else {
			isValid = nif.length() > 9;
		}
		return isValid;
	}

	private static boolean matches(String nif) {
		Pattern nifPattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKE])");
		Matcher m = nifPattern.matcher(nif);
		if (m.matches()) {
			String letra = m.group(2);
			// Extraer letra del NIF
			String letras = "TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke";
			int dni = Integer.parseInt(m.group(1));
			dni = dni % 23;
			String reference = letras.substring(dni, dni + 1);
			return reference.equalsIgnoreCase(letra);
		} else {
			return false;
		}
	}
}
