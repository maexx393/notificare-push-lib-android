# Notificare Push Library for Android Apps

## Configuring your app to use Notificare Push

First, download the NotificarePushLib-x.y.z.jar to the /libs folder of your project.

Then, add the following lines to your AndroidManifest.xml

    <uses-sdk android:minSdkVersion="8" android:targetSdkVersion="xx"/>
    
    <!-- PERMISSIONS -->
    <uses-permission android:name="android.permission.GET_ACCOUNTS" />
    <uses-permission android:name="android.permission.WAKE_LOCK" />
	<uses-permission android:name="android.permission.INTERNET"/>
	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

Where 'xx' is the actual target version of your app
	
For Android version < 4.1, add these permissions

	<!-- PERMISSIONS REQUIRED for C2DM  -->
    <permission android:name="com.mycompany.myproject.permission.C2D_MESSAGE" android:protectionLevel="signature" />
    <uses-permission android:name="com.mycompany.myproject.permission.C2D_MESSAGE" />
    
Where com.mycompany.myproject is your app's namespace
   
Allow your app to receive notifications
    
    <!-- This app has permission to register and receive message -->
    <uses-permission android:name="com.google.android.c2dm.permission.RECEIVE" />

And in the same file, inside your `<application>` element, add a receiver:

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

Where com.mycompany.myproject is of course the namespace of your app

## Handling registrations and push notifications

Create a class com.mycompany.myproject.receivers.IntentReceiver

	public class IntentReceiver extends BaseIntentReceiver {
		        
	        @Override
	        public void onNotificationReceived(String alert, String notificationId, Bundle extras) {
	            	onNotificationOpened(alert, notificationId, extras);
	        }
	
	        @Override
	        public void onNotificationOpened(String alert, String notificationId, Bundle extras) {
	            Intent launch = new Intent(Intent.ACTION_MAIN);
	            launch.setClass(Notificare.shared().getApplicationContext(), MyMainActivity.class);
	            launch.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	  
	            Notificare.shared().getApplicationContext().startActivity(launch);
	        }
	
			@Override
			public void onRegistrationFinished(String deviceId) {
				try {
			        //Get the preferences
					Context ctx = Notificare.shared().getApplicationContext();
					SharedPreferences prefs = ctx.getSharedPreferences("myproject", Context.MODE_PRIVATE);
	
		            Notificare.shared().registerDevice(deviceId, prefs.getString("userId", null), prefs.getString("userName",  null));
	
				} catch (NameNotFoundException e) {
					// Oops
				}
			}	
	}