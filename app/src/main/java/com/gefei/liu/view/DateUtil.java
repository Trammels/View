package com.gefei.liu.view;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

/**
 * 日期处理工具类
 * 
 */
public class DateUtil {

	public static final String YEAR = "year";

	public static final String MONTH = "month";

	public static final String WEEK = "week";

	public static final String DAY = "day";

	public static final String DAY_OF_WEEK = "dayOfWeek";

	public static final String HOUR = "hour";

	public static final String SECONDS = "dne-seconds";

	public static final String MINUTES = "dne-minutes";

	public static final String HOURS = "dne-hours";

	public static final String DAYS = "dne-days";

	public static final String MONTHS = "dne-months";

	public static final String YEARS = "dne-years";

	@Deprecated
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	@Deprecated
	public static final SimpleDateFormat MONTH_FORMAT = new SimpleDateFormat("yyyy-MM");
	
	@Deprecated
	public static final SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy");
	
	@Deprecated
	public static final SimpleDateFormat THRID_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Deprecated
	public static final SimpleDateFormat SECOND_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Deprecated
	public static final SimpleDateFormat FOURTH_FORMAT = new SimpleDateFormat("HH:mm:ss");
	
	static{
		DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		MONTH_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		YEAR_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		THRID_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		SECOND_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		FOURTH_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
	}

	public static DateFormat getCnDateFormat(String pattern) {
		DateFormat format = new SimpleDateFormat(pattern);
		format.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		return format;
	}

	public static String getPreviousMonthFirst(int k) {
		String str = "";
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);
		lastDate.add(Calendar.MONTH, k);

		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

