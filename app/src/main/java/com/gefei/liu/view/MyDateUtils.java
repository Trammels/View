package com.gefei.liu.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 
 * @ClassName: DataUtils
 * @Description: 毫秒转化为日期
 * @author sh
 * @date 2015年8月26日 下午2:17:21
 *
 */
public class MyDateUtils {

	/**
	 * 
	 * @Title: getDay @Description: 精确到天 @param @param ss @param @return
	 *         设定文件 @return String 返回类型 @throws
	 */
	public static String getDay(String ss) {
		long ms = Long.parseLong(ss);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");// 初始化Formatter的转换格式。
		String hms = formatter.format(ms);
		return hms;
	}
	
	public static String getDay(long ss) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");// 初始化Formatter的转换格式。
		String hms = formatter.format(ss);
		return hms;
	}

	/**
	 * 
	 * @Title: getSecond @Description:精确到秒 @param @param ss @param @return
	 *         设定文件 @return String 返回类型 @throws
	 */
	public static String getSecond(String ss) {
		if (ss != null && !ss.trim().equals("")) {
			long ms = Long.parseLong(ss);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 初始化Formatter的转换格式。
			String hms = formatter.format(ms);
			return hms;
		} else {
			return "";
		}
	}
	

	public static String getHour(String ss) {
		if (ss != null && !ss.trim().equals("")) {
			long ms = Long.parseLong(ss);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");// 初始化Formatter的转换格式。
			String hms = formatter.format(ms);
			return hms;
		} else {
			return "";
		}
	}

	public static String getTiem(String ss) {
		if (ss != null && !ss.trim().equals("")) {
			long ms = Long.parseLong(ss);
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd HH:mm");// 初始化Formatter的转换格式。
			String hms = formatter.format(ms);
			return hms;
		} else {
			return "";
		}
	}

	public static String getSecond(long ss) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm:ss");
		String hms = formatter.format(ss);
		return hms;
	}

	public static Long getMS(String time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long millionSeconds = sdf.parse(time).getTime();
			return millionSeconds;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSecond2(String ss) {
		long ms = Long.parseLong(ss);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 初始化Formatter的转换格式。
		String hms = formatter.format(ms);
		return hms;
	}

	/** 当前时间的小时 */
	public static String getCurrentHour() {
		long ms = System.currentTimeMillis() + 1000 * 40 * 60;
		SimpleDateFormat formatter = new SimpleDateFormat("HH");// 初始化Formatter的转换格式。
		String hms = formatter.format(ms);
		return hms;
	}

	/** 当前时间分钟 */
	public static String getCurrentMin() {
		long ms = System.currentTimeMillis() + 1000 * 40 * 60;
		SimpleDateFormat formatter = new SimpleDateFormat("mm");// 初始化Formatter的转换格式。
		String hms = formatter.format(ms);
		return hms;
	}

	/** 当前时间日 */
	public static String getCurrentDay() {
		long ms = System.currentTimeMillis();
		SimpleDateFormat formatter = new SimpleDateFormat("dd");// 初始化Formatter的转换格式。
		String hms = formatter.format(ms);
		return hms;
	}

	/** 预约时间的日 */
	public static String[] getTwoDay() {
		String[] days = new String[2];
		String day = getDay(System.currentTimeMillis() + "");
		String month = day.substring(5, 7);
		String year = day.substring(0, 4);
		day = day.substring(8, 10);
		int m = Integer.parseInt(month);
		int d = Integer.parseInt(day);
		int y = Integer.parseInt(year);
		if (m == 2) {
			if ((y % 100 == 0 && y % 400 == 0) || (y % 100 != 0 && y % 4 == 0)) {
				if (d == 29) {
					days[0] = "29";
					days[1] = "01";
					return days;
				} else {
					days[0] = d + "";
					days[1] = d + 1 + "";
					return days;
				}
			} else {
				if (d == 28) {
					days[0] = "28";
					days[1] = "01";
					return days;
				} else {
					days[0] = d + "";
					days[1] = d + 1 + "";
					return days;
				}
			}
		} else if (m == 4 || m == 6 || m== 9 || m == 11) {
			if (d == 30) {
				days[0] = "30";
				days[1] = "31";
				return days;
			} else {
				days[0] = d + "";
				days[1] = d + 1 + "";
				return days;
			}
		} else {
			if (d == 31) {
				days[0] = "31";
				days[1] = "01";
				return days;
			} else {
				days[0] = d + "";
				days[1] = d + 1 + "";
				return days;
			}
		}
	}
}
