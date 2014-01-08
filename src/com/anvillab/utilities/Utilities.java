package com.anvillab.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
	
	public static String getFormattedDate(Date lastUpdatedTime) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(lastUpdatedTime);
	
	}
	
	public static Date getFormattedDateFromString(String lastUpdatedTime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		
		try {
			date = dateFormat.parse(lastUpdatedTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}
}
