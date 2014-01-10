package com.anvillab.model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

import com.anvillab.helper.*;

public class Restaurant {
	
	public long Id;	
	public boolean HasSmokingZone;
	public boolean HasAC;
	public boolean HasWIFI;
	public boolean HasParking;
	public String Name;
	public String Address;
	public String Location;
	public String ContactNo;	
	public String OpenDuration;
	public String Holiday;
	public String SpecialFeatures;
	public String PrimeType;
	public String Standard;
	public String ActiveStatus;
	public int SeatCapacity;
	public Double Rating;
	public String LocationTag;
	public long TotalVote;
	public float PersonalRating;
	
	
	
	public Restaurant()
	{
		
	}
	
	public Restaurant( String name, String address, String location,
			String contactNo, String openDuration, String holiday,
			String specialFeatures, String primeType, String standard,
			String activeStatus, int seatCapacity, Double rating,long totalVote) {
		super();
		Name = name;
		Address = address;
		Location = location;
		ContactNo = contactNo;
		OpenDuration = openDuration;
		Holiday = holiday;
		SpecialFeatures = specialFeatures;
		PrimeType = primeType;
		Standard = standard;
		ActiveStatus = activeStatus;
		SeatCapacity = seatCapacity;
		Rating = rating;
		TotalVote=totalVote;
	}
	
	

	public Restaurant(long id, String name,String locationTag, String address, String location,
			String contactNo, String openDuration, String holiday,
			String specialFeatures, String primeType, String standard,
			String activeStatus, int seatCapacity, Double rating,long totalVote) {
		super();
		
		Id=id;
		Name = name;
		Address = address;
		Location = location;
		LocationTag=locationTag;
		ContactNo = contactNo;
		OpenDuration = openDuration;
		Holiday = holiday;
		SpecialFeatures = specialFeatures;
		PrimeType = primeType;
		Standard = standard;
		ActiveStatus = activeStatus;
		SeatCapacity = seatCapacity;
		Rating = rating;
		TotalVote=totalVote;
	}
	
	public String getLocationTag() {
		return LocationTag;
	}

	public void setLocationTag(String locationTag) {
		LocationTag = locationTag;
	}
		
