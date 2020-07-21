package com.common.reglib.config

import com.google.android.gms.location.LocationRequest

/**
 * Object class for setting up regular services.
 */
object ServicesConfig {
    private lateinit var locationRequest: LocationRequest

    /**
     * This method is used to setup location request service.
     */
    fun getLocationRequestConfiguration() {
        locationRequest = LocationRequest()
        locationRequest.smallestDisplacement = 10f
        locationRequest.fastestInterval = 3000
        locationRequest.interval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
}