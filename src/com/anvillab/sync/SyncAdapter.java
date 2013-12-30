package com.anvillab.sync;

import com.anvillab.foodaholic.MainActivity;
import com.anvillab.foodaholic.R;

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

@SuppressLint("NewApi")
public class SyncAdapter extends AbstractThreadedSyncAdapter {
   
    // Global variables
    // Define a variable to contain a content resolver instance
    ContentResolver mContentResolver;
    private NotificationManager mManager; 
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
    
    	
    	//test code
    	 mManager = (NotificationManager) context.getSystemService(this.context.NOTIFICATION_SERVICE);
         Intent intent1 = new Intent(this.context,MainActivity.class);
       
         Notification notification = new Notification(R.drawable.ic_launcher,"This is a test message!", System.currentTimeMillis());
         intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
   
         PendingIntent pendingNotificationIntent = PendingIntent.getActivity( this.context,0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
         notification.flags |= Notification.FLAG_AUTO_CANCEL;
         notification.setLatestEventInfo(context, "AlarmManagerDemo", "This is a test message!", pendingNotificationIntent);
   
         mManager.notify(0, notification);
    	
    }
}
