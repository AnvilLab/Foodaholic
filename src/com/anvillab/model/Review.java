package com.anvillab.model;

public class Review {
	
	public float Rating;
	public String Credential;
	public String Review;
	
	public Review(float rating, String review) {
		super();
		Rating = rating;
		Review = review;
	}
	
	public String getCredential() {
		return Credential;
	}

	public void setCredential(String credential) {
		Credential = credential;
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
