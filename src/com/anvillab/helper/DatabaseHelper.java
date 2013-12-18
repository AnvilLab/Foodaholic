package com.anvillab.helper;

import java.util.ArrayList;

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
    private static final String LOG = "DatabaseHelper";
 
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "FoodDatabase";
    
    //Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_RESTAURANT_ID = "restaurantId";
    private static final String KEY_MENU_ID = "itemId";
    private static final String KEY_RATING= "rating";
    private static final String KEY_REVIEW= "review";
    private static final String KEY_NAME = "name";
    private static final String KEY_TOTAL_VOTE = "totalVote";
        
    //Table names
    private static final String TABLE_USER = "Users";
    private static final String TABLE_RESTAURANT = "Restaurants";
    private static final String TABLE_MENU = "MenuItems";
    private static final String TABLE_RATE_RESTAURANT = "Rate_Restaurant";	
    private static final String TABLE_RATE_ITEM = "Rate_Menu";	
    
    //User column names
    private static final String KEY_USER_ID= "userId";
    private static final String KEY_CREDENTIAL= "credential";
    private static final String KEY_FACEBOOK_ID= "facebookId";
    private static final String KEY_LAST_UPDATED= "lastUpdated";
    private static final String KEY_LAST_UPDATED_ID= "lastUpdatedId";
     
    //Restaurant column names
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_CONTACT = "contact";
    private static final String KEY_OPEN_DURATION = "openDuration";
    private static final String KEY_HOLIDAY = "holiday";
    private static final String KEY_SPECIAL_FEATURES = "specialFeatures";
    private static final String KEY_SEAT_CAPACITY = "seatCapacity";
    private static final String KEY_PRIME_TYPE = "primeType";
    private static final String KEY_STANDARD = "standard";
    private static final String KEY_ACTIVE_STATUS = "activeStatus";
    
    //Menu column names
    private static final String KEY_CATEGORY  = "category";
    private static final String KEY_TAG  = "tags";
    private static final String KEY_SUB_CATEGORY = "subCategory";
    private static final String KEY_PACKAGE = "package";
    private static final String KEY_AVAILABLE_TIME = "availableTime";
    private static final String KEY_PRICE = "price";
    
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY NOT NULL," + KEY_LAST_UPDATED + " DATETIME," 
    		+ KEY_LAST_UPDATED_ID + " INTEGER,"+ KEY_FACEBOOK_ID + " TEXT," + KEY_CREDENTIAL + " TEXT" + ")";

    //id is populated from server
    private static final String CREATE_TABLE_RESTAURANT = "CREATE TABLE "
            + TABLE_RESTAURANT + "(" + KEY_ID + " INTEGER PRIMARY KEY NOT NULL," + KEY_NAME + " TEXT," 
    		+ KEY_ADDRESS + " TEXT," + KEY_LOCATION + " TEXT," + KEY_CONTACT + " TEXT," + KEY_OPEN_DURATION + " TEXT,"
    		+ KEY_PRIME_TYPE + " TEXT," + KEY_HOLIDAY + " TEXT," + KEY_SPECIAL_FEATURES + " TEXT," + KEY_SEAT_CAPACITY + " INTEGER,"
    		+ KEY_STANDARD + " TEXT," + KEY_TOTAL_VOTE + " INTEGER," + KEY_ACTIVE_STATUS + " TEXT,"
    		+ KEY_RATING + " REAL" + ")"; 
   
  //id is populated from server
   private static final String CREATE_TABLE_MENU = "CREATE TABLE "
           + TABLE_MENU + "(" + KEY_ID + " INTEGER PRIMARY KEY NOT NULL," + KEY_RESTAURANT_ID + " INTEGER," + KEY_NAME + " TEXT," 
   		   + KEY_CATEGORY  + " TEXT,"  + KEY_SUB_CATEGORY + " TEXT," + KEY_TAG + " TEXT," + KEY_PACKAGE + " TEXT," + KEY_AVAILABLE_TIME + " TEXT,"
   		   + KEY_PRICE + " REAL," +  KEY_TOTAL_VOTE + " INTEGER," + KEY_RATING + " REAL" + ")";
   
   //user and restaurant joint primary key
   private static final String CREATE_TABLE_RATE_RESTAURANT = "CREATE TABLE "
           + TABLE_RATE_RESTAURANT  + "(" + KEY_USER_ID + " INTEGER,"  + KEY_RESTAURANT_ID + " INTEGER," 
   		   + KEY_RATING + " REAL," +  KEY_REVIEW + " TEXT," + " PRIMARY KEY " + "(" + KEY_RESTAURANT_ID + ", " + KEY_USER_ID + "))";
   
   //user and menu joint primary key
   private static final String CREATE_TABLE_RATE_ITEM = "CREATE TABLE "
           + TABLE_RATE_ITEM + "(" + KEY_USER_ID + " INTEGER," + KEY_MENU_ID + " INTEGER," 
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
	
	
	
	public long createRestaurant(Restaurant restaurant)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		
		values.put(KEY_NAME,restaurant.getName());
		values.put(KEY_ADDRESS,restaurant.getAddress());
		values.put(KEY_LOCATION,restaurant.getLocation());
		values.put(KEY_PRIME_TYPE, restaurant.getPrimeType());
		values.put(KEY_OPEN_DURATION, restaurant.getOpenDuration());
		values.put(KEY_CONTACT, restaurant.getContactNo());
		values.put(KEY_HOLIDAY, restaurant.getHoliday());
		values.put(KEY_ACTIVE_STATUS, restaurant.getActiveStatus());
		values.put(KEY_SPECIAL_FEATURES, restaurant.getSpecialFeatures());
		values.put(KEY_SEAT_CAPACITY, restaurant.getSeatCapacity());
		values.put(KEY_RATING, restaurant.getRating());
		values.put(KEY_TOTAL_VOTE, restaurant.getTotalVote());
		values.put(KEY_STANDARD, restaurant.getStandard());
		
		long id= db.insert(TABLE_RESTAURANT, null, values);
		return id;
	}
	
	public long updateRestaurant(Restaurant restaurant)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		
		values.put(KEY_NAME,restaurant.getName());
		values.put(KEY_ADDRESS,restaurant.getAddress());
		values.put(KEY_LOCATION,restaurant.getLocation());
		values.put(KEY_PRIME_TYPE, restaurant.getPrimeType());
		values.put(KEY_OPEN_DURATION, restaurant.getOpenDuration());
		values.put(KEY_CONTACT, restaurant.getContactNo());
		values.put(KEY_HOLIDAY, restaurant.getHoliday());
		values.put(KEY_ACTIVE_STATUS, restaurant.getActiveStatus());
		values.put(KEY_SPECIAL_FEATURES, restaurant.getSpecialFeatures());
		values.put(KEY_SEAT_CAPACITY, restaurant.getSeatCapacity());
		values.put(KEY_RATING, restaurant.getRating());
		values.put(KEY_TOTAL_VOTE, restaurant.getTotalVote());
		values.put(KEY_STANDARD, restaurant.getStandard());
		
		
		return db.update(TABLE_RESTAURANT, values, KEY_ID + " = ?",
	            new String[] { String.valueOf(restaurant.getId()) });
		
	}
	
	
	public ArrayList<MenuItem> getMenuByRestaurant(long restaurantId)
	{
		ArrayList<MenuItem> Menus=new ArrayList<MenuItem>();
		
		String selectQuery = "SELECT  * FROM " + TABLE_MENU + " WHERE "
	            + KEY_RESTAURANT_ID + " = " + restaurantId;
		
		 SQLiteDatabase db = this.getReadableDatabase();
		 Cursor cursor = db.rawQuery(selectQuery, null);
		 
		 if (cursor.moveToFirst()) {
		        do {
		            MenuItem menu = new MenuItem();
		         
		            menu.setId((cursor.getLong(cursor.getColumnIndex(KEY_ID))));
		            menu.setName((cursor.getString(cursor.getColumnIndex(KEY_NAME))));
		            menu.setTags((cursor.getString(cursor.getColumnIndex(KEY_TAG))));
		            menu.setPackage((cursor.getString(cursor.getColumnIndex(KEY_PACKAGE))));
		            menu.setCategory((cursor.getString(cursor.getColumnIndex(KEY_CATEGORY))));
		            menu.setSubCatergory((cursor.getString(cursor.getColumnIndex(KEY_SUB_CATEGORY))));
		            menu.setAvailableTime((cursor.getString(cursor.getColumnIndex(KEY_AVAILABLE_TIME))));
		            menu.setPrice((cursor.getDouble(cursor.getColumnIndex(KEY_PRICE))));
		            menu.setTotalVote((cursor.getLong(cursor.getColumnIndex(KEY_TOTAL_VOTE))));
		            menu.setRating((cursor.getFloat(cursor.getColumnIndex(KEY_RATING))));
		            
		            Menus.add(menu);
		            
		        } while (cursor.moveToNext());
		 }
		 
		 return Menus;
	
	}
	
	public void deleteRestaurant(long restaurantId)
	{
		ArrayList<MenuItem> Menus =  getMenuByRestaurant(restaurantId);
		
		for(int i=0;i<Menus.size();i++)
			deleteMenu(Menus.get(i).getId());
		
		SQLiteDatabase db=this.getWritableDatabase();
		
		db.delete(TABLE_RESTAURANT, KEY_ID + " = ?",
	            new String[] { String.valueOf(restaurantId) });
	}
		
		
	public long createMenu(MenuItem menu)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		
		values.put(KEY_NAME,menu.getName());
		values.put(KEY_CATEGORY,menu.getCategory());
		values.put(KEY_TAG,menu.getTags());
		values.put(KEY_SUB_CATEGORY,menu.getSubCatergory());
		values.put(KEY_PACKAGE, menu.getPackage());
		values.put(KEY_AVAILABLE_TIME, menu.getAvailableTime());
		values.put(KEY_PRICE,menu.getPrice());
		values.put(KEY_RATING, menu.getRating());
		values.put(KEY_TOTAL_VOTE,menu.getTotalVote());
		values.put(KEY_RESTAURANT_ID,menu.getRestaurantId());
		
		
		long id= db.insert(TABLE_MENU, null, values);
		return id;
	}
	
	public long updateMenu(MenuItem menu)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		
		values.put(KEY_NAME,menu.getName());
		values.put(KEY_CATEGORY,menu.getCategory());
		values.put(KEY_SUB_CATEGORY,menu.getSubCatergory());
		values.put(KEY_PACKAGE, menu.getPackage());
		values.put(KEY_TAG,menu.getTags());
		values.put(KEY_AVAILABLE_TIME, menu.getAvailableTime());
		values.put(KEY_PRICE,menu.getPrice());
		values.put(KEY_RATING, menu.getRating());
		values.put(KEY_TOTAL_VOTE,menu.getTotalVote());
		values.put(KEY_RESTAURANT_ID,menu.getRestaurantId());
		
		return db.update(TABLE_MENU, values, KEY_ID + " = ?",
	            new String[] { String.valueOf(menu.getId()) });
	}
	
	public void deleteMenu(long menuId)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		db.delete(TABLE_MENU, KEY_ID + " = ?",
		            new String[] { String.valueOf(menuId) });
	}
	
		
	
		
	public void rateRestaurant(Review review, long restaurantId)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues values = new ContentValues();
	    values.put(KEY_RATING, 	review.getRating());
	    values.put(KEY_REVIEW, review.getReview());
	    values.put(KEY_RESTAURANT_ID,restaurantId);
	    values.put(KEY_USER_ID, review.getUserId());
	    
	    db.insert(TABLE_RATE_RESTAURANT, null, values);
	    
	}
	
	public int updateRestaurantRating(Review review, long restaurantId)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues values = new ContentValues();
	    values.put(KEY_RATING, 	review.getRating());
	    values.put(KEY_RESTAURANT_ID,restaurantId);
	    values.put(KEY_USER_ID, review.getUserId());
	    
	    if(review.getReview()!="") values.put(KEY_REVIEW,review.getReview());
	    
	    return db.update(TABLE_RATE_RESTAURANT, values, KEY_RESTAURANT_ID + " = ?" + " AND " + KEY_CREDENTIAL + " = ?",
	            new String[] { String.valueOf(restaurantId),String.valueOf(review.getUserId())});
	    
	}
	
	
	public void rateItem(Review review, long itemId)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues values = new ContentValues();
	    values.put(KEY_RATING, 	review.getRating());
	    values.put(KEY_REVIEW, review.getReview());
	    values.put(KEY_MENU_ID,itemId);
	    values.put(KEY_USER_ID, review.getUserId());
	
	    
	    db.insert(TABLE_RATE_ITEM, null, values);
	    
	}
	
	public void updateItemRating(Review review, long itemId)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		 
	    ContentValues values = new ContentValues();
	    values.put(KEY_RATING, 	review.getRating());
	    values.put(KEY_MENU_ID,itemId);
	    values.put(KEY_USER_ID, review.getUserId());
	    
	    if(review.getReview()!="") values.put(KEY_REVIEW,review.getReview());
	    
	    db.update(TABLE_RATE_ITEM, values, KEY_MENU_ID + " = ?" + " AND " + KEY_USER_ID + " = ?",
	            new String[] { String.valueOf(itemId),String.valueOf(review.getUserId())});
	    
	}
	
	//WORK HERE
	public long getRestaurantsByAddress(String key)
	{
		//ArrayList<Restaurant> restaurants=new ArrayList<Restaurant>();
		String selectQuery = "SELECT  * FROM " + TABLE_RESTAURANT + " WHERE "
	            + KEY_ADDRESS + " LIKE '%" + key +"%'";
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		return cursor.getCount();
		
	}

}

