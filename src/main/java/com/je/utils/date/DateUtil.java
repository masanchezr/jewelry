package com.je.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class DateUtil.
 */
public class DateUtil {

	private DateUtil() {

	}

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * Gets the date.
	 *
	 * @param sdate the sdate
	 * @return the date
	 */
	public static Date getDate(String sdate) {
		Date date = null;
		String pattern = null;
		String p;
		SimpleDateFormat sdf = null;
		Map<String, String> patterns = new HashMap<>();
		patterns.put("\\d{2}/\\d{2}/\\d{4}", "dd/MM/yyyy");
		patterns.put("(\\d{2})-(\\d{2})-(\\d{4})", "dd-MM-yyyy");
		patterns.put("(\\d{4})-(\\d{2})-(\\d{2})", "yyyy-MM-dd");
		patterns.put("(\\d{4})(\\d{2})(\\d{2})", "yyyyMMdd");
		patterns.put("\\d{2}/\\d{4}", "MM/yyyy");
		patterns.put("\\w{3}\\s\\w{3}\\s\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\sCET\\s\\d{4}", "EEE MMM dd HH:mm:ss zzz yyyy");
		Set<String> spatterns = patterns.keySet();
		Iterator<String> ipatterns = spatterns.iterator();
		while (ipatterns.hasNext() && sdf == null) {
			pattern = ipatterns.next();
			if (sdate.matches(pattern)) {
				p = patterns.get(pattern);
				sdf = new SimpleDateFormat(p);
			}
		}
		if (sdf != null) {
			try {
				date = sdf.parse(sdate);
			} catch (ParseException e) {
				logger.error("No se ha podido parsear la fecha", e);
			}
		}
		return date;
	}

	/**
	 * Checks if is date.
	 *
	 * @param sdate the sdate
	 * @return true, if is date
	 */
	public static boolean isDate(String sdate) {
		boolean isdate = false;
		List<String> patterns = new ArrayList<>();
		// dd/MM/yyyy
		patterns.add("\\d\\d/\\d\\d/\\d\\d\\d\\d");
		// dd.MM.yyyy
		patterns.add("\\d\\d.\\d\\d.\\d\\d\\d\\d");
		// dd-MM-yyyy
		patterns.add("\\d\\d-\\d\\d-\\d\\d\\d\\d");
		// yyyy-MM-dd
		patterns.add("\\d\\d\\d\\d-\\d\\d-\\d\\d");
		// MM/yyyy
		patterns.add("\\d\\d/\\d\\d\\d\\d");
		Iterator<String> ipatterns = patterns.iterator();
		while (ipatterns.hasNext() && !isdate) {
			if (sdate.matches(ipatterns.next())) {
				isdate = true;
			}
		}
		return isdate;
	}

	public static Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}

	/**
	 * Convert Date to String format dd-MM-yyy
	 * 
	 * @param date
	 * @return date in format string
	 */
	public static String getStringDateFormatddMMyyyy(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(date);
	}

	public static Date today() {
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}
}
