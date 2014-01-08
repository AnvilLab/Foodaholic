package com.anvillab.asynctask;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import com.anvillab.helper.ParseHelper;
import com.anvillab.model.DBLog;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


public class GetRequestTask extends AsyncTask<String, String, String>{

	Context context;
	
	public GetRequestTask(Context _context)
	{
		context=_context;			
	}
	
    @Override
    protected String doInBackground(String... uri) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        try {
        	//formulating Get request
        	HttpGet get = new HttpGet(uri[0]);
            response = httpclient.execute(get);
            
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK)
            {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
            } 
            else
            {
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            //TODO Handle problems..
        } catch (IOException e) {
            //TODO Handle problems..
        }
       
        return responseString;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
       
        try {
        	ShowToast("Server call returned");
			ArrayList<DBLog> logs= ParseHelper.DBLogParser(result);
			ScriptExecutingTask dataInsertTask=new ScriptExecutingTask(context,logs);
			dataInsertTask.execute("");			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    void ShowToast(String message)
    {    	
    	 Toast.makeText(context,message,10).show();
    }
    
    
    
}