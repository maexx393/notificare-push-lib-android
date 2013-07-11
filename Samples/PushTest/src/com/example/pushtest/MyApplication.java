package com.example.pushtest;

import com.example.pushtest.receivers.IntentReceiver;

import re.notifica.Notificare;
import android.app.Application;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//Set our own class to handle incoming push messages.
	    Notificare.shared().setIntentReceiver(IntentReceiver.class);
	    Notificare.shared().launch(this);
	    Notificare.shared().enableNotifications();
	}

}
