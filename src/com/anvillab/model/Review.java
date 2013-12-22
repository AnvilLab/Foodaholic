package com.anvillab.model;

import com.anvillab.helper.DatabaseHelper;

import android.content.ContentValues;

public class Review {
	
	public static final int RESTAURANT=1;
	public static final int MENU=2;
	
	public float Rating;
	public long UserId;
	public String Review;
	
	public Review(float rating, String review) {
		super();
		Rating = rating;
		Review = review;
	}
	
	public long getUserId() {
		return UserId;
	}

	public void setUserId(long userId) {
		UserId = userId;
	}

	public float getRating() {
		return Rating;
	}

	public void setRating(float rating) {
		Rating = rating;
	}

	public String getReview() {
		return Review;
	}

	public void setReview(String review) {
		Review = review;
	} 
	
	public static ContentValues getVal(Review review,int choice,long id)
	{
		ContentValues values = new ContentValues();
	    values.put(DatabaseHelper.KEY_RATING, 	review.getRating());
	    values.put(DatabaseHelper.KEY_REVIEW, review.getReview());
	    
	    if(choice==1) values.put(DatabaseHelper.KEY_RESTAURANT_ID, id);
	    else values.put(DatabaseHelper.KEY_MENU_ID, id);
	    
	    values.put(DatabaseHelper.KEY_USER_ID, review.getUserId());
	    
	    return values;
	}
}