	public float getPersonalRating() {
		return PersonalRating;
	}
	public void setPersonalRating(float personalRating) {
		this.PersonalRating = personalRating;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getContactNo() {
		return ContactNo;
	}
	public void setContactNo(String contactNo) {
		ContactNo = contactNo;
	}
	public String getOpenDuration() {
		return OpenDuration;
	}
	public void setOpenDuration(String openDuration) {
		OpenDuration = openDuration;
	}
	public String getHoliday() {
		return Holiday;
	}
	public void setHoliday(String holiday) {
		Holiday = holiday;
	}
	public String getSpecialFeatures() {
		return SpecialFeatures;
	}
	public void setSpecialFeatures(String specialFeatures) {
		SpecialFeatures = specialFeatures;
	}
	public String getPrimeType() {
		return PrimeType;
	}
	public void setPrimeType(String primeType) {
		PrimeType = primeType;
	}
	public String getStandard() {
		return Standard;
	}
	public void setStandard(String standard) {
		Standard = standard;
	}
	public String getActiveStatus() {
		return ActiveStatus;
	}
	public void setActiveStatus(String activeStatus) {
		ActiveStatus = activeStatus;
	}
	public int getSeatCapacity() {
		return SeatCapacity;
	}
	public void setSeatCapacity(int seatCapacity) {
		SeatCapacity = seatCapacity;
	}
	public Double getRating() {
		return Rating;
	}
	public void setRating(Double rating) {
		Rating = rating;
	}
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	
	public long getTotalVote() {
		return TotalVote;
	}

	public void setTotalVote(long totalVote) {
		TotalVote = totalVote;
	}
	
	public boolean isHasSmokingZone() {
		return HasSmokingZone;
	}

	public void setHasSmokingZone(boolean hasSmokingZone) {
		HasSmokingZone = hasSmokingZone;
	}

	public boolean isHasAC() {
		return HasAC;
	}

	public void setHasAC(boolean hasAC) {
		HasAC = hasAC;
	}

	public boolean isHasWIFI() {
		return HasWIFI;
	}

	public void setHasWIFI(boolean hasWIFI) {
		HasWIFI = hasWIFI;
	}

	public boolean isHasParking() {
		return HasParking;
	}

	public void setHasParking(boolean hasParking) {
		HasParking = hasParking;
	}
	
	//PREPARES A RESTAURANT FOR PUSH TO LOCAL DB
	public static ContentValues getVal(Restaurant restaurant)
	{
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.KEY_ID,restaurant.getId());
		values.put(DatabaseHelper.KEY_NAME,restaurant.getName());
		values.put(DatabaseHelper.KEY_ADDRESS,restaurant.getAddress());
		values.put(DatabaseHelper.KEY_LOCATION,restaurant.getLocation());
		values.put(DatabaseHelper.KEY_PRIME_TYPE, restaurant.getPrimeType());
		values.put(DatabaseHelper.KEY_OPEN_DURATION, restaurant.getOpenDuration());
		values.put(DatabaseHelper.KEY_CONTACT, restaurant.getContactNo());
		values.put(DatabaseHelper.KEY_HOLIDAY, restaurant.getHoliday());
		values.put(DatabaseHelper.KEY_ACTIVE_STATUS, restaurant.getActiveStatus());
		values.put(DatabaseHelper.KEY_SPECIAL_FEATURES, restaurant.getSpecialFeatures());
		values.put(DatabaseHelper.KEY_SEAT_CAPACITY, restaurant.getSeatCapacity());
		values.put(DatabaseHelper.KEY_RATING, restaurant.getRating());
		values.put(DatabaseHelper.KEY_TOTAL_VOTE, restaurant.getTotalVote());
		values.put(DatabaseHelper.KEY_STANDARD, restaurant.getStandard());
		values.put(DatabaseHelper.KEY_LOCATION_TAG, restaurant.getLocationTag());
		
		return values;
	}
	
	public static Restaurant populateFeatures (Restaurant restaurant){
		
		String line = restaurant.SpecialFeatures;
		String features[] = line.split(",");  //w,a,p,s
		int count = features.length;
		
		for(int i=0;i<count;i++){
			if(features[i].equalsIgnoreCase("w"))
				restaurant.HasWIFI = true;
			if(features[i].equalsIgnoreCase("a"))
				restaurant.HasAC = true;
			if(features[i].equalsIgnoreCase("p"))
				restaurant.HasParking = true;
			if(features[i].equalsIgnoreCase("s"))
				restaurant.HasSmokingZone = true;	
		}
		
		return restaurant;
		
	}
	
	//RETURNS RESTAURANT LIST FOR UI
	public static ArrayList<Restaurant> getRestaurantsFromCursor(Cursor cursor)
	{
		ArrayList<Restaurant> restaurants=new ArrayList<Restaurant>();
		
		Restaurant restaurant = new Restaurant();
		

		if (cursor.moveToFirst()) {
	        do {
					restaurant.setId((cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_ID))));
					restaurant.setName((cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_NAME))));
			        restaurant.setPrimeType((cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_PRIME_TYPE))));
			        restaurant.setStandard((cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_STANDARD))));
			        restaurant.setContactNo((cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_CONTACT))));
			        restaurant.setAddress((cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_ADDRESS))));
			        restaurant.setOpenDuration((cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_OPEN_DURATION))));
			        restaurant.setSpecialFeatures((cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_SPECIAL_FEATURES))));
			        restaurant.setTotalVote((cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_TOTAL_VOTE))));
			        restaurant.setRating((cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.KEY_RATING))));
			        restaurant.setSeatCapacity((cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_SEAT_CAPACITY))));
			        restaurant.setActiveStatus((cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_ACTIVE_STATUS))));
			        restaurant.setHoliday((cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_HOLIDAY))));
			        restaurant.setLocation((cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_LOCATION))));
			      
			        //FORMULATE PERSONAL RATING
			        
	        } while (cursor.moveToNext());
		 }
		
		return restaurants;
	}
		
}
