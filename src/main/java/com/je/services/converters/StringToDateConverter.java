package com.je.services.converters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dozer.DozerConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.je.utils.date.DateUtil;
import com.je.utils.string.Util;

/**
 * The Class StringToDateConverter.
 */
public class StringToDateConverter extends DozerConverter<String, Date> {
	/** The log. */
	private static Logger log = LoggerFactory
			.getLogger(StringToDateConverter.class);

	/**
	 * Instantiates a new string to date converter.
	 */
	public StringToDateConverter() {
		super(String.class, Date.class);
	}

	@Override
	public Date convertTo(String source, Date destination) {
		Date date = null;
		log.info("source:" + source);
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
