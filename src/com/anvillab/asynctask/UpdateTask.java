package com.anvillab.asynctask;

import com.anvillab.helper.ContentProviderWrapper;
import com.anvillab.model.Review;
import com.anvillab.model.User;
import com.anvillab.utilities.UpdateMapper;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class UpdateTask extends AsyncTask<Integer, Long, Long>{
	
	User user;
	String maxDate;
	Context context;
	Review review;
	long id;
	ContentProviderWrapper wrapper;
	
	public UpdateTask(User user,Context context)
	{
		this.user=user;
		this.context=context;
	}
	
	public UpdateTask(Context context, String maxDate)
	{
		this.context=context;
		this.maxDate=maxDate;
	}
	
	public UpdateTask(Review review,long id,Context context)
	{
		this.review=review;
		this.id=id;
		this.context=context;
	}
	
	@Override
	protected Long doInBackground(Integer...index) {
	
		long result=0;
		wrapper= new ContentProviderWrapper(context);
		
		switch (index[0]) 
		{
		
	        case UpdateMapper.INSERT_USER:
	        	result = wrapper.createUser(user);	
	            break;
	                 
	        case UpdateMapper.UPDATE_MAX_DATE:  
	        	result = wrapper.updateUserSyncFields(maxDate, 0);
                break;         
	
	        case UpdateMapper.INSERT_RESTAURANT_RATING:  
	            break;
	                 
	        case UpdateMapper.INSERT_MENU_RATING:  
	        	break;         
		}
		return result;
	}
	
	@Override
	protected void onPostExecute(Long result) {
		super.onPostExecute(result);
		ShowToast("inserted row: "+result.toString());
	}
	
	void ShowToast(String message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

}
