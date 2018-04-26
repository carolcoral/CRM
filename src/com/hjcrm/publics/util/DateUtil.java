package com.hjcrm.publics.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 日期工具类
 * @author likang
 * @date 2016-10-19 上午10:55:48
 */
public class DateUtil {
	public static final String DATE_HOURS_MINUTS = "MM-dd HH:mm";
	public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
	public static final String DATETIME_NOSECONDS = "yyyy-MM-dd HH:mm";
	public static final String DATETIMEOUT = "yyyyMMddHHmmss";
	public static final String TIMEOUT = "HHmmss";
	public static final String DATE_WEEK = "yyyy-MM-dd EEEE";
	public static final String DATE = "yyyy-MM-dd";
	public static final String DATE_YYYYMMDD = "yyyyMMdd";
	public static final String DATE_CH = "yyyy年MM月dd日";
	public static final String TIME = "HH:mm:ss";
	public static final String HOURS_MINUTS = "HH:mm";
	public static final String YEAR = "yyyy";
	public static final String MONTH_DAY = "MM-dd";
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	
	private static List<String> weekend = new ArrayList<String>();
	private static final DateFormat dateTimeFormatter = new SimpleDateFormat(DATETIME);
	
	static {
		weekend.add("Sun");
		weekend.add("Sat");
		weekend.add("星期六");
		weekend.add("星期日");
	}
	
	/**
	 * default pattern {@value #DATETIME}
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return dateTimeFormatter.format(date);
	}
	
	
	/**
	 * 获取一个没有格式，顺序排列的日期时间字符串。
	 * @return
	 */
	public static String getCurrentDateTimeWithOutFormat(){
		return new SimpleDateFormat(DATETIMEOUT).format(new Date());
	}
	
	public static String getCurrentTimeWithOutFormat(){
		return new SimpleDateFormat(TIMEOUT).format(new Date());
	}
	

	/**
	 * default pattern {@value #DATETIME}
	 * @param source
	 * @return
	 */
	public static Date parse(String source) {
		return parse(DATETIME, source);
	}
	
	public static Date parse(String pattern, String source) {
		try {
			if (!StringUtils.hasText(source)) return null;
			if (!StringUtils.hasText(pattern))
				return dateTimeFormatter.parse(source);
			return new SimpleDateFormat(pattern).parse(source);
		} catch (ParseException e) {
			throw new RuntimeException (e);
		}
	}

