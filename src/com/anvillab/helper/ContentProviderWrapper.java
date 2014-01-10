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
	
	public Cursor getData()
	{
		CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.TAG_TABLE);
		return Provider.query(CONTENT_URI, null, null, null, null);
	}
	
	public long createUser(User user)
	{
		if(ifUserExists(user.getUserId()))
			return updateUser(user);

		long insertedRow = 0;
		
		ContentValues values=new ContentValues();
		values=User.getVal(user);
		CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.USER_TABLE);
		CONTENT_URI = Provider.insert(CONTENT_URI, values);
		
		insertedRow = Long.valueOf(CONTENT_URI.getLastPathSegment().toString());
		updateUserSyncFields();
		return insertedRow;
	}
	
	public boolean ifUserExists(long userId)
	{
		boolean tick = false;
		
		String selectionClause=DatabaseHelper.KEY_ID + " = ?";
		String selectionArgs[]=new String[] { String.valueOf(userId) };
		
		CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.USER_TABLE);
		Cursor cursor = Provider.query(CONTENT_URI, null, selectionClause, selectionArgs, null);
		
		if(cursor.getCount()!=0)
			tick = true;
		
		return tick;
	}
	
	public long updateUser(User user)
	{
		
		String selectionClause=DatabaseHelper.KEY_ID + " = ?";
		String selectionArgs[]=new String[] { String.valueOf(user.getUserId()) };
	
		ContentValues values=new ContentValues();
		values=User.getVal(user);
		
		CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.USER_TABLE);
		long rowsUpdated = Provider.update(CONTENT_URI, values, selectionClause, selectionArgs);
		
		updateUserSyncFields();
		return rowsUpdated;
	}
	
	public String getMaxDateFromDatabase()
	{
		Cursor cursor;
		CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.USER_TABLE);
		cursor = Provider.query(CONTENT_URI, new String[] {"MAX(lastUpdated) AS lastUpdated"} , null, null, null);
		cursor.moveToFirst();
		
		String maxDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_LAST_UPDATED));
		return maxDate;
	}
	
	public long updateUserSyncFields()
	{
		  
		CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.USER_TABLE);
		
		Cursor cursor = Provider.query(CONTENT_URI, new String[] {"MAX(lastUpdatedId) AS lastUpdatedId"} , null, null, null);
		cursor.moveToFirst();
		long maxId = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_LAST_UPDATED_ID));
		
		cursor = Provider.query(CONTENT_URI, new String[] {"MAX(lastUpdated) AS lastUpdated"} , null, null, null);
		cursor.moveToFirst();
		
		String maxDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_LAST_UPDATED));
		
		ContentValues values = new ContentValues();
		
		values.put(DatabaseHelper.KEY_LAST_UPDATED, maxDate);
		values.put(DatabaseHelper.KEY_LAST_UPDATED_ID, maxId);
		
		return Provider.update(CONTENT_URI, values, null, null);
	}
	
	public long updateUserSyncFields(String maxDate,long maxId)
	{
		ContentValues values = new ContentValues();
		
		values.put(DatabaseHelper.KEY_LAST_UPDATED, maxDate);
		values.put(DatabaseHelper.KEY_LAST_UPDATED_ID, maxId);
		
		return Provider.update(CONTENT_URI, values, null, null);
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
	

	public long rateRestaurant(Review review)
	{
		if(ifRestaurantRatingExists(review.getUserId(),review.getAssociatedId()))
			return updateRestaurantRating(review);
		
		long insertedRow = 0;
		 
	    ContentValues values = new ContentValues();
	    values=Review.getVal(review, review.RESTAURANT);
	    
	    CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RATE_RESTAURANT_TABLE);
		CONTENT_URI = Provider.insert(CONTENT_URI, values);
		
		insertedRow = Long.valueOf(CONTENT_URI.getLastPathSegment().toString());
		return insertedRow;
	    
	}
	
	public boolean ifRestaurantRatingExists(long userId, long restId)
	{
		boolean tick = false;
		
		String selectionClause=DatabaseHelper.KEY_USER_ID + " = ?" + " AND " + DatabaseHelper.KEY_RESTAURANT_ID + " = ?";
		String selectionArgs[]=new String[] { String.valueOf(userId), String.valueOf(restId) };
		
		CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RATE_RESTAURANT_TABLE);
		Cursor cursor = Provider.query(CONTENT_URI, null, selectionClause, selectionArgs, null);
		
		if(cursor.getCount()!=0)
			tick = true;
		
		return tick;
	}
	

	public long updateRestaurantRating(Review review)
	{
		
		String selectionClause=DatabaseHelper.KEY_RESTAURANT_ID + " = ?";
		String selectionArgs[]=new String[] { String.valueOf(review.getAssociatedId()) };
		 
	    ContentValues values = new ContentValues();
	    values=Review.getVal(review, Review.RESTAURANT);
	    
	    CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RATE_RESTAURANT_TABLE);
		
	    return Provider.update(CONTENT_URI, values, selectionClause, selectionArgs);
	    
	}
	
	public long rateMenu(Review review)
	{
		if(ifMenuRatingExists(review.getUserId(),review.getAssociatedId()))
			return updateMenuRating(review);
		
		long insertedRow = 0;
		 
	    ContentValues values = new ContentValues();
	    values=Review.getVal(review, Review.MENU);
	    
	    CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RATE_MENU_TABLE);
		CONTENT_URI = Provider.insert(CONTENT_URI, values);
		
		insertedRow = Long.valueOf(CONTENT_URI.getLastPathSegment().toString());
		return insertedRow;
	    
	}
	
	
	public boolean ifMenuRatingExists(long userId, long menuId)
	{
		boolean tick = false;
		
		String selectionClause=DatabaseHelper.KEY_USER_ID + " = ?" + " AND " + DatabaseHelper.KEY_MENU_ID + " = ?";
		String selectionArgs[]=new String[] { String.valueOf(userId), String.valueOf(menuId) };
		
		CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY+ "/" + DataProvider.RATE_MENU_TABLE);
		Cursor cursor = Provider.query(CONTENT_URI, null, selectionClause, selectionArgs, null);
		
		if(cursor.getCount()!=0)
			tick = true;
		
		return tick;
	}
	
	
	
	public long updateMenuRating(Review review)
	{
		
		String selectionClause=DatabaseHelper.KEY_MENU_ID + " = ?";
		String selectionArgs[]=new String[] { String.valueOf(review.getAssociatedId()) };
		 
	    ContentValues values = new ContentValues();
	    values=Review.getVal(review, Review.MENU);
	    
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
