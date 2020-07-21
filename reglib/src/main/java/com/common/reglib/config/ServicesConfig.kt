package com.common.reglib.config

import com.google.android.gms.location.LocationRequest

object ServicesConfig {
    private lateinit var locationRequest: LocationRequest

    fun getLocationRequestConfiguration() {
        locationRequest = LocationRequest()
        locationRequest.smallestDisplacement = 10f
        locationRequest.fastestInterval = 3000
        locationRequest.interval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
}