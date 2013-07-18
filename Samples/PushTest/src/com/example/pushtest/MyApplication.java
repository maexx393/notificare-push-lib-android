package com.example.pushtest;

import com.example.pushtest.receivers.IntentReceiver;

import re.notifica.Notificare;
import android.app.Application;

/**
 * Use the application class to setup Notificare
 * @author Joris Verbogt <joris@notifica.re>
 */
public class MyApplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		// Launch Notificare system
	    Notificare.shared().launch(this);
		//Set our own class to handle incoming push messages.
	    Notificare.shared().setIntentReceiver(IntentReceiver.class);
	    // Enable this device for push notifications
	    Notificare.shared().enableNotifications();
	}

}
