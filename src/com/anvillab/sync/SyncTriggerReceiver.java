package com.anvillab.sync;

import com.anvillab.sync.SyncUtilities;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class SyncTriggerReceiver  extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		ContentResolver.requestSync(SyncUtilities.mAccount,SyncUtilities.AUTHORITY,new Bundle());
		
	}

}
