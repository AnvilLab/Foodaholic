package com.anvillab.helper;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.anvillab.model.MenuItem;
import com.anvillab.model.Restaurant;
import com.anvillab.model.Review;
import com.anvillab.model.User;

public class ContentProviderWrapper {
	
	Context Context;
	ContentResolver Provider;
	String id;
	
	public static Uri CONTENT_URI;
	
	public ContentProviderWrapper(Context context)
	{
		Context=context;
		Provider=context.getContentResolver();
	}
	
	
	
	public long createUser(User user)
	{
		long insertedRow = 0;
		
		ContentValues values=new ContentValues();
		values=User.getVal(user);
		CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.USER_TABLE);
		CONTENT_URI = Provider.insert(CONTENT_URI, values);
		
		insertedRow = Long.valueOf(CONTENT_URI.getLastPathSegment().toString());
		return insertedRow;
	}
	
	public long updateUser(User user)
	{
		
		String selectionClause=DatabaseHelper.KEY_ID + " = ?";
		String selectionArgs[]=new String[] { String.valueOf(user.getUserId()) };
	
		ContentValues values=new ContentValues();
		values=User.getVal(user);
		
		CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.USER_TABLE);
		return Provider.update(CONTENT_URI, values, selectionClause, selectionArgs);
		
	}
	
	public long createRestaurant(Restaurant restaurant)
	{
		long insertedRow = 0;
		
		ContentValues values=new ContentValues();
		values=Restaurant.getVal(restaurant);
		CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RESTAURANT_TABLE);
		CONTENT_URI = Provider.insert(CONTENT_URI, values);
		
		insertedRow = Long.valueOf(CONTENT_URI.getLastPathSegment().toString());
		return insertedRow;
	}
	
	public long createMenu(MenuItem menu)
	{
		long insertedRow = 0;
		
		ContentValues values=new ContentValues();
		values=MenuItem.getVal(menu);
		CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.MENU_TABLE);
		CONTENT_URI = Provider.insert(CONTENT_URI, values);
		
		insertedRow = Long.valueOf(CONTENT_URI.getLastPathSegment().toString());
		return insertedRow;
	}
	
	public long rateRestaurant(Review review, long restaurantId)
	{
		long insertedRow = 0;
		 
	    ContentValues values = new ContentValues();
	    values=Review.getVal(review, review.RESTAURANT, restaurantId);
	    
	    CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RATE_RESTAURANT_TABLE);
		CONTENT_URI = Provider.insert(CONTENT_URI, values);
		
		insertedRow = Long.valueOf(CONTENT_URI.getLastPathSegment().toString());
		return insertedRow;
	    
	}
	

	public long updateRestaurantRating(Review review, long restaurantId)
	{
		
		String selectionClause=DatabaseHelper.KEY_RESTAURANT_ID + " = ?";
		String selectionArgs[]=new String[] { String.valueOf(restaurantId) };
		 
	    ContentValues values = new ContentValues();
	    values=Review.getVal(review, Review.RESTAURANT, restaurantId);
	    
	    CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RATE_RESTAURANT_TABLE);
		
	    return Provider.update(CONTENT_URI, values, selectionClause, selectionArgs);
	    
	}
	
	public long rateMenu(Review review, long menuId)
	{
		long insertedRow = 0;
		 
	    ContentValues values = new ContentValues();
	    values=Review.getVal(review, Review.MENU, menuId);
	    
	    CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RATE_MENU_TABLE);
		CONTENT_URI = Provider.insert(CONTENT_URI, values);
		
		insertedRow = Long.valueOf(CONTENT_URI.getLastPathSegment().toString());
		return insertedRow;
	    
	}
	
	public long updateMenuRating(Review review, long menuId)
	{
		
		String selectionClause=DatabaseHelper.KEY_MENU_ID + " = ?";
		String selectionArgs[]=new String[] { String.valueOf(menuId) };
		 
	    ContentValues values = new ContentValues();
	    values=Review.getVal(review, Review.MENU, menuId);
	    
	    CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RATE_MENU_TABLE);
		
	    return Provider.update(CONTENT_URI, values, selectionClause, selectionArgs);
	    
	}
	
	public Cursor getRestaurantsbyLocation(String location)
	{
		location = "%" + location + "%";
		
		//"address"
		String selectionClause=DatabaseHelper.KEY_ADDRESS + " LIKE ?";
		String selectionArgs[]=new String[] { location };
		CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RESTAURANT_TABLE);
		
		Cursor cursor = Provider.query(CONTENT_URI, null, selectionClause, selectionArgs, null);
		
		return cursor;
	}
	
	public Cursor getUsers()
	{
		CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.USER_TABLE);
		Cursor cursor = Provider.query(CONTENT_URI, null, null, null, null);
		return cursor;
	}
	
	public Cursor getRestaurants()
	{
		CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RESTAURANT_TABLE);
		Cursor cursor = Provider.query(CONTENT_URI, null, null, null, null);
		return cursor;
	}
	
	public Cursor getRestaurantRatings()
	{
		CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RATE_RESTAURANT_TABLE);
		Cursor cursor = Provider.query(CONTENT_URI, null, null, null, null);
		return cursor;
	}
	
	public double getPersonalResturantRating(long userId,long restaurantId)
	{
		
		String[] projection = { DatabaseHelper.KEY_RATING }; 
		
		CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RATE_RESTAURANT_TABLE);
		String selectionClause=DatabaseHelper.KEY_RESTAURANT_ID + " = ? AND " + DatabaseHelper.KEY_USER_ID + " = ?";
		String selectionArgs[]=new String[] { String.valueOf(restaurantId), String.valueOf(userId) };
		
		Cursor cursor = Provider.query(CONTENT_URI, projection, selectionClause, selectionArgs, null);
		cursor.moveToFirst();
		
		return cursor.getFloat(cursor.getColumnIndex(DatabaseHelper.KEY_RATING));
	}
	
	public Cursor getRestaurantsByLocationAndKeyword(String Location, String key)
	{
		Cursor cursor=getRestaurantsbyLocation(Location);
		return cursor;
	}

}
