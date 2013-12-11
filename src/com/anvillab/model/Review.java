package com.anvillab.model;

public class Review {
	
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
}
