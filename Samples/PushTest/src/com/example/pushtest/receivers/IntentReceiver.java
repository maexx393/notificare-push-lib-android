package com.example.pushtest.receivers;

import re.notifica.Notificare;
import re.notifica.push.gcm.BaseIntentReceiver;
import android.content.Intent;
import android.os.Bundle;

import com.example.pushtest.MainActivity;

public class IntentReceiver extends BaseIntentReceiver {
	 
    @Override
    public void onNotificationReceived(String alert, String notificationId, Bundle extras) {
            onNotificationOpened(alert, notificationId, extras);
    }

    @Override
    public void onNotificationOpened(String alert, String notificationId, Bundle extras) {
        Intent launch = new Intent(Intent.ACTION_MAIN);
        launch.setClass(Notificare.shared().getApplicationContext(), MainActivity.class);
        launch.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Notificare.shared().getApplicationContext().startActivity(launch);
    }

    @Override
    public void onRegistrationFinished(String deviceId) {
            Notificare.shared().registerDevice(deviceId);
    }   
}