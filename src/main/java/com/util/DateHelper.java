package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.time.FastDateFormat;

public abstract class DateHelper {

	public static String convertCustomDateTime(String strDate) {
		String convertDate = strDate;
		if (convertDate.lastIndexOf(".") != -1)
			convertDate = convertDate.substring(0, convertDate.lastIndexOf("."));
		return convertDate.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
	}
		
	
	public static String strDate() {
		FastDateFormat f = FastDateFormat.getInstance("yyyyMMddHHmmss", new Locale("ko_KR"));
		return f.format(new Date());
	}
	public static String strHourMinute() {
		FastDateFormat f = FastDateFormat.getInstance("HHmm", new Locale("ko_KR"));
		return f.format(new Date());
	}
	
	public static String strYear() {
		FastDateFormat f = FastDateFormat.getInstance("yyyy", new Locale("ko_KR"));
		return f.format(new Date());
	}
	
	public static String strMonth() {
		FastDateFormat f = FastDateFormat.getInstance("MM", new Locale("ko_KR"));
		return f.format(new Date());
	}
	
	public static String strDay() {
		FastDateFormat f = FastDateFormat.getInstance("dd", new Locale("ko_KR"));
		return f.format(new Date());
	}
	
	public static String strHour() {
		FastDateFormat f = FastDateFormat.getInstance("HH", new Locale("ko_KR"));
		return f.format(new Date());
	}
	
	public static String strMinute() {
		FastDateFormat f = FastDateFormat.getInstance("mm", new Locale("ko_KR"));
		return f.format(new Date());
	}
	
	public static String strSecond() {
		FastDateFormat f = FastDateFormat.getInstance("ss", new Locale("ko_KR"));
		return f.format(new Date());
	}
	
	public static boolean isBetween_HourMinute(String start, String end) {
		
		FastDateFormat f = FastDateFormat.getInstance("HHmm", new Locale("ko_KR"));		
		String current = f.format(new Date());
		int cur = Integer.parseInt(current);
		int st = Integer.parseInt(start);
		int ed = Integer.parseInt(end);
		if(st<ed) {
			if(st<cur && ed>cur) {return true;}		
			else return false;
		}else {
			if(ed<cur && st>cur) return false;			
		}		
		return true;
	}
	
	public static String strMilliseconds() {
		/*FastDateFormat f = FastDateFormat.getInstance("S", new Locale("ko_KR"));
		return f.format(new Date());*/
		
		long time = System.currentTimeMillis(); 		
		String strDT =	 DateHelper.MILLISECOND_FORMAT.get().format(new Date(time)); 
		return strDT;
	}
		
	
	private static final ThreadLocal<SimpleDateFormat> MILLISECOND_FORMAT = new ThreadLocal<SimpleDateFormat>() {
	    @Override
	    protected SimpleDateFormat initialValue() {
	    	return new SimpleDateFormat("SSS");
	    }
	};
	
	private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT = new ThreadLocal<SimpleDateFormat>() {
	    @Override
	    protected SimpleDateFormat initialValue() {
	    	return new SimpleDateFormat("yyyyMMddHHmmss");
	    }
	};

	private static final ThreadLocal<SimpleDateFormat> DAY_FORMAT = new ThreadLocal<SimpleDateFormat>() {
	    @Override
	    protected SimpleDateFormat initialValue() {
	    	return new SimpleDateFormat("yyyyMMdd");
	    }
	};

	
	
	/**
	 * 특정 날짜에 대한 요일 번호(자바 캘린더)를 구함
	 * @return 요일 Index : 1(일) ~ 7(토)
	 * @author dsnoh
	 */
	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayNum = cal.get(Calendar.DAY_OF_WEEK) ;
	    return dayNum;
	}
	
	/**
	 * 특정 날짜에 대한 요일 번호(DB 저장용)를 구함
	 * @return 요일 Index : 1(월) ~ 7(일)
	 * @author dsnoh
	 */
	public static int getWeekNmbr(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayNum = cal.get(Calendar.DAY_OF_WEEK) ;		
		if (dayNum == 1) {
			dayNum = 7;
		} else {
			dayNum--;
		}
	    return dayNum;
	}
	
	/**
	 * 
	 * @param period
	 * @param date : yyyymmdd 형식
	 * @return
	 */
	public static Date getAfterFiveMinDate(int period , String date) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
		Date dt = new Date();
		try {
			dt = dateFormatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		cal.setTime(dt);

		cal.set(Calendar.SECOND, 0); // '초' 0으로 셋팅
		cal.set(Calendar.MILLISECOND, 0); // '밀리초' 0으로 셋팅

		// 5분단위로 수정
		int min = cal.get(Calendar.MINUTE);
		cal.add(Calendar.MINUTE, -(min % 5) + (5 * period));

		return cal.getTime();
	}

	public static String getDate() {
//		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");//26
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyyMMddHHmmss");//14
		return dayFormat.format(System.currentTimeMillis());
	}
}
