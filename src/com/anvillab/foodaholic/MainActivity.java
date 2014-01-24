package com.anvillab.foodaholic;


import java.util.ArrayList;
import java.util.Date;

import com.anvillab.asynctask.GetRequestTask;
import com.anvillab.helper.ContentProviderWrapper;
import com.anvillab.helper.DatabaseHelper;
import com.anvillab.model.DBLog;
import com.anvillab.model.MenuItem;
import com.anvillab.model.Restaurant;
import com.anvillab.model.Review;
import com.anvillab.model.User;
import com.anvillab.sync.SyncUtilities;
import com.anvillab.utilities.ServiceUrlMapper;

import android.widget.Button;
import android.widget.Toast;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;


public class MainActivity extends Activity  {

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
		
		/*createRestaurants();
		createMenus();*/
		
		Intent intent = new Intent(this, LoaderTestActivity.class);
		startActivity(intent);
	}
	
	//CREATE ACCOUNT AND SET SYNC SETTINGS
	public void setSync(View view)
	{
		Cursor c = wrapper.getRestaurants();
		Toast.makeText(getApplicationContext(), String.valueOf(c.getCount()), 50).show();
	}
	
	public void insertRating()
	{
		Review review=new Review((float)5.0,"Good",1,1);
		wrapper.rateRestaurant(review);
		
	}
	
	public void createRestaurants()
	{
		for(int i=1;i<=10;i++)
		{
			Restaurant restaurant=new Restaurant(i,"Pizza Hut","Dhanmondi 12/A", "Dhaka", "123",
					"go", "str", "str",
					"Music", "Music Cafe", "str",
					"Active", 120, 4.5,120);
			wrapper.createRestaurant(restaurant);
		}
	}
	
	public void createMenus()
	{
		MenuItem menu;
		for(long i=1;i<=5;i++)
		{
			if(i%2 == 0) menu= new MenuItem(12+i,"menu" ,"evCategory","pizza","package","tag","x",10.0,3,10,5,1);
			else menu= new MenuItem(12+i,"menu" ,"odCategory","burger","package","tag","x",10.0,3,10,5,2);
			wrapper.createMenu(menu);
		}
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

