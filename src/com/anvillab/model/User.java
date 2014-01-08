package com.anvillab.model;

import java.util.Date;

import android.content.ContentValues;
import com.anvillab.foodaholic.*;
import com.anvillab.helper.DatabaseHelper;
import com.anvillab.utilities.Utilities;

public class User {
	
	public long userId;
	public String facebookId;
	public String credential;
	public Date lastUpdatedTime;
	public long lastUpdatedId;
	
	
	public User(long userId, String facebookId, String credential,
			Date lastUpdatedTime, long lastUpdatedId) {
		super();
		this.userId = userId;
		this.facebookId = facebookId;
		this.credential = credential;
		this.lastUpdatedId=lastUpdatedId;
		this.lastUpdatedTime = lastUpdatedTime;
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getCredential() {
		return credential;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public long getLastUpdatedId() {
		return lastUpdatedId;
	}

	public void setLastUpdatedId(long lastUpdatedId) {
		this.lastUpdatedId = lastUpdatedId;
	}

	public static ContentValues getVal(User user)
	{
		ContentValues values = new ContentValues();
		
		values.put(DatabaseHelper.KEY_ID, user.getUserId());
		values.put(DatabaseHelper.KEY_FACEBOOK_ID, user.getFacebookId());
		values.put(DatabaseHelper.KEY_CREDENTIAL, user.getCredential());
		values.put(DatabaseHelper.KEY_LAST_UPDATED, Utilities.getFormattedDate(user.getLastUpdatedTime()));
		values.put(DatabaseHelper.KEY_LAST_UPDATED_ID, user.getLastUpdatedId());
		
		return values;
		
	}
	
	
	
}
