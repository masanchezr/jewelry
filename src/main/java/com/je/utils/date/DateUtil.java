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

/**
 * The Class DateUtil.
 */
public class DateUtil {

	/**
	 * Gets the date.
	 *
	 * @param sdate
	 *            the sdate
	 * @return the date
	 */
	public static Date getDate(String sdate) {
		Date date = null;
		String pattern = null, p;
		SimpleDateFormat sdf = null;
		Map<String, String> patterns = new HashMap<String, String>();
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
				e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * Checks if is date.
	 *
	 * @param sdate
	 *            the sdate
	 * @return true, if is date
	 */
	public static boolean isDate(String sdate) {
		boolean isdate = false;
		List<String> patterns = new ArrayList<String>();
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

	public static String getStringDateFormatdd_MM_yyyy(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(date);
	}
}
