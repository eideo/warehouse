package com.fh.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import com.hp.hpl.sparta.xpath.ThisNodeTest;


public class DateUtil {
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat(
			"yyyy-MM-dd");
	
	private final static DateFormat sdfDays = new SimpleDateFormat(
	"yyyyMMdd");
	private final static  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	private final static DateFormat ISO_8601 =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	static {
		  
        /* Create Date Formats */
        final String[] possibleDateFormats = {
                /* RFC 1123 with 2-digit Year */"EEE, dd MMM yy HH:mm:ss z",
                /* RFC 1123 with 4-digit Year */"EEE, dd MMM yyyy HH:mm:ss z",   
                /* RFC 1123 with no Timezone */"EEE, dd MMM yy HH:mm:ss",   
                /* Variant of RFC 1123 */"EEE, MMM dd yy HH:mm:ss",   
                /* RFC 1123 with no Seconds */"EEE, dd MMM yy HH:mm z",   
                /* Variant of RFC 1123 */"EEE dd MMM yyyy HH:mm:ss",   
                /* RFC 1123 with no Day */"dd MMM yy HH:mm:ss z",   
                /* RFC 1123 with no Day or Seconds */"dd MMM yy HH:mm z",   
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ssZ",   
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss'Z'",   
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:sszzzz",   
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss z",   
                /* ISO 8601 */"yyyy-MM-dd'T'HH:mm:ssz",   
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss.SSSz",   
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HHmmss.SSSz",   
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss",   
                /* ISO 8601 w/o seconds */"yyyy-MM-dd'T'HH:mmZ",   
                /* ISO 8601 w/o seconds */"yyyy-MM-dd'T'HH:mm'Z'",   
                /* RFC 1123 without Day Name */"dd MMM yyyy HH:mm:ss z",   
                /* RFC 1123 without Day Name and Seconds */"dd MMM yyyy HH:mm z",   
                /* Simple Date Format */"yyyy-MM-dd",   
                /* Simple Date Format */"MMM dd, yyyy"};
  
      
    }
 
	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays(String date){
		if(date==null){
			return sdfDays.format(new Date());
		}else{
			return sdfDays.format(DateUtil.fomatDate(date, DateUtil.ISO_8601));
		}
		
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}
	/**
	 * 获取yyyy-MM-dd'T'HH:mm:ssZ格式
	 * 
	 * @return
	 */
	public static String getTimeISO_8601(Date date) {
		return ISO_8601.format(date);
	}
	
	
	
	
	
	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	public static Date fomatDate(String date,DateFormat fmt) {
	//	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long aa=0;
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	  /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
        Date date = getAfterDayDate(daysInt);       
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static Date getAfterDayDate(int days) {   	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, days); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        
        return date;
    }
    
    
    
    
    
    
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        
        return dateStr;
    }
    
    
//    public static String getIsoDate( Date date )
//    {
//        SimpleDateFormat  dateToIsoDateString = new SimpleDateFormat( ISO_8601_DATE_FORMAT );
//        TimeZone tz = TimeZone.getTimeZone("UTC");
//        dateToIsoDateString.setTimeZone( tz );
//        return dateToIsoDateString.format( date );
//    }
//
//    // this will return a date with GMT timezone
//    public static Date getDateFromIsoDateString( String iso8601date )
//    {
//        DateTimeFormatter jodaParser = ISODateTimeFormat.dateTimeNoMillis();
//        return jodaParser.parseDateTime( iso8601date ).toDate();
//    }
    
  public static String getCurrentDateOfUtc(){
	     ZoneId australia = ZoneId.of("Pacific/Auckland");
	     LocalDateTime localtDateAndTime =LocalDateTime.now();
	     ZonedDateTime dateAndTimeInSydney = ZonedDateTime.of(localtDateAndTime, australia );
	     ZonedDateTime utcDate = dateAndTimeInSydney.withZoneSameInstant(ZoneOffset.UTC);
	     return utcDate.format(dtf);
  }
    
    public static void main(String[] args) {
    	//System.out.println(getDays());
    	//System.out.println(getAfterDayWeek("3"));
    	
//    	TimeZone tz = TimeZone.getTimeZone("UTC");
//    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
//    	df.setTimeZone(tz);
//    	String nowAsISO = df.format(new Date());
//    	System.out.println(nowAsISO);
    	  //2016-10-13T02:32:58Z
//       DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
//       TemporalAccessor accessor = timeFormatter.parse("2015-10-27T16:22:27.605-07:00");
//
//       Date date = Date.from(Instant.from(accessor));
     System.out.println( DateUtil.getDays(null));
//      
    
     // Date and time with timezone in Java 8
     ZoneId australia = ZoneId.of("Australia/Sydney");
     String str = "2015-01-05 17:00";
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
     LocalDateTime localtDateAndTime =LocalDateTime.now();// LocalDateTime.parse(str, formatter);
     ZonedDateTime dateAndTimeInSydney = ZonedDateTime.of(localtDateAndTime, australia );

     System.out.println("Current date and time in a particular timezone : " + dateAndTimeInSydney);

     ZonedDateTime utcDate = dateAndTimeInSydney.withZoneSameInstant(ZoneOffset.UTC);

     System.out.println("Current date and time in UTC : " + utcDate);
    	
    	
    }

}
