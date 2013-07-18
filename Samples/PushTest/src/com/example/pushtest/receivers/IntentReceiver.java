package com.example.pushtest.receivers;

import re.notifica.Notificare;
import re.notifica.NotificareCallback;
import re.notifica.NotificareError;
import re.notifica.push.gcm.BaseIntentReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.pushtest.MainActivity;

/**
 * Intent receiver for Notificare intents. 
 * Use this to dispatch incoming Notification stuff to other parts of your application.
 * @author Joris Verbogt <joris@notifica.re>
 */
public class IntentReceiver extends BaseIntentReceiver {
	 
    protected static final String TAG = IntentReceiver.class.getSimpleName();

	@Override
    public void onNotificationReceived(String alert, String notificationId, Bundle extras) {
            onNotificationOpened(alert, notificationId, extras);
    }

    @Override
    public void onNotificationOpened(String alert, String notificationId, Bundle extras) {
        Intent launch = new Intent(Intent.ACTION_MAIN);
        launch.setClass(Notificare.shared().getApplicationContext(), MainActivity.class);
        launch.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Notificare.shared().getApplicationContext().startActivity(launch);
    }

    @Override
    public void onRegistrationFinished(String deviceId) {
            Notificare.shared().registerDevice(deviceId, new NotificareCallback<String>() {

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
}