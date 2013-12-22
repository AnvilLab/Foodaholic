package com.anvillab.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
	
	public static String getFormattedDate(Date lastUpdatedTime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(lastUpdatedTime);
	}
}
