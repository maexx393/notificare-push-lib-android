Changelog
=========

## 1.5.1

Reregister if last registration was more than a month ago

## 1.5.0

Send oldDeviceID if changed
Added system push / refresh application info
Removed deprecated ActionBarActivity for AppCompatActivity
Removed deprecated Apache HTTP client references
Updated Gradle build tools 1.2.2, Google Play Services 7.3.0, V7AppCompat 22.1.1

## 1.4.3

Set previously registered deviceId at start
Replace some RuntimeExceptions with warnings
Check for applicationInfo before passing onReady intent
Log spurious ready intents in BaseIntentReceiver

## 1.4.2

Fixed concurrency issue in Connectivity listener that could lead to crashes

## 1.4.1

Added lights settings to be read from incoming notifications
Added default settings specific for Android (currently only lights)
Added preference setting for enabled/disabled notifications, apps should check with isNotificationsEnabled() in onReady callback
Fixed compatibility issue with latest V7AppCompat
Updated to Gradle build tools 1.1.3, Android build tools 22.0.1, Android SDK 22, GooglePlayServices 7.0.0, V7AppCompat 22.0.0, AltBeacon 2.1.4 

## 1.4.0

- Let OS handle all URLs in WebView
- Update AndroidBeaconLibrary to 2.1 
- Added multiple beacon sessions, for overlapping regions
- Changed update intervals on geofences 
- Added customizable small icon, use setSmallIcon() to change it from default application icon
- Retry API requests
- Clear entered beacon regions
- Save registered device to shared preferences, so API calls are only done when needed, even after restart
- Save already entered beacons to shared preferences, so even after restart enters are not triggered
- Save already entered geofences to shared preferences, so even after restart enters are not triggered

## 1.3.2	(2015-01-22)

- only make one fetchApplicationInfo request at once

## 1.3.1	(2015-01-22)

- launch now keeps retrying to load config soon connectivity becomes available
- notification opens are now logged from NotificationActivity or after action is taken from the Notification Manager, if notifications are opened through custom activities, those activities will have to call getEventLogger().logOpenNotification themselves 
- removed UserSegment events, will be available through Notificare Live API

## 1.3.0   (2015-01-08)

- prevent compatibility issue with Android Wear
- add region and beacon session
- minor bug fixes

## 1.2.3	(2014-12-16)

- check for non-null BeaconClient before monitoring beacons
- make listener interfaces public in Billing

## 1.2.2	(2014-12-15)

- check for enabled GCM and Location service at launch, now needs to wait for onReady for all services
- added onReady listener interface in addition to onReady intent
- modular dependencies for Google Play Services

## 1.2.1	(2014-12-10)

- removed debug logging messages
- upgraded to new Google Play Services API

## 1.2.0	(2014-12-08)

- added beacon support
- added in-app billing
- added user preferences
- added inbox
 
