package com.example.pushme;

import java.util.ArrayList;

import re.notifica.Notificare;
import re.notifica.NotificareCallback;
import re.notifica.NotificareError;
import re.notifica.model.NotificareUserPreference;
import re.notifica.push.gcm.BaseActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "Activity launched with action " + getIntent().getAction() + " and data " + getIntent().getData());
		setContentView(R.layout.activity_main);

		final Button button = (Button) findViewById(R.id.button1);
	    button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ArrayList<String> tags = new ArrayList<String>();
				tags.add("1234");
	            Notificare.shared().addDeviceTags(tags, new NotificareCallback<Boolean>() {

					@Override
					public void onError(NotificareError e) {
						Log.e(TAG, e.getMessage());
					}

					@Override
					public void onSuccess(Boolean result) {
						Log.d(TAG, "added tag");
					}
				});
	            NotificareUserPreference pref = Notificare.shared().getUserPreferences().get(0);
	            String segmentId = pref.getPreferenceOptions().get(0).getUserSegmentId();
	            Notificare.shared().userSegmentAddToUserPreference(segmentId, pref, new NotificareCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean result) {
						Log.d(TAG, "added segment");
					}

					@Override
					public void onError(NotificareError error) {
						Log.e(TAG, "error adding segment: " + error.getMessage(), error);
					}
				});
	        }

	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_beacons) {
			startActivity(new Intent(this, InboxActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}



}
