package com.anvillab.sync;

import java.util.Calendar;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class SyncUtilities {
	
	public static final String AUTHORITY = "com.anvillab.helper.DataProvider";
	
	public static final long MILLISECONDS_PER_SECOND = 1000L;
	public static final long SECONDS_PER_MINUTE = 60L;
	public static final long SYNC_INTERVAL_IN_MINUTES = 2L;
	public static final long SYNC_INTERVAL =
	            SYNC_INTERVAL_IN_MINUTES *
	            SECONDS_PER_MINUTE *
	            MILLISECONDS_PER_SECOND;
	
    public static final String ACCOUNT_TYPE = "com.udinic.auth_example";
    public static Account mAccount;
	
	public static Account CreateSyncAccount(Context context, String accountName) {
		
		deleteAccounts(context);
		
        // Create the account type and default account
        Account newAccount = new Account(
                accountName, ACCOUNT_TYPE);
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(
                        context.ACCOUNT_SERVICE);
     
        
        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call context.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */
        } else {
            /*
             * The account exists or some other error occurred. Log this, report it,
             * or handle it internally.
             */
        }
        
        return newAccount;
    }
	
	public static void deleteAccounts(Context context)
	{
		 AccountManager accountManager =
	                (AccountManager) context.getSystemService(
	                        context.ACCOUNT_SERVICE);
		
		Account[] accounts = accountManager.getAccountsByType(ACCOUNT_TYPE);
		
		if(accounts.length > 0)
		{
			for (int i = 0; i < accounts.length; i++) 
				accountManager.removeAccount(accounts[i], null, null);
		}
	}
	
	public static void setAccountAndSync(Context context,String accountName)
	{
		Bundle bundle=new Bundle();
		mAccount=CreateSyncAccount(context,accountName);
	
		ContentResolver.setIsSyncable(mAccount, AUTHORITY, 1);
		ContentResolver.setSyncAutomatically(mAccount, AUTHORITY, true);
		
		setSyncSchedule(context);
    
	}
	
	public static void setSyncSchedule(Context context)
	{
		PendingIntent pendingIntent;
		Calendar calendar = Calendar.getInstance();
	   
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.set(Calendar.HOUR_OF_DAY, 8);
		calendar.set(Calendar.MINUTE, 55);
	     
	    Intent myIntent = new Intent(context, SyncTriggerReceiver.class);
	    pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent,0);
	     
	    AlarmManager alarmManager = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
	    alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
	    
	    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
	            1000 * 60 * 1, pendingIntent);
	}
}
