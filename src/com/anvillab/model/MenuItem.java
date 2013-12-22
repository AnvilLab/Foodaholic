package com.anvillab.model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

import com.anvillab.helper.DatabaseHelper;

public class MenuItem {
		
	public long Id;
	public String Name;
	public String Category;
	public String SubCatergory;
	public String Package;
	public String Tags;
	public String AvailableTime;
	public Double Price;
	public float Rating;
	public long TotalVote;
	public float PersonalRating;
	public long RestaurantId;
	
	public MenuItem()
	{
		
	}
	
	public MenuItem(long id, String name, String category, String subCatergory,
			String package1, String tags, String availableTime, Double price,
			float rating, long totalVote, float personalRating,
			long restaurantId) {
		super();
		Id = id;
		Name = name;
		Category = category;
		SubCatergory = subCatergory;
		Package = package1;
		Tags = tags;
		AvailableTime = availableTime;
		Price = price;
		Rating = rating;
		TotalVote = totalVote;
		PersonalRating = personalRating;
		RestaurantId = restaurantId;
	}
	
	public float getPersonalRating() {
		return PersonalRating;
	}
	public void setPersonalRating(float personalRating) {
		PersonalRating = personalRating;
	}	
	
	

	public long getRestaurantId() {
		return RestaurantId;
	}
	public void setRestaurantId(long restaurantId) {
		RestaurantId = restaurantId;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getSubCatergory() {
		return SubCatergory;
	}
	
	public void setSubCatergory(String subCatergory) {
		SubCatergory = subCatergory;
	}
	public String getPackage() {
		return Package;
	}
	public void setPackage(String package1) {
		Package = package1;
	}
	public String getTags() {
		return Tags;
	}
	public void setTags(String tags) {
		Tags = tags;
	}
	public String getAvailableTime() {
		return AvailableTime;
	}
	public void setAvailableTime(String availableTime) {
		AvailableTime = availableTime;
	}
	public Double getPrice() {
		return Price;
	}
	public void setPrice(Double price) {
		Price = price;
	}
	public float getRating() {
		return Rating;
	}
	public void setRating(float rating) {
		Rating = rating;
	}
	public long getTotalVote() {
		return TotalVote;
	}
	public void setTotalVote(long totalVote) {
		TotalVote = totalVote;
	}
	
	//PREPARES A MENU FOR PUSH TO LOCAL DB
	public static ContentValues getVal(MenuItem menu)
	{
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.KEY_ID,menu.getId());
		values.put(DatabaseHelper.KEY_NAME,menu.getName());
		values.put(DatabaseHelper.KEY_CATEGORY,menu.getCategory());
		values.put(DatabaseHelper.KEY_TAG,menu.getTags());
		values.put(DatabaseHelper.KEY_SUB_CATEGORY,menu.getSubCatergory());
		values.put(DatabaseHelper.KEY_PACKAGE, menu.getPackage());
		values.put(DatabaseHelper.KEY_AVAILABLE_TIME, menu.getAvailableTime());
		values.put(DatabaseHelper.KEY_PRICE,menu.getPrice());
		values.put(DatabaseHelper.KEY_RATING, menu.getRating());
		values.put(DatabaseHelper.KEY_TOTAL_VOTE,menu.getTotalVote());
		values.put(DatabaseHelper.KEY_RESTAURANT_ID,menu.getRestaurantId());
		return values;
	}
	
	//RETURNS MENU LIST FOR UI
	public static ArrayList<MenuItem> getMenusFromCursor(Cursor cursor)
	{
		ArrayList<MenuItem> Menus=new ArrayList<MenuItem>();
		
		 if (cursor.moveToFirst()) {
		        do {
		            
		        	MenuItem menu = new MenuItem();
		         
		            menu.setId((cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_ID))));
		            menu.setName((cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_NAME))));
		            menu.setTags((cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_TAG))));
		            menu.setPackage((cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_PACKAGE))));
		            menu.setCategory((cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_CATEGORY))));
		            menu.setSubCatergory((cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_SUB_CATEGORY))));
		            menu.setAvailableTime((cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_AVAILABLE_TIME))));
		            menu.setPrice((cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.KEY_PRICE))));
		            menu.setTotalVote((cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_TOTAL_VOTE))));
		            menu.setRating((cursor.getFloat(cursor.getColumnIndex(DatabaseHelper.KEY_RATING))));
		       
		            
		            Menus.add(menu);
		            
		        } while (cursor.moveToNext());
		 }
		
		return Menus;
	}

	
	
	
}
