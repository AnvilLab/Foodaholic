package com.anvillab.foodaholic;

import android.database.Cursor;
import com.anvillab.helper.CursorLoaderFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.widget.Toast;


public class LoaderTestActivity extends FragmentActivity  implements LoaderManager.LoaderCallbacks<Cursor> {

	CursorLoaderFactory loaderFactory;
	String[] params = {"Dhaka","Category"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loader_test);
		loaderFactory = new CursorLoaderFactory(this);
		initializeLoader(1,params);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.loader_test, menu);
		return true;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle params) {
		return loaderFactory.setupLoader(params.getStringArray("params"), id);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
	
		Toast.makeText(this, String.valueOf(cursor.getCount()), Toast.LENGTH_LONG).show();
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		
		
	}
	
	void initializeLoader(int id, String[] params)
	{
		Bundle bundle = new Bundle();
		bundle.putStringArray("params", params);
		this.getSupportLoaderManager().initLoader(id, bundle, this);
	}

}
