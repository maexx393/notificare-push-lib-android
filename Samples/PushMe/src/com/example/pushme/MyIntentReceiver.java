package com.example.pushme;

import re.notifica.Notificare;
import re.notifica.NotificareCallback;
import re.notifica.NotificareError;
import re.notifica.model.NotificareNotification;
import re.notifica.push.gcm.DefaultIntentReceiver;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

public class MyIntentReceiver extends DefaultIntentReceiver {

	private static final String TAG = MyIntentReceiver.class.getSimpleName();

	@Override
	public void onReady() {
	    // Enable this device for push notifications
	    Notificare.shared().enableNotifications();
	    Notificare.shared().enableLocationUpdates();
	}

	@Override
	public void onRegistrationFinished(String deviceId) {
		Log.d(TAG, "Device was registered with GCM as device " + deviceId);
		// Register as a device for a test userID
        Notificare.shared().registerDevice(deviceId, "testuser@example.com", new NotificareCallback<String>() {

			@Override
			public void onSuccess(String result) {
				Log.d(TAG, "Successfully registered");
			}

			@Override
			public void onError(NotificareError error) {
				Log.e(TAG, "Error registering device", error);
			}
        	
        });
        
	}

	@Override
	public void onActionReceived(Uri target) {
		Log.d(TAG, "Custom action was received: " + target.toString());
		// By default, pass the target as data URI to your main activity in a launch intent
		super.onActionReceived(target);
	}

	@Override
	protected void buildTaskStack(TaskStackBuilder stackBuilder,
			Intent notificationIntent, NotificareNotification notification) {
		// If notification contains a boolean "test" set to true, just launch the MainActivity
		if (notification.getExtra().containsKey("test")) {
			Intent launchIntent = new Intent(Notificare.shared().getApplicationContext(), MainActivity.class);
			launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			stackBuilder.addNextIntentWithParentStack(launchIntent);
		} else {
			stackBuilder.addNextIntentWithParentStack(notificationIntent);
		}
	}
	
	
	
}
