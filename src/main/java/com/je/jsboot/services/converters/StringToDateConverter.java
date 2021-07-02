package com.je.jsboot.services.converters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dozer.DozerConverter;

import com.je.jsboot.utils.date.DateUtil;
import com.je.jsboot.utils.string.Util;

/**
 * The Class StringToDateConverter.
 */
public class StringToDateConverter extends DozerConverter<String, Date> {

	/**
	 * Instantiates a new string to date converter.
	 */
	public StringToDateConverter() {
		super(String.class, Date.class);
	}

	@Override
	public Date convertTo(String source, Date destination) {
		Date date = null;
		if (!Util.isEmpty(source)) {
			date = DateUtil.getDate(source);
		}
		return date;
	}

	@Override
	public String convertFrom(Date source, String destination) {
		if (source != null) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.format(source);
		} else {
			return null;
		}
	}

}
