package com.anvillab.foodaholic;


import java.util.Date;

import com.anvillab.helper.ContentProviderWrapper;
import com.anvillab.helper.DatabaseHelper;
import com.anvillab.model.Restaurant;
import com.anvillab.model.Review;
import com.anvillab.model.User;
import com.anvillab.sync.SyncUtilities;
import android.widget.Button;
import android.widget.Toast;


import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;


public class MainActivity extends Activity {

	DatabaseHelper db;
	int userCount=0;
	ContentProviderWrapper wrapper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		db=new DatabaseHelper(this);
		wrapper=new ContentProviderWrapper(this.getApplicationContext());
		
	}
	
	public void sendMessage(View view) {
	    insertRating();
	}
	
	//CREATE ACCOUNT AND SET SYNC SETTINGS
	public void setSync(View view)
	{
		createUser();
		createRestaurants();
		insertRating();
		Toast.makeText(this, String.valueOf((wrapper.getPersonalResturantRating(1, 1))), Toast.LENGTH_LONG).show();
		//SyncUtilities.setAccountAndSync(getApplicationContext(), "MAGNETO");
	}
	
	public void insertRating()
	{
		Review review=new Review((float)3.5,"Good",1);
		wrapper.rateRestaurant(review, 1);
		
	}
	
	public void createRestaurants()
	{
		Restaurant restaurant=new Restaurant(2,"Pizza Hut", "Dhaka", "123",
				"go", "str", "str",
				"str", "str", "str",
				"Active", 120, 4.5,120);
		wrapper.createRestaurant(restaurant);
	}
	
	
	
	public void createUser()
	{
		userCount++;
		User user = new User(1,"galib2145","Kx55ttU",new Date());
		wrapper.createUser(user);
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
    
	
	
}

