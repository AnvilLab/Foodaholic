package com.anvillab.model;

import com.anvillab.helper.DatabaseHelper;

import android.content.ContentValues;

public class Review {
	
	public static final int RESTAURANT=1;
	public static final int MENU=2;
	
	public float Rating;
	public long UserId;
	public String Review;
	public long AssociatedId;
	
	public Review(float rating, String review, long userId,long associatedId) {
		super();
		UserId = userId;
		Rating = rating;
		Review = review;
		AssociatedId = associatedId;
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
	
	public long getAssociatedId() {
		return AssociatedId;
	}

	public void setAssociatedId(long associatedId) {
		AssociatedId = associatedId;
	}
	
	public static ContentValues getVal(Review review,int choice)
	{
		ContentValues values = new ContentValues();
	    values.put(DatabaseHelper.KEY_RATING, 	review.getRating());
	    values.put(DatabaseHelper.KEY_REVIEW, review.getReview());
	    
	    if(choice==1) values.put(DatabaseHelper.KEY_RESTAURANT_ID,review.getAssociatedId());
	    else values.put(DatabaseHelper.KEY_MENU_ID, review.getAssociatedId());
	    
	    values.put(DatabaseHelper.KEY_USER_ID, review.getUserId());
	    values.put(DatabaseHelper.KEY_IS_UPDATED, 0);
	    
	    return values;
	}

	
}
