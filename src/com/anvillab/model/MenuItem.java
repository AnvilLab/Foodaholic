package com.anvillab.model;

public class MenuItem {
	
	public long id;
	public String Name;
	public String Category;
	public String SubCatergory;
	public String Package;
	public String Tags;
	public String AvailableTime;
	public Double Price;
	public Double Rating;
	public long TotalVote;
	public long RestaurantId;
	
	public long getRestaurantId() {
		return RestaurantId;
	}
	public void setRestaurantId(long restaurantId) {
		RestaurantId = restaurantId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public Double getRating() {
		return Rating;
	}
	public void setRating(Double rating) {
		Rating = rating;
	}
	public long getTotalVote() {
		return TotalVote;
	}
	public void setTotalVote(long totalVote) {
		TotalVote = totalVote;
	}
	
}
