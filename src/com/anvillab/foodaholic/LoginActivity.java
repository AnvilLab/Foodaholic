package com.anvillab.foodaholic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import com.anvillab.asynctask.PostRequestTask;
import com.anvillab.utilities.Utilities;
import com.facebook.*;
import com.facebook.model.*;

public class LoginActivity extends Activity {

	Button logoutbutton;
	String facebookId, facebookToken, facebookName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		PackageInfo info;
		try {
			info = getPackageManager().getPackageInfo(
					"com.anvillab.foodaholic", PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md;
				md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				String something = new String(Base64.encode(md.digest(), 0));
				Log.e("hash key", something);
			}
		} catch (NameNotFoundException e1) {
			Log.e("name not found", e1.toString());
		} catch (NoSuchAlgorithmException e) {
			Log.e("no such an algorithm", e.toString());
		} catch (Exception e) {
			Log.e("exception", e.toString());
		}
		

		Session.openActiveSession(this, true, new Session.StatusCallback() {
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
										facebookId = user.getUsername();
										facebookToken = session.getAccessToken();
										facebookName = user.getName();
										
										Utilities.savePreferences(getApplicationContext(), "facebookId", facebookId);
										Utilities.savePreferences(getApplicationContext(), "accessToken", facebookToken);


										List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(
												3);
										nameValuePair.add(new BasicNameValuePair(
												"fbid", facebookId ));
										nameValuePair.add(new BasicNameValuePair(
												"fbtoken", facebookToken ));
										nameValuePair.add(new BasicNameValuePair(
												"name", facebookName ));
										PostRequestTask obj = new PostRequestTask(
												getApplicationContext(),
												nameValuePair);
										obj.execute("http://anvillab.com/food/dummy_entry/api/login");
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
