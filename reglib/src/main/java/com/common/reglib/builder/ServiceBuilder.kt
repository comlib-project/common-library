package com.common.reglib.builder

import android.Manifest
import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.common.reglib.intents.permission.Permissions
import com.common.reglib.utils.Common

/**
 * Object class for creating services.
 */
object ServiceBuilder {
    private lateinit var locationRequest: LocationRequest
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    /**
     * This method is used to get the last location of device.
     * @param activity The Activity in which the activity is running.
     * @param services The class that handle broadcast receiver for update location.
     */
    fun <T> updateLocation(activity: Activity, services: Class<T>) {
        Permissions(activity).returnOnRestrictedPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        setupLocationRequest()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity).apply {
            requestLocationUpdates(locationRequest, getLocationPendingIntent(activity, services))
        }
    }

    /**
     * This method is used to get the location pending intent.
     * @param activity The Activity in which the activity is running.
     * @param services The class that handle broadcast receiver for update location.
     */
    private fun <T> getLocationPendingIntent(activity: Activity, services: Class<T>): PendingIntent? {
        val intent = Intent(activity, services)
        intent.action = Common.ACTION_CODE_UPDATE_LOCATION
        return PendingIntent.getBroadcast(activity, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    /**
     * This method is used setup location request.
     */
    private fun setupLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.smallestDisplacement = 10f
        locationRequest.fastestInterval = 3000
        locationRequest.interval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
}