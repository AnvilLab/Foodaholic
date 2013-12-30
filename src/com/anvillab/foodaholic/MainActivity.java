package com.anvillab.foodaholic;


import com.anvillab.helper.ContentProviderWrapper;
import com.anvillab.helper.DatabaseHelper;
import com.anvillab.model.User;
import com.anvillab.sync.SyncUtilities;
import android.widget.Button;
import android.widget.Toast;


import android.os.Bundle;
import android.app.Activity;
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
		//wrapper=new ContentProviderWrapper(this.getApplicationContext());
	}
	
	public void sendMessage(View view) {
	    
	}
	
	//CREATE ACCOUNT AND SET SYNC SETTINGS
	public void setSync(View view)
	{
		SyncUtilities.setAccountAndSync(getApplicationContext(), "MAGNETO");
	}
	
	public void createUser()
	{
		/*userCount++;
		User user = new User(userCount,"galib2145","Kx55ttU",new Date());
		wrapper.createUser(user);*/
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
    
	
	
}

