package com.example.pushme;

import re.notifica.Notificare;
import re.notifica.NotificareCallback;
import re.notifica.NotificareError;
import re.notifica.model.NotificareNotification;
import re.notifica.push.gcm.DefaultIntentReceiver;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MyIntentReceiver extends DefaultIntentReceiver {

	private static final String TAG = MyIntentReceiver.class.getSimpleName();

	@Override
	public void onNotificationReceived(String alert, String notificationId,
			Bundle extras) {
		// Execute default behavior, i.e., put notification in drawer
		super.onNotificationReceived(alert, notificationId, extras);
	}

	@Override
	public void onNotificationOpened(String alert, String notificationId,
			Bundle extras) {
		// Notification is in extras
		NotificareNotification notification = extras.getParcelable(Notificare.INTENT_EXTRA_NOTIFICATION);
		Log.d(TAG, "Notification was opened with type " + notification.getType());
		// By default, open the NotificationActivity and let it handle the Notification
		super.onNotificationOpened(alert, notificationId, extras);
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
	
}
