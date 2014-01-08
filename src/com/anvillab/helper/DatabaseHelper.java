package com.anvillab.helper;

import java.util.ArrayList;

import com.anvillab.model.DBLog;
import com.anvillab.model.Restaurant;
import com.anvillab.model.Review;
import com.anvillab.model.MenuItem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

//ADD CREATE AND DELETE USER

public class DatabaseHelper extends SQLiteOpenHelper {
	
	// Logcat tag
    public static final String LOG = "DatabaseHelper";
 
    // Database Version
    public static final int DATABASE_VERSION = 1;
 
    // Database Name
    public static final String DATABASE_NAME = "FoodDatabase";
    
    //Common column names
    public static final String KEY_ID = "id";
    public static final String KEY_RESTAURANT_ID = "restaurantId";
    public static final String KEY_MENU_ID = "itemId";
    public static final String KEY_RATING= "rating";
    public static final String KEY_REVIEW= "review";
    public static final String KEY_NAME = "name";
    public static final String KEY_IS_UPDATED = "isUpdated";
    public static final String KEY_TOTAL_VOTE = "totalVote";
        
    //Table names
    public static final String TABLE_USER = "User";
    public static final String TABLE_RESTAURANT = "Restaurant";
    public static final String TABLE_MENU = "Menu";
    public static final String TABLE_RATE_RESTAURANT = "Rate_Restaurant";	
    public static final String TABLE_RATE_ITEM = "Rate_Menu";	
    
    //User column names
    public static final String KEY_USER_ID= "userId";
    public static final String KEY_CREDENTIAL= "credential";
    public static final String KEY_FACEBOOK_ID= "facebookId";
    public static final String KEY_LAST_UPDATED= "lastUpdated";
    public static final String KEY_LAST_UPDATED_ID= "lastUpdatedId";
     
    //Restaurant column names
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_CONTACT = "contact";
    public static final String KEY_LOCATION_TAG = "locationTag";
    public static final String KEY_OPEN_DURATION = "openDuration";
    public static final String KEY_HOLIDAY = "holiday";
    public static final String KEY_SPECIAL_FEATURES = "specialFeatures";
    public static final String KEY_SEAT_CAPACITY = "seatCapacity";
    public static final String KEY_PRIME_TYPE = "primeType";
    public static final String KEY_STANDARD = "standard";
    public static final String KEY_ACTIVE_STATUS = "activeStatus";
    
    //Menu column names
    public static final String KEY_CATEGORY  = "category";
    public static final String KEY_TAG  = "tags";
    public static final String KEY_SUB_CATEGORY = "subCategory";
    public static final String KEY_PACKAGE = "package";
    public static final String KEY_AVAILABLE_TIME = "availableTime";
    public static final String KEY_PRICE = "price";
    
    public static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY NOT NULL," + KEY_LAST_UPDATED + " DATETIME," 
    		+ KEY_LAST_UPDATED_ID + " INTEGER,"+ KEY_FACEBOOK_ID + " TEXT," + KEY_CREDENTIAL + " TEXT" + ")";

    //id is populated from server
    public static final String CREATE_TABLE_RESTAURANT = "CREATE TABLE "
            + TABLE_RESTAURANT + "(" + KEY_ID + " INTEGER PRIMARY KEY NOT NULL," + KEY_NAME + " TEXT," 
    		+ KEY_ADDRESS + " TEXT," + KEY_LOCATION + " TEXT," + KEY_CONTACT + " TEXT," + KEY_OPEN_DURATION + " TEXT,"
    		+ KEY_PRIME_TYPE + " TEXT," + KEY_HOLIDAY + " TEXT," + KEY_SPECIAL_FEATURES + " TEXT," + KEY_SEAT_CAPACITY + " INTEGER,"
    		+ KEY_STANDARD + " TEXT," + KEY_TOTAL_VOTE + " INTEGER," + KEY_ACTIVE_STATUS + " TEXT,"
    		+ KEY_RATING + " REAL" + ")"; 
   
  //id is populated from server
   public static final String CREATE_TABLE_MENU = "CREATE TABLE "
           + TABLE_MENU + "(" + KEY_ID + " INTEGER PRIMARY KEY NOT NULL," + KEY_RESTAURANT_ID + " INTEGER," + KEY_NAME + " TEXT," 
   		   + KEY_CATEGORY  + " TEXT,"  + KEY_SUB_CATEGORY + " TEXT," + KEY_TAG + " TEXT," + KEY_PACKAGE + " TEXT," + KEY_AVAILABLE_TIME + " TEXT,"
   		   + KEY_PRICE + " REAL," +  KEY_TOTAL_VOTE + " INTEGER," + KEY_RATING + " REAL" + ")";
   
   //user and restaurant joint primary key
   public static final String CREATE_TABLE_RATE_RESTAURANT = "CREATE TABLE "
           + TABLE_RATE_RESTAURANT  + "(" + KEY_USER_ID + " INTEGER,"  + KEY_LAST_UPDATED + " INTEGER,"  + KEY_RESTAURANT_ID + " INTEGER," 
   		   + KEY_RATING + " REAL," +  KEY_REVIEW + " TEXT," + " PRIMARY KEY " + "(" + KEY_RESTAURANT_ID + ", " + KEY_USER_ID + "))";
   
   //user and menu joint primary key
   public static final String CREATE_TABLE_RATE_ITEM = "CREATE TABLE "
           + TABLE_RATE_ITEM + "(" + KEY_USER_ID + " INTEGER," + KEY_LAST_UPDATED + " INTEGER," + KEY_MENU_ID + " INTEGER," 
           + KEY_RATING + " REAL," + KEY_REVIEW + " TEXT," + " PRIMARY KEY " + "(" + KEY_MENU_ID + ", " + KEY_USER_ID + "))";
   
   

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	 public DatabaseHelper(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	 }

	@Override
	public void onCreate(SQLiteDatabase database) {
		// TODO Auto-generated method stub
		database.execSQL(CREATE_TABLE_USER);
		database.execSQL(CREATE_TABLE_RESTAURANT);
		database.execSQL(CREATE_TABLE_MENU);
		database.execSQL(CREATE_TABLE_RATE_RESTAURANT);
		database.execSQL(CREATE_TABLE_RATE_ITEM);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_RATE_RESTAURANT);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_RATE_ITEM);
	}

	public void executeScript(ArrayList<DBLog> dbLogs)
	{
		SQLiteDatabase database = this.getWritableDatabase();
		
		for (int i=0; i < dbLogs.size() ; i++)
			database.execSQL(dbLogs.get(i).SqlString);
	}

}

