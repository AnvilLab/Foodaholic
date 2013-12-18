package com.anvillab.model;

public class MenuItem {
	
	//sqlite test constructor
	public MenuItem(long id, String name, String category, String subCatergory,
			String package1, String tags, String availableTime, Double price,
			float rating, long totalVote, float personalRating,
			float aggregateRating, long restaurantId) {
		super();
		this.id = id;
		Name = name;
		Category = category;
		SubCatergory = subCatergory;
		Package = package1;
		Tags = tags;
		AvailableTime = availableTime;
		Price = price;
		Rating = rating;
		TotalVote = totalVote;
		this.personalRating = personalRating;
		RestaurantId = restaurantId;
	}
	
	public long id;
	public String Name;
	public String Category;
	public String SubCatergory;
	public String Package;
	public String Tags;
	public String AvailableTime;
	public Double Price;
	public float Rating;
	public long TotalVote;
	public float personalRating;
	public long RestaurantId;
	
	public MenuItem()
	{
		
	}
	
	public float getPersonalRating() {
		return personalRating;
	}
	public void setPersonalRating(float personalRating) {
		this.personalRating = personalRating;
	}	
	
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
	
}
