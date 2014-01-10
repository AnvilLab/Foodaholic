package com.anvillab.helper;

import com.anvillab.utilities.QueryMapper;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;



public class CursorLoaderFactory {
	
	Context context;
	CursorLoader loader;
	
	public CursorLoaderFactory(Context context)
	{
		this.context=context;
	}
	
	
	public CursorLoader setupLoader(String[] params,int id)
	{
		CursorLoader loader = null;
		
		switch (id) {

        case QueryMapper.GET_RESTAURANTS:
        	loader=getRestaurants();
            break;

        case QueryMapper.GET_RESTAURANTS_BY_LOCATION:
        	loader=getRestaurantsByLocation(params[0]);
            break;
            
        case QueryMapper.GET_RESTAURANTS_BY_LOCATION_NAME:
        	loader=getRestaurantsByLocationAndName(params[0],params[1]);
            break;

        case QueryMapper.GET_RESTAURANTS_BY_LOCATION_TYPE:
        	loader=getRestaurantsByLocationAndType(params[0],params[1]);
            break;
            
        case QueryMapper.GET_RESTAURANTS_BY_NAME:
        	loader=getRestaurantsByName(params[0]);
            break;  
            
        case QueryMapper.GET_RESTAURANTS_BY_TYPE:
        	loader=getRestaurantsByType(params[0]);
            break;
            
        case QueryMapper.GET_MENUS_BY_KEYWORDS:
        	loader=getMenuByMenuKeyword(params[0]);
            break;
            
        case QueryMapper.GET_MENUS_BY_RESTAURANT:
        	loader=getMenuByRestaurant(params[0]);
            break;
        
		}
		
		return loader;
	}
		
	public CursorLoader getRestaurantsByLocation(String location)
	{
		location = "%" + location + "%";
		
		String selectionClause=DatabaseHelper.KEY_LOCATION_TAG + " LIKE ?";
		String selectionArgs[]=new String[] { location };
		ContentProviderWrapper.CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RESTAURANT_TABLE);
	
		loader = new CursorLoader(context,ContentProviderWrapper.CONTENT_URI,null,selectionClause,selectionArgs,DatabaseHelper.KEY_RATING + " ASC");
		return loader;
	}
	
	public CursorLoader getRestaurantsByLocationAndType(String location,String type)
	{
		location = "%" + location + "%";
		type = "%" + type + "%";
		
		String selectionClause=DatabaseHelper.KEY_LOCATION_TAG + " LIKE ? AND " + DatabaseHelper.KEY_PRIME_TYPE + " LIKE ?";
		String selectionArgs[]=new String[] { location,type };
		ContentProviderWrapper.CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RESTAURANT_TABLE);
	
		loader = new CursorLoader(context,ContentProviderWrapper.CONTENT_URI,null,selectionClause,selectionArgs,DatabaseHelper.KEY_RATING + " ASC");
		return loader;
	}
	
	public CursorLoader getRestaurantsByLocationAndName(String location,String name)
	{
		location = "%" + location + "%";
		name = "%" + name + "%";
		
		String selectionClause=DatabaseHelper.KEY_LOCATION_TAG + " LIKE ? AND " + DatabaseHelper.KEY_NAME + " LIKE ?";
		String selectionArgs[]=new String[] { location,name };
		ContentProviderWrapper.CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RESTAURANT_TABLE);
	
		loader = new CursorLoader(context,ContentProviderWrapper.CONTENT_URI,null,selectionClause,selectionArgs,DatabaseHelper.KEY_RATING + " ASC");
		return loader;
	}
	
	public CursorLoader getRestaurantsByType(String type)
	{
		type = "%" + type + "%";
		
		String selectionClause=DatabaseHelper.KEY_PRIME_TYPE + " LIKE ?";
		String selectionArgs[]=new String[] { type };
		ContentProviderWrapper.CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RESTAURANT_TABLE);
	
		loader = new CursorLoader(context,ContentProviderWrapper.CONTENT_URI,null,selectionClause,selectionArgs,DatabaseHelper.KEY_RATING + " ASC");
		return loader;
	}
	
	public CursorLoader getRestaurantsByName(String name)
	{
		name = "%" + name + "%";
		
		String selectionClause=DatabaseHelper.KEY_NAME + " LIKE ?";
		String selectionArgs[]=new String[] { name };
		ContentProviderWrapper.CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RESTAURANT_TABLE);
	
		loader = new CursorLoader(context,ContentProviderWrapper.CONTENT_URI,null,selectionClause,selectionArgs,DatabaseHelper.KEY_RATING + " ASC");
		return loader;
	}
	
	public CursorLoader getRestaurants()
	{
		ContentProviderWrapper.CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RESTAURANT_TABLE);
		loader = new CursorLoader(context,ContentProviderWrapper.CONTENT_URI,null,null,null,DatabaseHelper.KEY_RATING + " ASC");
		return loader;
	}
	
	public CursorLoader getMenuByRestaurant(String resId)
	{
		String selectionClause=DatabaseHelper.KEY_RESTAURANT_ID + " = ?";
		String selectionArgs[]=new String[] { resId };
		ContentProviderWrapper.CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.MENU_TABLE);
	
		loader = new CursorLoader(context,ContentProviderWrapper.CONTENT_URI,null,selectionClause,selectionArgs,DatabaseHelper.KEY_RATING + " ASC");
		return loader;
	}
	
	public CursorLoader getMenuByMenuKeyword(String keyword)
	{
		keyword = "%" + keyword + "%";
		
		String selectionClause=DatabaseHelper.KEY_CATEGORY + " LIKE ? OR " + DatabaseHelper.KEY_SUB_CATEGORY + " LIKE ? OR " + DatabaseHelper.KEY_NAME + " LIKE ? OR " + DatabaseHelper.KEY_PACKAGE + " LIKE ?";
		String selectionArgs[]=new String[] { keyword,keyword,keyword,keyword };
		ContentProviderWrapper.CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.MENU_TABLE);
	
		loader = new CursorLoader(context,ContentProviderWrapper.CONTENT_URI,null,selectionClause,selectionArgs,DatabaseHelper.KEY_RATING + " ASC");
		return loader;
	}
	
	
	
}
