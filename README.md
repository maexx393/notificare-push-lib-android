# Notificare Push Library for Android Apps

## How to include

First, download the NotificarePushLib-x.y.z.jar to the /libs folder of your project.

Then, add the following lines to your AndroidManifest.xml

        <!--
          BroadcastReceiver that will receive intents from GCM
          services and handle them to the custom IntentService.

          The com.google.android.c2dm.permission.SEND permission is necessary
          so only GCM services can send data messages for the app.
        -->
        <receiver
            android:name="re.notifica.push.gcm.PushReceiver"
            android:permission="com.google.android.c2dm.permission.SEND" >
            <intent-filter>
                <!-- Receives the actual messages. -->
                <action android:name="com.google.android.c2dm.intent.RECEIVE" />
                <!-- Receives the registration id. -->
                <action android:name="com.google.android.c2dm.intent.REGISTRATION" />
                <category android:name="com.mycompany.myproject" />
            </intent-filter>
        </receiver>
        <service android:name="re.notifica.push.gcm.PushService" 
            android:label="Notificare Push Service" />
        
        <receiver android:name="com.mycompany.myproject.receivers.IntentReceiver" />

