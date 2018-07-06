package yxy.videolineview;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 描述：日期工具类
 * by  minghui yxy
 * @date 2013-03-01
 */
public class DateUtils {
	public static final String DEFAULT_FORMAT_DATE_WITHOUT_TIME = "yyyy-MM-dd";
	public static final String DEFAULT_FORMAT_DATE = "yyyy-MM-dd HH:mm:ss";
	public static final BigDecimal BASETIME = new BigDecimal("60.00");
	public static final Pattern DATE_PATTERN = Pattern.compile("^(?:(?!0000)[0-9]{4}([-/.]?)(?:(?:0?[1-9]|1[0-2])([-/.]?)(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])([-/.]?)(?:29|30)|(?:0?[13578]|1[02])([-/.]?)31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-/.]?)0?2([-/.]?)29)$");

	public static boolean validateDateFormat(String dateText){
		return DATE_PATTERN.matcher(dateText).matches();
	}
	
	public static Timestamp now(){
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static Date resetTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static String formatDate(Date date, String formatStr) {
		return (new SimpleDateFormat((formatStr == null ? DEFAULT_FORMAT_DATE
				: formatStr))).format(date);
	}

	public static Date formatDate(String dateStr, String formatStr)
			throws ParseException {
		return (new SimpleDateFormat((formatStr == null ? DEFAULT_FORMAT_DATE
				: formatStr))).parse(dateStr);
	}

	public static Timestamp formatTime(String dateStr) {
		return Timestamp.valueOf(dateStr);
	}

	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	public static int getYear(String dateStr, String format)
			throws ParseException {
		return getYear(formatDate(dateStr, format));
	}

	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	public static int getMonth(String dateStr, String format)
			throws ParseException {
		return getMonth(formatDate(dateStr, format));
	}

	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static int getDay(String dateStr, String format)
			throws ParseException {
		return getDay(formatDate(dateStr, format));
	}

	public static int getWeekDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	public static int getWeekDay(String dateStr, String format)
			throws ParseException {
		return getWeekDay(formatDate(dateStr, format));
	}

	public static int getDayCountInMonth(int month, boolean isLeapYear) {
		int dayCount = 0;
		if (month >= Calendar.JANUARY && month <= Calendar.DECEMBER) {
			if (month == Calendar.JANUARY || month == Calendar.MARCH
					|| month == Calendar.MAY || month == Calendar.JULY
					|| month == Calendar.AUGUST || month == Calendar.OCTOBER
					|| month == Calendar.DECEMBER) {
				dayCount = 31;
			} else if (month == Calendar.APRIL || month == Calendar.JUNE
					|| month == Calendar.SEPTEMBER
					|| month == Calendar.NOVEMBER) {
				dayCount = 30;
			} else if (month == Calendar.FEBRUARY) {
				if (isLeapYear) {
					dayCount = 29;
				} else {
					dayCount = 28;
				}
			}
		}
		return dayCount;
	}

	public static boolean isLeapYear(int year) {
		if (year % 4 == 0 || year % 100 != 0 && year % 400 == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean compareDay(Date d1, Date d2) {
		return resetTime(d1).after(resetTime(d2));
	}

	/**
	 * 日期差 modify by jian_xie 2011-12-1
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long daySub(Date startDate, Date endDate) {
		return (startDate.getTime() - endDate.getTime()) > 0 ? (startDate
				.getTime() - endDate.getTime()) / 86400000
				: (endDate.getTime() - startDate.getTime()) / 86400000;
	}

	public static Date addOneDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
		return resetTime(c.getTime());
	}
	//减少一天
	public static Date lessenOneDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 1);
		return resetTime(c.getTime());
	}
	public static String lessenOneDay(String dateStr)throws Exception {
		Date date = formatDate(dateStr, DEFAULT_FORMAT_DATE_WITHOUT_TIME);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 1);
		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT_DATE_WITHOUT_TIME);
		return df.format(resetTime(c.getTime()));
	}

	/**
	 * 把时间除去毫秒显示 add by jian_xie
	 */
	public static String getFormatDateWithoutMillSecond(String time) {
		if (time == null || time.trim().length() == 0) {
			return null;
		}
		return time.substring(0, 19);
	}
	
	/*
	 * 通过年月获取起始时间
	 */
	public static Date getDateFromYearAndMonth(int year, int month){
		if(month > 12){
			year++;
			month -= 12;
		}
		Date resultDate = new Date(year - 1900, month - 1, 1);
		resultDate = resetTime(resultDate);
		return resultDate;
	}
	
	/*
	 * 通过季度获取起始时间
	 */
	public static Date getDateFromSeason(int year, int season){
		if(season < 1){
			throw new RuntimeException("季度必须大于1");
		}
		if(season > 4){
			year += season / 4;
			season = season % 4;
		}
		int month = (season-1)*3 + 1;
		return getDateFromYearAndMonth(year, month);
	}
	
	/*
	 * 获取某个月的最后一天最后瞬间
	 */
	public static Date getLastMomentOfDay(Date original) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(original);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, 0);//这个超过0的话会被进位,会变成第二天
		Date lastDate = calendar.getTime();
		return lastDate;
    }  

	/**
	 * 格式化(从、开始时间等)时间,方便hql时间查询比较
	 * add by shiwu_bin 2013-08-05
	 * */
	public static String formateStartTime(String startTime){
		return startTime + " 00:00:00";
	}
	
	/**
	 * 格式化(至、结束时间等)时间,方便hql时间查询比较
	 * add by shiwu_bin 2013-08-05
	 * */
	public static String formateEndTime(String endTime){
		return endTime + " 23:59:59";
	}
	
	/**
	 * 获取某年某月的最后一天的日期字符串(如2013-08-31)
	 */
	public static String getLastDayText(Integer year, Integer month) {
		return formatDate(getLastDay(year,month), DEFAULT_FORMAT_DATE_WITHOUT_TIME +" 23:59:59");
    }
	
	public static Date getLastDay(Integer year, Integer month){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}
	//某月最后一天
	public static String getLastDay(String dateStr){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT_DATE_WITHOUT_TIME);
		try {
			Date date = formatDate(dateStr,DEFAULT_FORMAT_DATE_WITHOUT_TIME);
			cal.setTime(date);
			cal.add(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.add(Calendar.DAY_OF_MONTH, -1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return df.format(cal.getTime());
	}
	/**
	 * 获取某年某月的第一天的日期字符串(如2013-08-01)
	 */
	public static String getFirstDayText(Integer year, Integer month) {
	    if(month < 10){
	    	return year + "-0" + month + "-01 00:00:00";
	    }
		return year + "-" + month+"-01" + " 00:00:00";
    }  
	
	public static Date getFirstDay(Integer year, Integer month){
		try {
			return formatDate(year+"-"+month+"-1", DEFAULT_FORMAT_DATE_WITHOUT_TIME);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//某月第一天
	public static String getFirstDay(String dateStr){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT_DATE_WITHOUT_TIME);
		try {
			Date date = formatDate(dateStr,DEFAULT_FORMAT_DATE_WITHOUT_TIME);
			cal.setTime(date);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return df.format(cal.getTime());
	}
	
	/**
	 * 获取一个月以前的日期
	 * */
	public static Date getBeforeMonthDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(cal.MONTH, -1);
		return cal.getTime();
	}
	
	public static Date addOneSecond(Date time){
		return new Timestamp(time.getTime() + 1000);
	}
	/**
	 * 取所在日期的星期第一天，星期日
	 * @param args
	 * weifengdeng 2015-3-10
	 */
	public static Date getFirstWeekDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//本周第一天，星期日
		return cal.getTime();
	}
	public static Date getFirstWeekDay(String dateStr){
		Date date = null;
		try {
			date = formatDate(dateStr,DEFAULT_FORMAT_DATE_WITHOUT_TIME);
			date = getFirstWeekDay(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static String getFirstWeekDayStr(String dateStr){
		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT_DATE_WITHOUT_TIME);
		return df.format(getFirstWeekDay(dateStr));
	}
	/**
	 * 取所在日期的星期最后一天，星期六
	 * @param args
	 */
	public static Date getLastWeekDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);//本周最后一天，星期六
		return cal.getTime();
	}
	public static Date getLastWeekDay(String dateStr){
		Date date = null;
		try {
			date = formatDate(dateStr,DEFAULT_FORMAT_DATE_WITHOUT_TIME);
			date = getLastWeekDay(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static String getLastWeekDayStr(String dateStr){
		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT_DATE_WITHOUT_TIME);
		return df.format(getLastWeekDay(dateStr));
	}
	/**
	 * 取所在日期的季度第一天
	 * @param args
	 */
	public static Date getFirstQuarter(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = getQuarterInMonth(cal.get(Calendar.MONTH), true);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	public static Date getFirstQuarter(String dateStr){
		Date date = null;
		try {
			date = formatDate(dateStr,DEFAULT_FORMAT_DATE_WITHOUT_TIME);
			date = getFirstQuarter(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static String getFirstQuarterStr(String dateStr){
		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT_DATE_WITHOUT_TIME);
		return df.format(getFirstQuarter(dateStr));
	}
	/**
	 * 取所在日期的季度最后一天
	 * @param args
	 */
	public static Date getLastQuarter(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = getQuarterInMonth(cal.get(Calendar.MONTH), false);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}
	public static Date getLastQuarter(String dateStr){
		Date date = null;
		try {
			date = formatDate(dateStr,DEFAULT_FORMAT_DATE_WITHOUT_TIME);
			date = getLastQuarter(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static String getLastQuarterStr(String dateStr){
		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT_DATE_WITHOUT_TIME);
		return df.format(getLastQuarter(dateStr));
	}
	// 返回第几个月份，不是几月   
	// 季度一年四季， 第一季度：2月-4月， 第二季度：5月-7月， 第三季度：8月-10月， 第四季度：11月-1月   
	private static int getQuarterInMonth(int month, boolean isQuarterStart) {   
		int months[] = { 1, 4, 7, 10 };
		if (!isQuarterStart) {
			months = new int[] { 3, 6, 9, 12 };
		}
		if (month >= 0 && month < 3)
			return months[0];
		else if (month >= 3 && month < 6)
			return months[1];
		else if (month >= 6 && month < 9)
			return months[2];
		else
			return months[3]; 
	}
	/**
	 * 字符串转timestamp
	 */
	public static Timestamp formatTimestamp(String time, String formatStr)throws Exception {
		Date date = formatDate(time, formatStr);
		Timestamp stamp = new Timestamp(date.getTime());
		return stamp;
	}
	//date 转 timestamp
	public static Timestamp formatTimestamp(Date date)throws Exception {
		Timestamp stamp = new Timestamp(date.getTime());
		return stamp;
	}
	
	public static void main(String[] args){
	}
	
	
}
