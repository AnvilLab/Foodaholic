package com.anvillab.foodaholic;


import java.util.ArrayList;

import com.anvillab.helper.ContentProviderWrapper;
import com.anvillab.helper.DatabaseHelper;
import com.anvillab.helper.DataProvider;
import com.anvillab.model.Restaurant;
import com.anvillab.model.MenuItem;
import android.widget.Toast;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;


public class MainActivity extends Activity {

	DatabaseHelper db;
	ContentProviderWrapper wrapper;
	public static final Uri CONTENT_URI = Uri.parse("content://" + DataProvider.AUTHORITY
		      + "/" + DataProvider.MENU_TABLE);
	
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
