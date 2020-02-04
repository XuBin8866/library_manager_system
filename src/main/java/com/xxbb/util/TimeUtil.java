package com.xxbb.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Title: TimeUtil.java
 * @Package com.xxbb.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐斌
 * @date 2020年1月30日
 * @version V1.0
 */

public class TimeUtil {
	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	// 获取时间
	public static String getTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		return dateFormat.format(date);
	}

	// 获取日期时间
	public static String getDateTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}

	// 日期计算
	public static String getLaterDate(int days) {
		Date date = new Date();
		int leapYear = 366;
		int[] leapMonth = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int commonYear = 365;
		int[] commonMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int daySum = 0;

		// 获取当前年月日
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		int year = calender.get(Calendar.YEAR);
		int month = calender.get(Calendar.MONTH) + 1;
		int day = calender.get(Calendar.DATE);

		// 闰年
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {

			// 将日期转化为天数进行计算
			for (int i = 0; i < month - 1; i++) {
				daySum += leapMonth[i];
			}
			daySum += day;
			daySum += days;
			// 只考虑加一年的情况
			// 日期增加后没到下一年
			if (daySum <= leapYear) {
				int i = 0;
				for (i = 0;; i++) {
					daySum -= leapMonth[i];
					if (daySum < 1) {
						daySum += leapMonth[i];
						break;
					}
				}
				month = i + 1;
				day = daySum;
			}
			// 日期增加后到了下一年
			else {
				year += 1;
				daySum -= leapYear;
				int i = 0;
				for (i = 0;; i++) {
					daySum -= commonMonth[i];
					if (daySum < 1) {
						daySum += commonMonth[i];
						break;
					}
				}
				month = i + 1;
				day = daySum;

			}

		}
		// 平年
		else {
			// 将日期转化为天数进行计算
			for (int i = 0; i < month - 1; i++) {
				daySum += commonMonth[i];
			}
			daySum += day;
			daySum += days;
			// 只考虑加一年的情况、
			// 日期增加后没到下一年
			if (daySum <= commonYear) {
				int i = 0;
				for (i = 0;; i++) {
					daySum -= commonMonth[i];
					if (daySum < 1) {
						daySum += commonMonth[i];
						break;
					}
				}
				month = i + 1;
				day = daySum;
			}
			// 日期增加后没到下一年
			else {
				year += 1;
				if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
					commonMonth = leapMonth;
				}
				daySum -= commonYear;
				int i = 0;
				for (i = 0;; i++) {
					daySum -= commonMonth[i];
					if (daySum < 1) {
						daySum += commonMonth[i];
						break;
					}
				}
				month = i + 1;
				day = daySum;

			}
		}

		return year + "-" + month + "-" + day;

	}

	// 日期计算
	public static String getLaterDate(String date_str, int days) {
		int leapYear = 366;
		int[] leapMonth = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int commonYear = 365;
		int[] commonMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int daySum = 0;

		// 获取日期
		int year=Integer.parseInt(date_str.substring(0,4));
		int month = Integer.parseInt(date_str.substring(5,7));
		int day = Integer.parseInt(date_str.substring(8,10));

		// 闰年
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {

			// 将日期转化为天数进行计算
			for (int i = 0; i < month - 1; i++) {
				daySum += leapMonth[i];
			}
			daySum += day;
			daySum += days;
			// 只考虑加一年的情况
			// 日期增加后没到下一年
			if (daySum <= leapYear) {
				int i = 0;
				for (i = 0;; i++) {
					daySum -= leapMonth[i];
					if (daySum < 1) {
						daySum += leapMonth[i];
						break;
					}
				}
				month = i + 1;
				day = daySum;
			}
			// 日期增加后到了下一年
			else {
				year += 1;
				daySum -= leapYear;
				int i = 0;
				for (i = 0;; i++) {
					daySum -= commonMonth[i];
					if (daySum < 1) {
						daySum += commonMonth[i];
						break;
					}
				}
				month = i + 1;
				day = daySum;

			}

		}
		// 平年
		else {
			// 将日期转化为天数进行计算
			for (int i = 0; i < month - 1; i++) {
				daySum += commonMonth[i];
			}
			daySum += day;
			daySum += days;
			// 只考虑加一年的情况、
			// 日期增加后没到下一年
			if (daySum <= commonYear) {
				int i = 0;
				for (i = 0;; i++) {
					daySum -= commonMonth[i];
					if (daySum < 1) {
						daySum += commonMonth[i];
						break;
					}
				}
				month = i + 1;
				day = daySum;
			}
			// 日期增加后没到下一年
			else {
				year += 1;
				if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
					commonMonth = leapMonth;
				}
				daySum -= commonYear;
				int i = 0;
				for (i = 0;; i++) {
					daySum -= commonMonth[i];
					if (daySum < 1) {
						daySum += commonMonth[i];
						break;
					}
				}
				month = i + 1;
				day = daySum;

			}
		}

		return year + "-" + month + "-" + day;

	}
}