	public static String format(String pattern, Date date) {
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 判断两个日期之间的差距 return 相差的毫秒数
	 */
	public static long timeBetween(Date arg1, Date arg2) {
		if (arg1 == null || arg2 == null)
			return 0;
		if (arg1.before(arg2))
			return arg2.getTime() - arg1.getTime();
		else
			return arg1.getTime() - arg2.getTime();
	}

	/**
	 * arg1是否在arg2和arg3之间
	 * 
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 */
	public static boolean between(Date arg1, Date arg2, Date arg3) {
		return arg1.equals(arg2) || (arg1.after(arg2) && arg1.before(arg3));
	}

	/**
	 * 是否周末
	 * 
	 * @param arg
	 * @return
	 */
	public static boolean isWeekend(Date arg) {
		String str = format("E", arg);
		return weekend.contains(str) ? true : false;
	}
	
	/**
	 * 通过生日获取年龄
	 * @param birthday
	 * @return
	 */
	public static Integer getAge(Date birthday) {
		if (birthday == null) return null;
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthday)) {
			throw new IllegalArgumentException("出生时间大于当前时间!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;// 注意此处，如果不加1的话计算结果是错误的
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthday);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				} else {
					// do nothing
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		} else {
			// monthNow<monthBirth
			// donothing
		}
		return age;
	}

	/**
	 * 排序
	 * @param list
	 * @param order
	 */
	public static void sort(List<Date> list, String order) {
		if (list == null || list.size() == 0)
			return;
		if (order == null)
			order = ASC;
		Collections.sort(list);
		if (DESC.equals(order)) {
			List<Date> result = new ArrayList<Date>();
			for (int i = list.size(); i > 0; i--) {
				result.add(list.get(i - 1));
			}
			list.clear();
			list.addAll(result);
		}
	}

	/**
	 * 排序
	 * @param list
	 */
	public static void sort(List<Date> list) {
		sort(list, ASC);
	}

	public static boolean isBeforeToday(Date arg) {
		return arg.before(getTodayStartTime());
	}

	public static boolean isToday(Date arg) {
		arg = parse(DateUtil.DATE, format(DateUtil.DATE, arg));
		Date today = parse(DateUtil.DATE, format(DateUtil.DATE, new Date()));
		return arg.equals(today);
	}
	public static boolean isAfterToday(Date arg) {
		return arg.after(parse(format("yyyy-MM-dd", new Date())
					+ " 23:59:59"));
	}

	/**
	 * 当天+day
	 * @param day
	 * @return
	 */
	public static Date getDayAfter(int day) {
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, day);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MINUTE, 0);
		Date monday = currentDate.getTime();
		return monday;
	}
	/**
	 * 获得本周一的日期
	 * @return
	 */
	public static Date getMondayOFWeek() {
		return getMondayOFWeek(0);
	}
	/**
	 * 获得(本周一+day)的日期
	 * 
	 * @return
	 */
	public static Date getMondayOFWeek(int day) {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + day);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MINUTE, 0);
		Date monday = currentDate.getTime();
		return monday;
	}


	/**
	 * 获取当月第一天
	 * @return
	 */
	public static Date getFirstDayOfMonth() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.set(Calendar.HOUR_OF_DAY, 0);
		lastDate.set(Calendar.SECOND, 0);
		lastDate.set(Calendar.MINUTE, 0);
		return lastDate.getTime();
	}

	/**
	 * 获得本年第一天的日期
	 * @return
	 */
	public static Date getCurrentYearFirst() {
		int yearPlus = getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MINUTE, 0);
		Date yearDay = currentDate.getTime();
		return yearDay;
	}

	// 获得当前日期与本周日相差的天数
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		// 2011.12.25 ls add
		if (dayOfWeek == 0) {// 此处为本人添加
			dayOfWeek = 7;
		}
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}
	
	private static int getYearPlus() {
		Calendar cd = Calendar.getInstance();
		int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 获得当天是一年中的第几天
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		if (yearOfNumber == 1) {
			return -MaxYear;
		} else {
			return 1 - yearOfNumber;
		}
	}
	
	/**
	 * 获取当天开始时时间
	 * @return
	 */
	public static Date getTodayStartTime() {
		return parse(format(DATE, new Date()) + " 0:00:00");
	}
	
	//以下方法都是用来获取从某个时间点以后多长时间。
	public static Date getDayAfterDay(int day) {
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, day);
		Date monday = currentDate.getTime();
		return monday;
	}
	public static Date getDayAfterDay(Date date, int day) {
		GregorianCalendar currentDate = new GregorianCalendar();
		if(date != null) {
			currentDate.setTimeInMillis(date.getTime());
		}
		currentDate.add(GregorianCalendar.DATE, day);
		Date monday = currentDate.getTime();
		return monday;
	}
	public static Date getDayAfterMonth(Date date, int month) {
		GregorianCalendar currentDate = new GregorianCalendar();
		if(date != null) {
			currentDate.setTimeInMillis(date.getTime());
		}
		currentDate.add(GregorianCalendar.MONTH, month);
		Date monday = currentDate.getTime();
		return monday;
	}
	public static Date getDayAfterHour(Date date, int hour) {
		GregorianCalendar currentDate = new GregorianCalendar();
		if(date != null) {
			currentDate.setTimeInMillis(date.getTime());
		}
		currentDate.add(GregorianCalendar.HOUR_OF_DAY, hour);
		Date monday = currentDate.getTime();
		return monday;
	}
	public static Date getDayAfterMinute(Date date, int minute) {
		GregorianCalendar currentDate = new GregorianCalendar();
		if(date != null) {
			currentDate.setTimeInMillis(date.getTime());
		}
		currentDate.add(GregorianCalendar.MINUTE, minute);
		Date monday = currentDate.getTime();
		return monday;
	}
	public static Date getDayAfterSecond(Date date, int second) {
		GregorianCalendar currentDate = new GregorianCalendar();
		if(date != null) {
			currentDate.setTimeInMillis(date.getTime());
		}
		currentDate.add(GregorianCalendar.SECOND, second);
		Date monday = currentDate.getTime();
		return monday;
	}
	
	
	//以下方法都是用来获取从某个时间点往前推多长时间。
	public static Date getDayBeforDay(int day) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - day );
		return c.getTime();
	}
	public static Date getDayBeforDay(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - day );
		return c.getTime();
	}
	public static Date getDayBeforMonth(Date date, int month) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - month);
		return c.getTime();
	}
	public static Date getDayBeforHour(Date date, int hour) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) - hour);
		return c.getTime();
	}
	public static Date getDayBeofrMinute(Date date, int minute) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) - minute);
		return c.getTime();
	}
	public static Date getDayBeforSecond(Date date, int second) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.SECOND, c.get(Calendar.SECOND) - second);
		return c.getTime();
	}
	
	
	
	
	
	public static Date parseForVad(String pattern, String source) throws Exception {
		try {
			if (!StringUtils.hasText(source)) return null;
			if (!StringUtils.hasText(pattern))
				return dateTimeFormatter.parse(source);
			return new SimpleDateFormat(pattern).parse(source);
		} catch (ParseException e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * @param date
	 * @param days 0:所有时间都转换为 几小时前 几天前等 ;days>0  (当天-days)显示几小时前，非(当天-days)的显示日期
	 * @return
	 */
	public static String parseShortTime(Date date,Integer days,String formatter){
		String returnStr = "";
		if(date!=null){
			Date today = new Date();
		    Date begin=DateUtil.parse(DateUtil.DATETIME, DateUtil.format(DateUtil.DATETIME, date));
		    Date end = DateUtil.parse(DateUtil.DATETIME, DateUtil.format(DateUtil.DATETIME, today));
		    long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒

		    long year=between/(24*3600*30*12);
		    long month=between/(24*3600*30);
		    long day=between/(24*3600);
		    long hour=between%(24*3600)/3600;
		    long minute=between/60;
		    long second=between;
		    if(days==0){
		    	if(year!=0){
			    	return year+"年前";
			    }
			    if(month!=0){
			    	return month+"月前";
			    }
			    if(day!=0){
			    	return day+"天前";
			    }
			    if(hour!=0){
			    	return hour+"小时前";
			    }
			    if(minute!=0){
			    	return minute+"分钟前";
			    }
			    if(second!=0){
			    	return second+"秒前";
			    }
			    return "1秒前";
		    }else{
		    	if(day>days){
		    		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		    		returnStr = sdf.format(date); 
		    	}else{
		    		if(day!=0){
				    	return day+"天前";
				    }
				    if(hour!=0){
				    	return hour+"小时前";
				    }
				    if(minute!=0){
				    	return minute+"分钟前";
				    }
				    if(second!=0){
				    	return second+"秒前";
				    }
				    return "1秒前";
		    	}
		    }
			return returnStr;
		}else{
			return returnStr;
		}
	}
	
	/**
	 * 判读是否是周五
	 * @param date
	 * @return
	 * @author likang 
	 * @date 2016-10-19 上午10:59:40
	 */
	public static boolean isFriday(String date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(DateUtil.parse(date)); 
		int week=cal.get(Calendar.DAY_OF_WEEK)-1;
		if(week==5){
			return true;
		}
		return false;
	}
	/**
	 * 判断是否是周一
	 * @param date
	 * @return
	 * @author likang 
	 * @date 2016-10-19 上午10:59:45
	 */
	public static boolean isMonday(String date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(DateUtil.parse(date)); 
		int week=cal.get(Calendar.DAY_OF_WEEK)-1;
		if(week==1){
			return true;
		}
		return false;
	}
	/**
	 * 判读是否是周四
	 * @param date
	 * @return
	 * @author likang 
	 * @date 2016-10-19 上午10:59:52
	 */
	public static boolean isThursday(String date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(DateUtil.parse(date)); 
		int week=cal.get(Calendar.DAY_OF_WEEK)-1;
		if(week==4){
			return true;
		}
		return false;
	}
	/**
	 * 判读是否是本月第一个工作日
	 * @param date
	 * @return
	 * @author likang 
	 * @date 2016-10-19 上午11:00:01
	 */
	public static boolean isFirstWeekDay(String date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(DateUtil.parse(date)); 
		int day = cal.get(Calendar.DAY_OF_MONTH);//获致是本月的第几天地, 1代表星期天...7代表星期六
		int totalDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		System.out.println(w);
		System.out.println(day);
		if(!isWeekend(DateUtil.parse(date))&&day==1){
			return true;
		}else if(day >1 && day<5 && !isWeekend(DateUtil.parse(date)) && w==1){
			return true;
		}
		return false;
	}
	/**
	 * 判读是否是本月最后一个工作日
	 * @param date
	 * @return
	 * @author likang 
	 * @date 2016-10-19 上午11:00:06
	 */
	public static boolean isLastWeekDay(String date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(DateUtil.parse(date)); 
		int day = cal.get(Calendar.DAY_OF_MONTH);//获致是本月的第几天地, 1代表星期天...7代表星期六
		int totalDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);//当月第几天
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;//周几
		int wm = cal.get(Calendar.WEEK_OF_MONTH);//当月第几周
		if(!isWeekend(DateUtil.parse(date))&&(totalDay-day)==0){
			return true;
		}else if(isFriday(date) && (totalDay-day)<=2){
			return true;
		}
		return false;
	}
	
	public static boolean isMonthOfLastFriday(String date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(DateUtil.parse(date)); 
		int day = cal.get(Calendar.DAY_OF_MONTH);//获致是本月的第几天
		int totalDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		if(isFriday(date)&&(totalDay-day)<7){
			return true;
		}
		return false;
	}
	
	/*public static void main(String[] args) {
		Calendar cal=Calendar.getInstance();
		cal.setTime(DateUtil.parse("2016-04-22 12:12:12")); 
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int i = 1;
		while(cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY){
		cal.set(Calendar.DAY_OF_MONTH, i++);
		}
		Date firstMonday = cal.getTime();
		System.out.println(firstMonday.toString());
	}*/
	
	public static void main(String[] args) {
		Calendar cal=Calendar.getInstance();
		String dateStr = "2016-01-22 12:12:12";
		cal.setTime(DateUtil.parse(dateStr)); 
		int day = cal.get(Calendar.DAY_OF_MONTH);//获致是本月的第几天地, 1代表星期天...7代表星期六
		int totalDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);//当月第几天
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;//周几
		int wm = cal.get(Calendar.WEEK_OF_MONTH);//当月第几周
		if(!isWeekend(DateUtil.parse(dateStr))&&(totalDay-day)==0){
			System.out.println("123123123");
		}else if(isFriday(dateStr) && (totalDay-day)<=2){
			System.out.println("111");
		}
		
	}
	/**
	 * 获得当前时间所在月份的上个月的最后一天所在日期
	 * @author likang	
	 * @return
	 * 2016年3月28日 下午1:52:26
	 */
	public static String getLastMonthDay() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, - 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//		System.out.println( sf.format(calendar.getTime()));
		return sf.format(calendar.getTime());
	}
	/**
	 * 获得当前时间所在月份的上一周的最后一天所在日期
	 * @author likang	
	 * @return
	 * 2016年3月28日 下午1:52:39
	 */
	public static String getLastWeekDay() {
//		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
//		Calendar calendar=Calendar.getInstance();
//		calendar.add(Calendar.WEEK_OF_YEAR, -1);
//		Date dateFrom = calendar.getTime();
//		return sf.format(dateFrom);
		 Calendar cal = Calendar.getInstance();
		    cal.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
		    cal.add(Calendar.DATE, -1*7);
		    cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		   String sunday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		   return sunday;
	}
	
	/**
	 * 获得当前时间所在月份的昨日所在日期
	 * @author likang	
	 * @return
	 * 2016年3月28日 下午1:52:51
	 */
	public static String getLastyesterDay(){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		//一天
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Date dateFrom = calendar.getTime();
		return sf.format(dateFrom);
	}

	/**
	 * 比较日期
	 * @author likang	
	 * @param DATE1
	 * @param DATE2
	 * @return
	 * 2016年5月25日 下午1:01:15
	 */
	public static int compare_date(String DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	
	
//	字母  日期或时间元素  表示  示例  
//	G  Era 标志符  Text  AD  
//	y  年  Year  1996; 96  
//	M  年中的月份  Month  July; Jul; 07  
//	w  年中的周数  Number  27  
//	W  月份中的周数  Number  2  
//	D  年中的天数  Number  189  
//	d  月份中的天数  Number  10  
//	F  月份中的星期  Number  2  
//	E  星期中的天数  Text  Tuesday; Tue  
//	a  Am/pm 标记  Text  PM  
//	H  一天中的小时数（0-23）  Number  0  
//	k  一天中的小时数（1-24）  Number  24  
//	K  am/pm 中的小时数（0-11）  Number  0  
//	h  am/pm 中的小时数（1-12）  Number  12  
//	m  小时中的分钟数  Number  30  
//	s  分钟中的秒数  Number  55  
//	S  毫秒数  Number  978  
//	z  时区  General time zone  Pacific Standard Time; PST; GMT-08:00  
//	Z  时区  RFC 822 time zone  -0800
}
