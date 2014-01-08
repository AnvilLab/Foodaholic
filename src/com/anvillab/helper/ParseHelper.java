package com.anvillab.helper;

import java.util.*;
import org.json.*;

import com.anvillab.model.DBLog;
import com.anvillab.model.User;

public class ParseHelper {

	private static String TAG_LOG_ID ="LogId";
	private static String TAG_CONTENT_ID ="ContentId";
	private static String TAG_SQL ="SqlString";
	private static String TAG_TABLE_NAME ="TableName";
	private static String TAG_METHOD ="Method";
	private static String TAG_DATE ="Date";
	
	private static String TAG_USER_ID ="UserId";
	private static String TAG_FULL_NAME ="Name";
	private static String TAG_USER_NAME ="UserName";
	private static String TAG_PASSWORD ="Password";
	private static String TAG_FACEBOOK_ID ="FacebookId";
	private static String TAG_ACCESS_TOKEN ="AccessToken";	
	private static String TAG_DATE_OF_JOIN ="DateOfJoin";
	private static String TAG_LOCATION ="Location";


	public static ArrayList<DBLog> DBLogParser(String input) throws JSONException
	{
		ArrayList<DBLog> array =new ArrayList();
		JSONArray jsonArr=new JSONArray(input);
		
		for(int i=0;i<jsonArr.length();i++)
		{
			DBLog temp=new DBLog();
			JSONObject jsonObj=jsonArr.getJSONObject(i);
			
			temp.LogId=jsonObj.getInt(TAG_LOG_ID);
			temp.ContentId=jsonObj.getInt(TAG_CONTENT_ID);
			temp.SqlString=jsonObj.getString(TAG_SQL);
			temp.TableName=jsonObj.getString(TAG_TABLE_NAME);
			temp.Method=jsonObj.getString(TAG_METHOD);
			temp.Date=jsonObj.getString(TAG_DATE);
			
			array.add(temp);
		}
		
		return array;
	}
	
	public static User UserParser(String input) throws JSONException
	{
		JSONObject jsonObj=new JSONObject(input);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -10000);
		
		User user=new User(jsonObj.getInt(TAG_USER_ID),jsonObj.getString(TAG_FACEBOOK_ID),jsonObj.getString(TAG_ACCESS_TOKEN),cal.getTime(),0);
				
		return user;
	}
}
