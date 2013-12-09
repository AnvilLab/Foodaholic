package com.anvillab.model;

public class Restaurant {
	
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
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getTotalVote() {
		return TotalVote;
	}

	public void setTotalVote(long totalVote) {
		TotalVote = totalVote;
	}

	
	public long id;	
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
	public long TotalVote;
		
}
