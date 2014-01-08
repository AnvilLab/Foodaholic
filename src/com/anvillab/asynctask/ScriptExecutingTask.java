package com.anvillab.asynctask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import com.anvillab.helper.DatabaseHelper;
import com.anvillab.model.DBLog;
import com.anvillab.utilities.UpdateMapper;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


public class ScriptExecutingTask extends AsyncTask<String, String, String>{

	Context context;
	ArrayList<DBLog> dbLogs;
	DatabaseHelper dbHelper;
	
	public ScriptExecutingTask(Context _context,ArrayList<DBLog> dbLogs)
	{
		context=_context;
		this.dbLogs=dbLogs;
		this.dbHelper=new DatabaseHelper(context);
	}
	
    @Override
    protected String doInBackground(String... uri) {
        
    	dbHelper.executeScript(dbLogs);
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        String maxDate = DBLog.getMaxDate(dbLogs);
        UpdateTask updateTask=new UpdateTask(context,maxDate);
        updateTask.execute(UpdateMapper.UPDATE_MAX_DATE);
    }
    
    
    void ShowToast(String message)
    {    	
    	 Toast.makeText(context,message,10).show();
    }
    
    
    
}