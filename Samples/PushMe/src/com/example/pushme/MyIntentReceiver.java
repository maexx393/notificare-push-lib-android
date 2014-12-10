package com.example.pushme;

import java.util.List;

import re.notifica.Notificare;
import re.notifica.NotificareCallback;
import re.notifica.NotificareError;
import re.notifica.model.NotificareNotification;
import re.notifica.model.NotificareUser;
import re.notifica.model.NotificareUserPreference;
import re.notifica.push.gcm.DefaultIntentReceiver;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

public class MyIntentReceiver extends DefaultIntentReceiver {

	private static final String TAG = MyIntentReceiver.class.getSimpleName();

	@Override
	public void onReady() {
	    // Fetch user preference definitions
		Notificare.shared().fetchUserPreferences(new NotificareCallback<List<NotificareUserPreference>>() {
			
			@Override
			public void onSuccess(List<NotificareUserPreference> result) {
				// Enable this device for push notifications
			    Notificare.shared().enableNotifications();
			    Notificare.shared().enableLocationUpdates();
			    Notificare.shared().enableBeacons();
			}
			
			@Override
			public void onError(NotificareError error) {
				Log.e(TAG, "Error fetching user Segments: " + error.getMessage(), error);
			    Notificare.shared().enableNotifications();
			    Notificare.shared().enableLocationUpdates();
			    Notificare.shared().enableBeacons();
			}
		});
		
	}

	@Override
	public void onRegistrationFinished(String deviceId) {
		Log.d(TAG, "Device was registered with GCM as device " + deviceId);
		// Register as a device for a test userID
        Notificare.shared().registerDevice(deviceId, "joris@notifica.re", new NotificareCallback<String>() {

        	@Override
			public void onSuccess(String result) {
				Log.d(TAG, "Successfully registered");

				Notificare.shared().userLogin("joris@notifica.re", "mac4ever", new NotificareCallback<Boolean>() {
					
					@Override
					public void onSuccess(Boolean result) {
						Notificare.shared().fetchUserDetails(new NotificareCallback<NotificareUser>() {
							
							@Override
							public void onSuccess(NotificareUser result) {
								Log.d(TAG, "fetched user");
							}
							
							@Override
							public void onError(NotificareError error) {
							}
						});
					}
					
					@Override
					public void onError(NotificareError error) {
						Log.e(TAG, "Error logging in", error);
					}
				});
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
