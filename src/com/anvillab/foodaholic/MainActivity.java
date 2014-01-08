package com.anvillab.foodaholic;


import java.util.Date;

import com.anvillab.asynctask.GetRequestTask;
import com.anvillab.helper.ContentProviderWrapper;
import com.anvillab.helper.DatabaseHelper;
import com.anvillab.model.Restaurant;
import com.anvillab.model.Review;
import com.anvillab.model.User;
import com.anvillab.sync.SyncUtilities;
import com.anvillab.utilities.ServiceUrlMapper;

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
		//createUser();
		//createRestaurants();
		insertRating();
		Toast.makeText(getApplicationContext(), String.valueOf(wrapper.getPersonalResturantRating(1, 1)), Toast.LENGTH_LONG).show();
	}
	
	//CREATE ACCOUNT AND SET SYNC SETTINGS
	public void setSync(View view)
	{
	
	}
	
	public void insertRating()
	{
		Review review=new Review((float)5.0,"Good",1);
		wrapper.rateRestaurant(review, 1);
		
	}
	
	public void createRestaurants()
	{
		Restaurant restaurant=new Restaurant(1,"Pizza Hut", "Dhaka", "123",
				"go", "str", "str",
				"str", "str", "str",
				"Active", 120, 4.5,120);
		wrapper.createRestaurant(restaurant);
	}
	
	
	
	public void createUser()
	{
		
		User user = new User(1,"galib2145","Kx55ttU",new Date(),6);
		wrapper.createUser(user);
		user = new User(2,"galib2145","Kx55ttU",new Date(),7);
		wrapper.createUser(user);
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
    
	
	
}

