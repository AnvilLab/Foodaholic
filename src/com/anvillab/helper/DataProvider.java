package com.anvillab.helper;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class DataProvider extends ContentProvider {
	
	private static final int RESTAURANTS = 1;
	private static final int MENUS = 3;
	private static final int USERS = 5;
	private static final int RATE_RESTAURANT = 7;
	private static final int RATE_MENU = 9;
	
	public static final String RESTAURANT_TABLE = "Restaurants";
	public static final String MENU_TABLE = "Menus";
	public static final String USER_TABLE = "Users";
	public static final String RATE_RESTAURANT_TABLE = "rateRestaurant";
	public static final String RATE_MENU_TABLE = "rateMenu";
	
	private DatabaseHelper db;
	
	public static final String AUTHORITY = "com.anvillab.helper.DataProvider";
	
	private static final UriMatcher uriMatcher;
	static {
	    uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	    uriMatcher.addURI(AUTHORITY, RESTAURANT_TABLE, RESTAURANTS);
	    uriMatcher.addURI(AUTHORITY, MENU_TABLE, MENUS);
	    uriMatcher.addURI(AUTHORITY, USER_TABLE, USERS);
	    uriMatcher.addURI(AUTHORITY, RATE_RESTAURANT_TABLE, RATE_RESTAURANT);
	    uriMatcher.addURI(AUTHORITY, RATE_MENU_TABLE, RATE_MENU);
	}
	
	@Override
	public Cursor query(Uri uri,String[] projection,String selection,String[] selectionArgs,String sortOrder) {
		
		SQLiteDatabase database= db.getReadableDatabase();
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		

		
		switch (uriMatcher.match(uri)) {

	        case RESTAURANTS:
	        	queryBuilder.setTables(DatabaseHelper.TABLE_RESTAURANT);
	            break;
	
	        case MENUS:
	        	queryBuilder.setTables(DatabaseHelper.TABLE_MENU);
	            break;
	            
	        case RATE_RESTAURANT:
	        	queryBuilder.setTables(DatabaseHelper.TABLE_RATE_RESTAURANT);
	            break;
	
	        case RATE_MENU:
	        	queryBuilder.setTables(DatabaseHelper.TABLE_RATE_ITEM);
	            break;
	            
	        case USERS:
	        	queryBuilder.setTables(DatabaseHelper.TABLE_USER);
	            break;    
            
		}
		
		Cursor cursor = queryBuilder.query(database, projection, selection,
		        selectionArgs, null, null, sortOrder);
		
		return cursor;
		
	}


	

	@Override
	public int delete(Uri uri, String selectionClause, String[] selectionArgs) {

		SQLiteDatabase database= db.getWritableDatabase();
		int rowsDeleted=0;
		
		switch (uriMatcher.match(uri)) {

	        case RESTAURANTS:
	        	rowsDeleted = database.delete(DatabaseHelper.TABLE_RESTAURANT, selectionClause, selectionArgs);
	            break;
	
	        case MENUS:
	        	rowsDeleted = database.delete(DatabaseHelper.TABLE_MENU, selectionClause, selectionArgs);
	            break;
            
	        case RATE_RESTAURANT:
	        	rowsDeleted = database.delete(DatabaseHelper.TABLE_RATE_RESTAURANT, selectionClause, selectionArgs);
	            break;
	
	        case RATE_MENU:
	        	rowsDeleted = database.delete(DatabaseHelper.TABLE_RATE_ITEM, selectionClause, selectionArgs);
	            break;
	            
	        case USERS:
	        	rowsDeleted = database.delete(DatabaseHelper.TABLE_USER, selectionClause, selectionArgs);
	            break;
            
		}
		
		return rowsDeleted;
	}


	@Override
	public Uri insert(Uri uri, ContentValues values) {

		SQLiteDatabase database = db.getWritableDatabase();
		long insertedId=0;
		
		switch (uriMatcher.match(uri)) {
		
			case USERS:
				insertedId = database.insert(DatabaseHelper.TABLE_USER, null, values);
	            break;
				
	        case RESTAURANTS:
	        	insertedId = database.insert(DatabaseHelper.TABLE_RESTAURANT, null, values);
	            break;
	
	        case MENUS:
	        	insertedId = database.insert(DatabaseHelper.TABLE_MENU, null, values);
	            break;
	            
	        case RATE_RESTAURANT:
				insertedId = database.insert(DatabaseHelper.TABLE_RATE_RESTAURANT, null, values);
	            break;
	            
	        case RATE_MENU:
				insertedId = database.insert(DatabaseHelper.TABLE_RATE_ITEM, null, values);
	            break;
            
		}
		
		//NOTIFY
		Context ctx = getContext();
        ctx.getContentResolver().notifyChange(uri, null, false);
		
		return Uri.parse(AUTHORITY + "/" + insertedId);
	}		
	

	@Override
	public int update(Uri uri, ContentValues values, String selectionClause, String[] selectionArgs) {

		SQLiteDatabase database = db.getWritableDatabase();
		int rowsUpdated = 0;
		
		switch (uriMatcher.match(uri)) {

	        case RESTAURANTS:
	        	rowsUpdated = database.update(DatabaseHelper.TABLE_RESTAURANT, values, selectionClause, selectionArgs);
	            break;
	
	        case MENUS:
	        	rowsUpdated = database.update(DatabaseHelper.TABLE_MENU, values, selectionClause, selectionArgs);
	            break;
	            
	        case RATE_RESTAURANT:
	        	rowsUpdated = database.update(DatabaseHelper.TABLE_RATE_RESTAURANT, values, selectionClause, selectionArgs);
	            break;
	
	        case RATE_MENU:
	        	rowsUpdated = database.update(DatabaseHelper.TABLE_RATE_ITEM, values, selectionClause, selectionArgs);
	            break;
	            
	        case USERS:
	        	rowsUpdated = database.update(DatabaseHelper.TABLE_USER, values, selectionClause, selectionArgs);
	            break;
            
		}
		
		return rowsUpdated;
	}
	
	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public boolean onCreate() {
		
		db=new DatabaseHelper(getContext());
		return true;
	
	}


}
