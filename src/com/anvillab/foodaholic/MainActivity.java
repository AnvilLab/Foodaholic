package com.anvillab.foodaholic;


import java.util.ArrayList;

import com.anvillab.helper.DatabaseHelper;
import com.anvillab.model.Restaurant;
import com.anvillab.model.MenuItem;
import android.widget.Toast;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;


public class MainActivity extends Activity {

	DatabaseHelper db; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
}
