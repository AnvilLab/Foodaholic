package com.anvillab.asynctask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;

import com.anvillab.helper.ParseHelper;
import com.anvillab.model.User;
import com.anvillab.utilities.UpdateMapper;
import com.anvillab.utilities.Utilities;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class PostRequestTask extends AsyncTask<String, String, String> {

	Context context;
	List<NameValuePair> nameValuePair;

	public PostRequestTask(Context _context, List<NameValuePair> paramValuePair) {
		
		context = _context;
		nameValuePair = paramValuePair;
		
	}

	@Override
	protected String doInBackground(String... uri) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		String responseString = null;
		try {

			HttpPost post = new HttpPost(uri[0]);
			post.setEntity(new UrlEncodedFormEntity(nameValuePair));
			response = httpclient.execute(post);

			StatusLine statusLine = response.getStatusLine();
			
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();
			} else {
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		
		} catch (ClientProtocolException e) {
			
		} catch (IOException e) {
			
		}

		return responseString;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);

		try {
			
			User user = ParseHelper.UserParser(result);
			Utilities.savePreferences(context, "userId", String.valueOf(user.getUserId()));
			UpdateTask updateTask=new UpdateTask(user,context);
			updateTask.execute(UpdateMapper.INSERT_USER);
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
	}

	void ShowToast(String message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

}