package com.anvillab.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.anvillab.utilities.*;

public class DBLog {
	
	public int LogId;
	public int ContentId;
    public String SqlString;
    public String TableName;
	public String Method;
    public String Date;
    
    public DBLog()
    {
    	
    }
    
    public DBLog(int id,int contentId,String sql, String table, String method, String date )
    {
    	this.LogId=id;
    	this.ContentId=contentId;
    	this.SqlString=sql;
    	this.TableName=table;
    	this.Method=method;
    	this.Date=date;
    }
    
    public static String getMaxDate(ArrayList<DBLog> logs)
    {
    	Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -10000);
		
		Date maxDate = cal.getTime();
    	
    	for(int i=0;i<logs.size();i++)
    	{
    		Date date = Utilities.getFormattedDateFromString(logs.get(i).Date);
    		if(date.after(maxDate)) 
    			maxDate=date;
    	}
    	
    	
    	
    	return Utilities.getFormattedDate(maxDate);
    }
}
