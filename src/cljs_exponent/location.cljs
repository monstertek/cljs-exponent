(ns cljs-exponent.location
  "This module allows reading geolocation information from the device. Your app can poll for the current location or subscribe to location update events."
  (:require [cljs-exponent.core :refer [exponent]]))

(def Location (aget exponent "Location"))

(defn get-current-position-async
  "Get the current position of the device.

   Arguments
   options (object) --
   A map of options:

   enableHighAccuracy (boolean) -- Whether to enable high-accuracy mode. For low-accuracy the implementation can avoid geolocation providers that consume a significant amount of power (such as GPS).
Returns
Returns an object with the following fields:

   coords (object) -- The coordinates of the position, with the following fields:
   latitude (number) -- The latitude in degrees.
   longitude (number) -- The longitude in degrees.
   altitude (number) -- The altitude in meters above the WGS 84 reference ellipsoid.
   accuracy (number) -- The radius of uncertainty for the location, measured in meters.
   altitudeAccuracy (number) -- The accuracy of the altitude value, in meters (iOS only).
   heading (number) -- Horizontal direction of travel of this device, measured in degrees starting at due north and continuing clockwise around the compass. Thus, north is 0 degrees, east is 90 degrees, south is 180 degrees, and so on.
   speed (number) -- The instantaneous speed of the device in meters per second.
   timestamp (number) -- The time at which this position information was obtained, in milliseconds since epoch."
  [options]
  (.call (aget Location "getCurrentPositionAsync")
         Location (clj->js options)))

(defn watchPositionAsync
  "Subscribe to location updates from the device.

Arguments
options (object) --
A map of options:

enableHighAccuracy (boolean) -- Whether to enable high accuracy mode. For low accuracy the implementation can avoid geolocation providers that consume a significant amount of power (such as GPS).
timeInterval (number) -- Minimum time to wait between each update in milliseconds.
distanceInterval (number) -- Receive updates only when the location has changed by at least this distance in meters.
callback (function) --
This function is called on each location update. It is passed exactly one parameter: an object with the following fields:

coords (object) -- The coordinates of the position, with the following fields:
latitude (number) -- The latitude in degrees.
longitude (number) -- The longitude in degrees.
altitude (number) -- The altitude in meters above the WGS 84 reference ellipsoid.
accuracy (number) -- The radius of uncertainty for the location, measured in meters.
altitudeAccuracy (number) -- The accuracy of the altitude value, in meters (iOS only).
heading (number) -- Horizontal direction of travel of this device, measured in degrees starting at due north and continuing clockwise around the compass. Thus, north is 0 degrees, east is 90 degrees, south is 180 degrees, and so on.
speed (number) -- The instantaneous speed of the device in meters per second.
timestamp (number) -- The time at which this position information was obtained, in milliseconds since epoch.
Returns
Returns a subscription object, which has one field:

remove (function) -- Call this function with no arguments to remove this subscription. The callback will no longer be called for location updates."
  [options callback]
  (.call (aget Location "watchPositionAsync")
         Location
         (clj->js options) callback))
