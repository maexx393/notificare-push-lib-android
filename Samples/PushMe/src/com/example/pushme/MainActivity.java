package com.example.pushme;

import java.util.ArrayList;

import re.notifica.Notificare;
import re.notifica.NotificareCallback;
import re.notifica.NotificareError;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

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
	        }

	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
