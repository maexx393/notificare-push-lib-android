package com.example.pushtest;

import re.notifica.push.gcm.BaseActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

/**
 * Main Activity class. Doesn't actually do anything
 * @author Joris Verbogt <joris@notifica.re>
 */
public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (getIntent().getAction().equals(Intent.ACTION_VIEW)) {
			Toast.makeText(getApplication(), getIntent().getDataString(), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}