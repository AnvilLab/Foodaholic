package com.anvillab.sync;

import java.io.Console;
import java.util.ArrayList;

import com.anvillab.foodaholic.MainActivity;
import com.anvillab.foodaholic.R;
import com.anvillab.helper.ContentProviderWrapper;
import com.anvillab.helper.DatabaseHelper;
import com.anvillab.model.DBLog;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

@SuppressLint("NewApi")
public class SyncAdapter extends AbstractThreadedSyncAdapter {
   
    // Global variables
    // Define a variable to contain a content resolver instance
	ContentProviderWrapper wrapper;
    ContentResolver mContentResolver;
    private NotificationManager mManager; 
    DatabaseHelper dbHelper;
    Context context;
    
    /**
     * Set up the sync adapter
     */
    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();
        this.context=context;
        wrapper=new ContentProviderWrapper(context);
        dbHelper=new DatabaseHelper(context);
    }
    
    /**
     * Set up the sync adapter. This form of the
     * constructor maintains compatibility with Android 3.0
     * and later platform versions
     */
    public SyncAdapter(
            Context context,
            boolean autoInitialize,
            boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();
       
    }
    
    /*
     * Specify the code you want to run in the sync adapter. The entire
     * sync adapter runs in a background thread, so you don't have to set
     * up your own background processing.
     */
    @Override
    public void onPerformSync(
            Account account,
            Bundle extras,
            String authority,
            ContentProviderClient provider,
            SyncResult syncResult) {
    
         
         String requestDate = wrapper.getMaxDateFromDatabase();
         SyncUtilities.getDatabaseScripts(requestDate);
         try{
         
        	 ArrayList<DBLog> logs = SyncUtilities.getDatabaseScripts(requestDate);
	         if(logs.size()>0)
	         {
	        	 dbHelper.executeScript(logs);
	        	 String maxDate = DBLog.getMaxDate(logs);
	        	 wrapper.updateUserSyncFields(maxDate, 0);
	         }
	         
         }
         catch(Exception e)
         {
        	 Log.d("Ex", e.getMessage());
         }
         
    }
}
