package com.py.shijijingying.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class DateUtil {
	public static final String DATA_FORMAT_STR = "yyyy-MM-dd";
	public static final String DATA_TIME_FORMAT_STR = "yyyy-MM-dd HH:mm";
	public static final String DATA_STAND_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

	
	public static boolean compare(Date date,Date startDate,Date endDate) throws ParseException{
		//Date类的一个方法，如果a早于b返回true，否则返回false
		if(date.before(endDate) && startDate.before(date))
			return true;
		else
			return false;
	}
	
	/**
	 * 时间字符串转DATE对象
	 */
	public static Date parseDateFromString(String input, String simpleformat) {
		if (StringUtils.isBlank(simpleformat)) {
			simpleformat = DATA_FORMAT_STR;
		}
		if (StringUtils.isBlank(input)) {
			return null;
		}
		try {
			DateFormat format = new SimpleDateFormat(simpleformat);
			Date date = format.parse(input);
			return date;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	/**
	 * 长整形时间转时间字符串
	 */
	public static String formatTime(long input, String simpleformat) {
		if (StringUtils.isBlank(simpleformat)) {
			simpleformat = DATA_FORMAT_STR;
		}
		SimpleDateFormat formate = new SimpleDateFormat(simpleformat);
		return formate.format(input);
	}

	/**
	 * 时间转字符串
	 */
	public static String formatTime(Date input, String simpleformat) {
		if (StringUtils.isBlank(simpleformat)) {
			simpleformat = DATA_FORMAT_STR;
		}
		SimpleDateFormat formate = new SimpleDateFormat(simpleformat);
		return formate.format(input);
	}

	public static String getNow() {
		return formatTime(new Date(), DATA_STAND_FORMAT_STR);
	}

	/**
	 * 获取当前7天后的具体日期
	 */
	public static Date getAfter7DaysDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		String newDateStr = formatTime(calendar.getTime(), DATA_FORMAT_STR) + " 23:59:59";

		return parseDateFromString(newDateStr, DATA_STAND_FORMAT_STR);
	}

	/**
	 * 差多少月份
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getMonth(Date start, Date end) {
		if (start.after(end)) {
			Date t = start;
			start = end;
			end = t;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(start);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		Calendar temp = Calendar.getInstance();
		temp.setTime(end);
		temp.add(Calendar.DATE, 1);

		int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

		if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month + 1;
		} else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month;
		} else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
			return year * 12 + month;
		} else {
			return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
		}
	}

	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static String getDateSuffix(Date createTime) {
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		//long ns = 1000;// 一秒钟的毫秒数
		// 获得两个时间的毫秒时间差异
		long diff = new Date().getTime() - createTime.getTime();
		long day = diff / nd;// 计算差多少天
		long hour = diff % nd / nh;// 计算差多少小时
		long min = diff % nd % nh / nm;// 计算差多少分钟

		if (day > 0) {
			if (day < 10) {
				return day + "天前";
			} else {
				return DateUtil.formatTime(createTime, DATA_FORMAT_STR);
			}
		} else if (hour > 0) {
			return hour + "小时前";
		} else {
			return (min + 1) + "分钟前";
		}

	}

	public static boolean isValidDate(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if (str == null)
			return false;
		try {
			Date date = (Date) formatter.parse(str);
			return str.equals(formatter.format(date));
		} catch (Exception e) {
			return false;
		}
	}

	public static Date getDateOfPreviousMonth(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MONTH, -1);
		ca.add(Calendar.DAY_OF_YEAR, 1);

		Date dateOfPreviousMonth = ca.getTime();

		return dateOfPreviousMonth;
	}

	public static Date getDateofPreviousHalfYear(Date date){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MONTH,-6);

		Date dateOfPreviousHalfYear = ca.getTime();

		return dateOfPreviousHalfYear;
	}

	public static Date getDateofPreviousThreeMonth(Date date){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MONTH,-3);

		Date dateOfPreviousHalfYear = ca.getTime();

		return dateOfPreviousHalfYear;
	}

	public static Date getDateOfPreviousYear(Date date,int previousYearNum) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MONTH, - previousYearNum * 12 + 1);

		Date dateOfPreviousYear = ca.getTime();

		return dateOfPreviousYear;
	}

	/**
     * 获取某一时间段特定星期一的日期
     * @param dateFrom 开始时间
     * @param dateEnd 结束时间
     * @param weekDays 星期
     * @return 返回时间数组
     */
    public static String[] getDates(String dateFrom, String dateEnd, String weekDays) {
        long time = 1l;
        long perDayMilSec = 24 * 60 * 60 * 1000;
        List<String> dateList = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //需要查询的星期系数
        String strWeekNumber = weekForNum(weekDays);
        try {
            dateFrom = sdf.format(sdf.parse(dateFrom).getTime() - perDayMilSec);
            while (true) {
                    time = sdf.parse(dateFrom).getTime();
                    time = time + perDayMilSec;
                    Date date = new Date(time);
                    dateFrom = sdf.format(date);
                    if (dateFrom.compareTo(dateEnd) <= 0) {
                        //查询的某一时间的星期系数
                        Integer weekDay = dayForWeek(date);
                        //判断当期日期的星期系数是否是需要查询的
                        if (strWeekNumber.indexOf(weekDay.toString())!=-1) {
                            dateList.add(dateFrom);
                        }
                    } else {
                        break;
                    }
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        String[] dateArray = new String[dateList.size()];
        dateList.toArray(dateArray);
        return dateArray;
    }

    //等到当期时间的周系数。星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
    public static Integer dayForWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 得到对应星期的系数  星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
     * @param weekDays 星期格式  星期一|星期二
     */
    public static String weekForNum(String weekDays){
        //返回结果为组合的星期系数
        String weekNumber = "";
        //解析传入的星期
        if(weekDays.indexOf("|")!=-1){//多个星期数
            String []strWeeks = weekDays.split("\\|");
            for(int i=0;i<strWeeks.length;i++){
                weekNumber = weekNumber + "" + getWeekNum(strWeeks[i]).toString();
            }
        }else{//一个星期数
            weekNumber = getWeekNum(weekDays).toString();
        }

        return weekNumber;

    }

    //将星期转换为对应的系数  星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
    public static Integer getWeekNum(String strWeek){
        Integer number = 1;//默认为星期日
        if("星期日".equals(strWeek)){
            number = 1;
        }else if("星期一".equals(strWeek)){
            number = 2;
        }else if("星期二".equals(strWeek)){
            number = 3;
        }else if("星期三".equals(strWeek)){
            number = 4;
        }else if("星期四".equals(strWeek)){
            number = 5;
        }else if("星期五".equals(strWeek)){
            number = 6;
        }else if("星期六".equals(strWeek)){
            number = 7;
        }else{
            number = 1;
        }
        return number;
    }
    /**
     * 计算一段时间内每个月一号的日期
     */
    public static String[] getMonths(Date dateFrom, Date dateEnd){
    	List<String> dateList=new ArrayList<>();
		Date d1= dateFrom;
		Date d2 = dateEnd;//定义结束日期
		Calendar dd = Calendar.getInstance();//定义日期实例
    	dd.setTime(d1);//设置日期起始时间
    	while(dd.getTime().before(d2)){//判断是否到结束日期
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	dd.set(Calendar.DAY_OF_MONTH, 1);
	    	String str=sdf.format(dd.getTime());
	    	dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
	    	dateList.add(str);
    	}
		//定义起始日期
		String[] dateArray = new String[dateList.size()];
        dateList.toArray(dateArray);
		return dateArray;
    }
    /**
     * 计算一段时间内每个月第一天的日期
     */
    public static String[] getOneOfMonths(Date dateFrom, Date dateEnd){
    	List<String> dateList=new ArrayList<>();
		Date d1= dateFrom;
		Date d2 = dateEnd;//定义结束日期
		Calendar dd = Calendar.getInstance();//定义日期实例
    	dd.setTime(d1);//设置日期起始时间
    	while(dd.getTime().before(d2)){//判断是否到结束日期
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	    	dd.set(Calendar.DAY_OF_MONTH, 1);
	    	String str=sdf.format(dd.getTime());
	    	dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
	    	dateList.add(str);
    	}
		//定义起始日期
		String[] dateArray = new String[dateList.size()];
        dateList.toArray(dateArray);
		return dateArray;
    }

	public static int formatTime(long timeBtw) {
		long oneHourTimeMillis=3600*1000;
		return (int) (timeBtw/oneHourTimeMillis);
	}

	public static int formatTimeBtwminutes(long timeBtw) {
		long oneHourTimeMillis=60*1000;
		return (int) (timeBtw/oneHourTimeMillis);
	}
	
	public static Date getDateAddMonth(Date date,Integer month){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MONTH,month);

		Date dateOfPreviousHalfYear = ca.getTime();

		return dateOfPreviousHalfYear;
	}
	
	public static Date getDateAddDay(Date date,Integer day){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DAY_OF_MONTH,day);

		Date dateOfPreviousHalfYear = ca.getTime();

		return dateOfPreviousHalfYear;
	}
}
