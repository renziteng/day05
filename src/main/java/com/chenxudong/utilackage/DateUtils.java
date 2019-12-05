package com.chenxudong.utilackage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateUtils {
	
	// date 日期  Calendar 日历
	public static int getAge(Calendar birth) {
		//当前日期的年�?
		Calendar now = formateDateToCalendar(new Date());
		int j = now.get(Calendar.YEAR);
		//生日年份
		int i = birth.get(Calendar.YEAR);
		return j - i;
	}
	
	
	public static int getAge(String birth) {
		//当前的年�?
		Calendar now = formateDateToCalendar(new Date());
		int j = now.get(Calendar.YEAR);
		//生日年份
		Date date = formateStringToDate(birth);
		Calendar calendar = formateDateToCalendar(date);
		int i = calendar.get(Calendar.YEAR);
		return j - i;
	}
	
	public static Calendar formateDateToCalendar(Date date) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		return instance;
	}
	
	public static Date formateStringToDate(String str) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			 date = dateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static int getFutureDays(String future) {
		
		Date date = new Date();
		long time = date.getTime();
		
		Date formateStringToDate = formateStringToDate(future);
		long time2 = formateStringToDate.getTime();
		
		long dt = time2 - time ;
		
		int day = (int) (dt/1000/60/60/24);
		return day;
	}
	/**
	 * 判断给定日期是否是当�?
	 * @param str
	 * @return
	 */
	public static boolean isToday(String str) {
		/*
		 * 获取日期的年 �? �?
		 * Calendar instance = Calendar.getInstance();
		instance.setTime(new Date());
		instance.get(Calendar.YEAR);
		instance.get(Calendar.MONTH);
		instance.get(Calendar.DAY_OF_MONTH);*/
		
		int futureDays = getFutureDays(str);
		if(futureDays==0) {
			return true;
		}
		return false;
	}
	/**
	 * 判断当前给定的日期是否在本周之内
	 * @return
	 */
	public static boolean isDayInWeek(String date) {
		
		Calendar instance = Calendar.getInstance();
		instance.setTime(new Date());
		int year = instance.get(Calendar.YEAR);
		int month = instance.get(Calendar.MONTH);
		int dayOfWeek = instance.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		
		Date stringToDate = formateStringToDate(date);
		Calendar dateToCalendar = formateDateToCalendar(stringToDate);
		int year1 = dateToCalendar.get(Calendar.YEAR);
		int month1 = dateToCalendar.get(Calendar.MONTH);
		int dayOfWeek1 = dateToCalendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		
		if(year==year1&&month==month1&&dayOfWeek==dayOfWeek1) {
			return true;
		}
		return false;
	}
	
	/***
	 * 给定时间对象，初始化到该年初�?1�?1�?0�?0�?0�?0毫秒
	 */
	public static void  setInitDate(String str) {
		Date stringToDate = formateStringToDate(str);
		Calendar dateToCalendar = formateDateToCalendar(stringToDate);
		dateToCalendar.set(Calendar.MONTH, 1);
		dateToCalendar.set(Calendar.DAY_OF_YEAR, 1);
		dateToCalendar.set(Calendar.HOUR, 0);
		dateToCalendar.set(Calendar.MINUTE, 0);
		dateToCalendar.set(Calendar.SECOND, 0);
		dateToCalendar.set(Calendar.MILLISECOND, 0);
		
		System.out.println(dateToCalendar.getTime());
		
	}
	
		/**方法1：根据传入的日期获取年龄*/
		public static int getAge(Date date){
			Calendar ca = Calendar.getInstance();
			int now = ca.get(Calendar.YEAR);
			ca.setTime(date);
			int dateYear = ca.get(Calendar.YEAR);
			return now - dateYear;
		}
		
		/**方法2：根据传入的参数获取该日期的月初日期，例如给定的日期为�??2019-09-19 19:29:39”，返回�?2019-09-01 00:00:00�?*/
		public static Date getDateByMonthInit (Date date) {
			Calendar ca = Calendar.getInstance();
			ca.setTime(date);
			int year = ca.get(Calendar.YEAR);
			int mouth = ca.get(Calendar.MONTH);
			ca.clear();
			ca.set(Calendar.YEAR, year);
			ca.set(Calendar.MONTH, mouth);
			ca.set(Calendar.HOUR, 0);
			return ca.getTime();
		}
		
		/**方法3 :根据传入的参数获取该日器的月末日期，例如给定的日期为�?2019-09-19 19:29:39”，返回�?2019-09-30 23:59:59”，注意月大月小以及闰年�?*/
		public static Date getDateByMonthLast(Date date){
			Calendar ca = Calendar.getInstance();
			ca.setTime(date);
			int year = ca.get(Calendar.YEAR);
			int mouth = ca.get(Calendar.MONTH);
			ca.clear();
			ca.set(Calendar.HOUR, 0);
			ca.set(Calendar.YEAR, year);
			ca.set(Calendar.MONTH, mouth+1);
			Long time = ca.getTime().getTime();
			return new Date(time - 1);
		}
		
		/**方法4：求未来日期离今天还剩的天数*/
		public static int getDaysByFuture (Date future) {
			Date now = new Date();
			return (int)((future.getTime() - now.getTime())/(60*60*24*1000));
		}
		
		/**方法5：求过去日期离今天过去的天数*/
		public static int getDaysByDeparted (Date departed){
			Date now = new Date();
			return (int)((now.getTime() - departed.getTime())/(60*60*24*1000));
		}
		
		public static void main(String[] args) {
			for (int i = 0; i < 100; i++) {
				Date randomDate = getRandomDate(2019);
				System.out.println(new SimpleDateFormat("yyyy-MM-dd:hh-mm-ss").format(randomDate));
			}
		}
		
		/** 随机�?个时�?  param:int类型的年份，随机日期在该年份之后*/
		public static Date getRandomDate(int year){
			Calendar now = Calendar.getInstance();
			int nowYear = now.get(Calendar.YEAR);
			int nowMouth = now.get(Calendar.MONTH)+1;
			int nowDay = now.get(Calendar.DATE);
			
			Random rd = new Random();
			int newYear = rd.nextInt(nowYear - year+1)+year;
			int newMonth = 0;
			if(newYear == year){
				newMonth = rd.nextInt(nowMouth);
			}else{
				newMonth = rd.nextInt(13);
			}
			//newMonth = newMonth==0?newMonth+1:newMonth;
			int newDay = 0;
			if(newYear == year && newMonth==nowMouth){
				newDay = rd.nextInt(nowDay+1);
			}else{
				if(newMonth!=4){
					newDay = rd.nextInt(31);
				}else{
					newDay = rd.nextInt(29);
				}
			}
			newDay = newDay==0?newDay+1:newDay;
			Date date = new Date(rd.nextInt(1000*60*60*24));
			now.setTime(date);
			now.set(newYear, newMonth, newDay);
			return now.getTime();
		}

}
