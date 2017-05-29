package com.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.time.DateFormatUtils;

public final class DateUtil {

	public static final String DATE_FORMAT = "MM/dd/yyyy";

	public static final String DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm";

	public static final String ORA_DATE_FORMAT = "yyyyMMdd";

	public static final String ORA_DATE_FORMAT_SIMPLE = "yyMMdd";

	public static final String ORA_DATE_TIME_FORMAT = "yyyyMMddHHmm";

	public static final String ORA_DATE_TIME_SHORT_FORMAT = "yyMMddHHmmss";
	
	public static final String ORA_DATE_TIMES_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final String DATE_TIMES_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String TIMESTAMP_FORMAT = "yyyyMMddHHmmssSSS";

	public static final String ORA_DATES_FORMAT = "yyyy-MM-dd";

	/**
	 * 根据指定的格式与时区返回指定时间
	 * @param pattern
	 * @param locale
	 * @return String
	 */
	public static String getTime(String pattern, Locale locale) {
		Calendar cl = Calendar.getInstance();
		return DateFormatUtils.format(cl.getTime(), pattern, locale);
	}
	
	/**
	 * 把日期转换成字符串型
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String toString(Date date, String pattern){
		if(date == null){
			return "";
		}
		if(pattern == null){
			pattern = "yyyy-MM-dd";;
		}
		String dateString = "";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			dateString = sdf.format(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dateString;
	}
	
	/**
	 * 获得服务器当天日期yyMMdd格式
	 */
	public static String serverCurrentDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(
				ORA_DATE_FORMAT_SIMPLE);
		String str = formatter.format(date);
		return str;
	}

	/**
	 * 获得服务器当前时点yyyy-MM-dd HH:mm:ss格式
	 */
	public static String serverCurrentDateTime() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIMES_FORMAT);
		String str = formatter.format(date);
		return str;
	}

	/**
	 * 获得服务器当前时点yyyyMMddHHmmssSSS格式
	 */
	public static String serverCurrentTimeStamp() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(TIMESTAMP_FORMAT);
		String str = formatter.format(date);
		return str;
	}
	
	/**
	 * 
	* <p>方法名称: serverCurrentTimeStamp|描述: 根据指定的格式返回指定时间</p>
	* @param pattern
	* @return
	 */
	public static String serverCurrentTimeStamp(String pattern) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String str = formatter.format(date);
		return str;
	}
}
