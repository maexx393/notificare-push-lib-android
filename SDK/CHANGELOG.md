Changelog
=========

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
 