		str = DATE_FORMAT.format(lastDate.getTime());
		return str;
	}

	public static String getPreviousMonthLast(int k) {
		String str = "";
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);
		lastDate.add(Calendar.MONTH, k + 1);
		lastDate.add(Calendar.DATE, -1);
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		str = DATE_FORMAT.format(lastDate.getTime());
		return str;
	}

	public static Date getDateByAddSecond(int amount) {
		return getDateByAddSecond(null, amount);
	}

	public static Date getDateByAddSecond(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		if (Validator.isNotNull(date))
			calendar.setTime(date);
		calendar.add(Calendar.SECOND, amount);
		return calendar.getTime();
	}

	public static Date getDateByAddMillisecond(int amount) {
		return getDateByAddMillisecond(null, amount);
	}

	public static Date getDateByAddMillisecond(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		if (Validator.isNotNull(date))
			calendar.setTime(date);
		calendar.add(Calendar.MILLISECOND, amount);
		return calendar.getTime();
	}

	public static Date getDateByAddMonth(int amount) {
		return getDateByAddMonth(null, amount);
	}

	public static Date getDateByAddMonth(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		if (Validator.isNotNull(date))
			calendar.setTime(date);
		calendar.add(Calendar.MONTH, amount);
		return calendar.getTime();
	}

	public static Date getDateByAddYear(int amount) {
		return getDateByAddYear(null, amount);
	}

	public static Date getDateByAddYear(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		if (Validator.isNotNull(date))
			calendar.setTime(date);
		calendar.add(Calendar.YEAR, amount);
		return calendar.getTime();
	}

	public static Date getDateByAddDayOfYear(int amount) {
		return getDateByAddDayOfYear(null, amount);
	}

	public static Date getDateByAddDayOfYear(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		if (Validator.isNotNull(date))
			calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, amount);
		return calendar.getTime();
	}

	public static Date getDateByAddWeekOfYear(int amount) {
		return getDateByAddWeekOfYear(null, amount);
	}

	public static Date getDateByAddWeekOfYear(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		if (Validator.isNotNull(date))
			calendar.setTime(date);
		calendar.add(Calendar.WEEK_OF_YEAR, amount);
		return calendar.getTime();
	}

	public static Date getDateByAddWeekOfMonth(int amount) {
		return getDateByAddWeekOfMonth(null, amount);
	}

	public static Date getDateByAddWeekOfMonth(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		if (Validator.isNotNull(date))
			calendar.setTime(date);
		calendar.add(Calendar.WEEK_OF_MONTH, amount);
		return calendar.getTime();
	}

	public static Date getLastDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return calendar.getTime();
	}

	public static Date getFirstDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date getLastDateOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		calendar.add(Calendar.WEEK_OF_YEAR, 1);
		return calendar.getTime();
	}

	public static Date getFirstDateOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return calendar.getTime();
	}

	public static Date getLastDateOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, 1);
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return calendar.getTime();
	}

	public static Date getFirstDateOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		return calendar.getTime();
	}

	public static int getWeekByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			calendar.add(Calendar.DAY_OF_YEAR, -1);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	public static int getDayOfWeekByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return day == 0 ? 7 : day;
	}

	public static long beforeBySecond(Date date) {

		Date now = new Date();
		long nowLong = now.getTime() / 1000;

		long dateLong = date.getTime() / 1000;

		return nowLong - dateLong;
	}

	public static String getUnitOfTime(long seconds) {
		if (seconds < 60)
			return SECONDS;
		else if (seconds < 3600)
			return MINUTES;
		else if (seconds < 86400)
			return HOURS;
		else if (seconds < 2592000)
			return DAYS;
		else
			return MONTHS;
	}

	public static long getNumberOfUnit(long seconds, String unit) {
		if (unit.equals(SECONDS))
			return seconds;
		else if (unit.equals(MINUTES))
			return seconds / 60;
		else if (unit.equals(HOURS))
			return seconds / 3600;
		else if (unit.equals(DAYS))
			return seconds / 86400;
		else
			return seconds / 2592000;
	}

	public static int getWeeksBetween(Date startDate, Date endDate,
			TimeZone timeZone) {

		int offset = timeZone.getRawOffset();

		Calendar startCal = new GregorianCalendar(timeZone);

		startCal.setTime(startDate);
		startCal.add(Calendar.MILLISECOND, offset);

		Calendar endCal = new GregorianCalendar(timeZone);

		endCal.setTime(endDate);
		endCal.add(Calendar.MILLISECOND, offset);

		int weeksBetween = 0;

		while (startCal.compareTo(endCal) < 0) {
			startCal.add(Calendar.WEEK_OF_MONTH, 1);

			weeksBetween++;
		}

		return weeksBetween;
	}

	public static long getSecondsBetween(Date startDate, Date endDate,
			TimeZone timeZone) {

		// Calendar start = Calendar.getInstance();
		// start.setTime(startDate);
		//
		// Calendar end = Calendar.getInstance();
		// end.setTime(endDate);
		//
		// int day = IdmDateUtil.getDaysBetween(startDate, endDate, timeZone);
		// int hour = end.get(Calendar.HOUR_OF_DAY) -
		// start.get(Calendar.HOUR_OF_DAY);
		// int minute = end.get(Calendar.MINUTE) - start.get(Calendar.MINUTE);
		// int second = end.get(Calendar.SECOND) - start.get(Calendar.SECOND);
		//
		// long result = day * Time.DAY + hour * Time.HOUR + minute *
		// Time.MINUTE + second * Time.SECOND;
		//
		// return result/1000;

		long startLong = startDate.getTime() / 1000;

		long endLong = endDate.getTime() / 1000;

		long result = endLong - startLong;

		return result;
	}

	// private static long longTime = 0l;
	/**
	 * 由java.util.Date到java.sql.Date的类型转换
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date getSqlDate(Date date) {
		return new Date(date.getTime());
	}

	/**
	 * date 格式化为字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToStr(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		return sdf.format(date);
	}

	/**
	 * str 格式化为时间
	 * 
	 * @param datestr
	 * @param format
	 * @return
	 */
	public static Date strToDate(String datestr, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		ParsePosition pos = new ParsePosition(0);
		return formatter.parse(datestr, pos);
	}

	/**
	 * 获得某年某月第一天的日期
	 * 
	 * @param year
	 * @param month
	 * @return Date
	 */
	public static Date getFirstDayOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * 获得某年某月最后一天的日期
	 * 
	 * @param year
	 * @param month
	 * @return Date
	 */
	public static Date getLastDayOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, 1);
		return getPreviousDate(calendar.getTime());
	}

	/**
	 * 获得某一日期的前一天
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date getPreviousDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, day - 1);
		return calendar.getTime();
	}

	/**
	 * 得到前某年某个月的天数，i从当前开始倒推
	 * 
	 * @param year
	 * @param month
	 * @param i
	 */
	public static void getEndDate(int year, int month, int i) {
		int index = month;
		int[][] days = new int[][] {
				{ 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
				{ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };
		boolean leap = year % 4 == 0 && (year % 400 == 0 || year % 100 != 0);
		int curLast = days[leap ? 0 : 1][index];
		int preLast = 0;
		if (index - i >= 0) {
			preLast = days[leap ? 0 : 1][index - i];
		}
		// System.out.println(preLast+"");
		// System.out.println(curLast+"");
	}

	/**
	 * ============================== 取得某月的天数
	 * 
	 * @param year
	 * @param month
	 * @return int
	 */
	public static int getDayCountOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, 0);
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 获得某年某季度的最后一天的日期
	 * 
	 * @param year
	 * @param quarter
	 * @return Date
	 */
	public static Date getLastDayOfQuarter(int year, int quarter) {
		int month = 0;
		if (quarter > 4) {
			return null;
		} else {
			month = quarter * 3;
		}
		return getLastDayOfMonth(year, month);

	}

	/**
	 * 获得某年某季度的第一天的日期
	 * 
	 * @param year
	 * @param quarter
	 * @return Date
	 */
	public static Date getFirstDayOfQuarter(int year, int quarter) {
		int month = 0;
		if (quarter > 4) {
			return null;
		} else {
			month = (quarter - 1) * 3 + 1;
		}
		return getFirstDayOfMonth(year, month);
	}

	/**
	 * 获得某年的第一天的日期
	 * 
	 * @param year
	 * @return Date
	 */
	public static Date getFirstDayOfYear(int year) {
		return getFirstDayOfMonth(year, 1);
	}

	/**
	 * 获得某年的最后一天的日期
	 * 
	 * @param year
	 * @return Date
	 */
	public static Date getLastDayOfYear(int year) {
		return getLastDayOfMonth(year, 12);
	}

	/**
	 * 返回指定日期的，按照固定的时间间隔的列表
	 */
	public static List<Date> getCurrentDatePayList(Date date, int minute) {
		List<Date> tempList = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		String str0 = dateToStr(new Date(), "yyyy-MM-dd") + " 00:00:00";
		calendar.setTime(strToDate(str0, "yyyy-MM-dd HH:mm:ss"));

		int p = 60 / minute * 24;

		for (int i = 0; i < p; i++) {
			if (i == 0) {
				tempList.add(calendar.getTime());
			} else {
				calendar.add(Calendar.MINUTE, minute);
				tempList.add(calendar.getTime());
			}
		}
		return tempList;
	}

	/**
	 * 比较两个日期大小
	 * 
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	public static int compareDate(Date arg0, Date arg1) {
		Calendar calendar0 = Calendar.getInstance();
		calendar0.setTime(arg0);
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(arg1);
		return calendar0.compareTo(calendar1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
