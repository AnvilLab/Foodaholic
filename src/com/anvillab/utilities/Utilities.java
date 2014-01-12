package com.anvillab.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Utilities {
	
	public static String FACEBOOK_ID;
	public static long USER_ID;
	public static String ACCESS_TOKEN;

	public static String getFormattedDate(Date lastUpdatedTime) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(lastUpdatedTime);

	}

	public static Date getFormattedDateFromString(String lastUpdatedTime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = null;

		try {
			date = dateFormat.parse(lastUpdatedTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	public static void loadSavedPreferences(Context context) {

		SharedPreferences sharedPreferences = PreferenceManager
		.getDefaultSharedPreferences(context);

		sharedPreferences.getString("facebookId", FACEBOOK_ID);
		sharedPreferences.getString("accessToken", ACCESS_TOKEN);
		sharedPreferences.getLong("userId", USER_ID);

	}



	public static void savePreferences(Context context, String key, String value) {

		SharedPreferences sharedPreferences = PreferenceManager
		.getDefaultSharedPreferences(context);
		
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();

	}

}
