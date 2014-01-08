package com.anvillab.foodaholic;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import com.anvillab.asynctask.PostRequestTask;
import com.anvillab.utilities.ServiceUrlMapper;
import com.facebook.*;
import com.facebook.model.*;

public class LoginActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Session.openActiveSession(this, true, new Session.StatusCallback() {

			// callback when session changes state
			@SuppressWarnings("deprecation")
			@Override
			public void call(final Session session, SessionState state,
					Exception exception) {
				if (session.isOpened()) {

					// make request to the /me API
					Request.executeMeRequestAsync(session,
							new Request.GraphUserCallback() {
								@Override
								public void onCompleted(GraphUser user,
										Response response) {
									if (user != null) {
										TextView welcome = (TextView) findViewById(R.id.welcome);
										welcome.setText("Hello "+ user.getName() + "!");

										List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(3);
										nameValuePair.add(new BasicNameValuePair("fbid", user.getUsername()));
										nameValuePair.add(new BasicNameValuePair("fbtoken", session.getAccessToken()));
										nameValuePair.add(new BasicNameValuePair("name", user.getName()));
										PostRequestTask obj = new PostRequestTask(getApplicationContext(), nameValuePair);
										obj.execute(ServiceUrlMapper.LOG_IN);
									}

								}
							});
				}
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode,
				resultCode, data);
	}
}
