/**
 * 
 */
package com.jin.calendar.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author zhai
 *
 * 2016-10-19 上午9:25:56
 */
public class FormartDatetime {
	
	/**
	 * 
	 * @param datetime
	 * @return
	 */
	public static String getDate(Date datetime){
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
		String date = sDateFormat.format(datetime);
		return date;
	}
}
