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

object ServiceBuilder {
    private lateinit var locationRequest: LocationRequest
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    fun <T> updateLocation(context: Activity, services: Class<T>) {
        Permissions(context).returnOnRestrictedPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        setupLocationRequest()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context).apply {
            requestLocationUpdates(locationRequest, getLocationPendingIntent(context, services))
        }
    }

    private fun <T> getLocationPendingIntent(context: Activity, services: Class<T>): PendingIntent? {
        val intent = Intent(context, services)
        intent.action = Common.ACTION_CODE_UPDATE_LOCATION
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun setupLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.smallestDisplacement = 10f
        locationRequest.fastestInterval = 3000
        locationRequest.interval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
}