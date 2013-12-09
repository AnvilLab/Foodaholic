package com.anvillab.foodaholic;


import com.anvillab.helper.DatabaseHelper;
import com.anvillab.model.Restaurant;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	DatabaseHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		db = new DatabaseHelper(getApplicationContext());
		 
		Restaurant restaurant=new Restaurant("a","b","c","d","e","f","g","h","i","j",40,4.4,3500);
		long num=db.createRestaurant(restaurant);
		
		Toast.makeText(getApplicationContext(), String.valueOf(num), 
				   Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
