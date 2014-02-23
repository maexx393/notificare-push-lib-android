package com.example.pushme;

import re.notifica.Notificare;
import re.notifica.push.gcm.BaseApplication;

/**
 * Customisation of the default application class
 * 
 * Since we are using our own intent receiver, we have to tell Notificare engine to 
 * send its intents there
 * 
 * @author Joris Verbogt <joris@notifica.re>
 */
public class MyApplication extends BaseApplication {

	@Override
	public void onCreate() {
		// Launch Notificare system
	    Notificare.shared().launch(this);
	    // Set our own intent receiver
	    Notificare.shared().setIntentReceiver(MyIntentReceiver.class);
	    // Enable this device for push notifications
	    Notificare.shared().enableNotifications();
	    Notificare.shared().enableLocationUpdates();
	}

}
