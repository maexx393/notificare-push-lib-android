package com.example.pushme;

import re.notifica.Notificare;
import android.app.Application;

/**
 * Customisation of the default application class
 * 
 * Since we are using our own intent receiver, we have to tell Notificare engine to 
 * send its intents there
 * 
 * @author Joris Verbogt <joris@notifica.re>
 */
public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		// Launch Notificare system
	    Notificare.shared().launch(this);
	    // Set our own intent receiver
	    Notificare.shared().setIntentReceiver(MyIntentReceiver.class);
	}

}
