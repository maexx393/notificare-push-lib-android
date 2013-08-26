package com.example.pushtest.receivers;

import re.notifica.Notificare;
import re.notifica.NotificareCallback;
import re.notifica.NotificareError;
import re.notifica.push.gcm.BaseIntentReceiver;
import re.notifica.ui.NotificationActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

/**
 * Intent receiver for Notificare intents. 
 * Use this to dispatch incoming Notification stuff to other parts of your application.
 * @author Joris Verbogt <joris@notifica.re>
 */
public class IntentReceiver extends BaseIntentReceiver {
	 
    protected static final String TAG = IntentReceiver.class.getSimpleName();

	@Override
    public void onNotificationReceived(String alert, String notificationId, Bundle extras) {
    }

    @Override
    public void onNotificationOpened(String alert, String notificationId, Bundle extras) {
		Intent notificationIntent = new Intent()
			.setClass(Notificare.shared().getApplicationContext(), NotificationActivity.class)
			.setAction(Notificare.INTENT_ACTION_NOTIFICATION_OPENED)
			.putExtras(extras)
			.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		TaskStackBuilder backStack = TaskStackBuilder.create(Notificare.shared().getApplicationContext());
		backStack.addNextIntentWithParentStack(notificationIntent);
		backStack.startActivities();
    }

    @Override
    public void onRegistrationFinished(String deviceId) {
            Notificare.shared().registerDevice(deviceId, "testuser@notifica.re", "Test User", new NotificareCallback<String>() {

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