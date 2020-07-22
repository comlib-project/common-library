package com.common.reglib.builder

import android.Manifest
import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentSender
import android.location.LocationManager
import android.provider.Settings
import androidx.core.app.ActivityCompat.startActivityForResult
import com.common.reglib.intents.permission.Permissions
import com.common.reglib.utils.Common
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import java.lang.ClassCastException

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
     * This method is used to check the location service.
     * @param activity The Activity in which the activity is running.
     */
    fun checkLocationService(activity: Activity) {
        setupLocationRequest()
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        val result = LocationServices.getSettingsClient(activity)
            .checkLocationSettings(builder.build())
            .addOnCompleteListener {
                try {
                    it.getResult(ApiException::class.java)
                } catch (e: ApiException) {
                    when(e.statusCode) {
                        LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> enableLocationService(activity, e)
                        LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> return@addOnCompleteListener
                    }

                    e.printStackTrace()
                }
            }
    }

    /**
     * This method is used to check available location service and enable it.
     * @param activity The Activity in which the activity is running.
     * @param error The Error exception.
     */
    private fun enableLocationService(activity: Activity, error: ApiException) {
        try {
            val resolvableApiException = error as ResolvableApiException
            resolvableApiException.startResolutionForResult(activity, 2122)
        } catch (ex: IntentSender.SendIntentException) {
            ex.printStackTrace()
        } catch (ex: ClassCastException) {}
    }

    /**
     * This method is used to check gps provider on settings.
     * @param activity The Activity in which the activity is running.
     */
    fun checkGPSProvider(activity: Activity) {
        val locationManager: LocationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            AlertDialogBuilder.alertDialogMessage(activity, "GPS is required to run this app. Please enable GPS.").apply {
                setButton(DialogInterface.BUTTON_POSITIVE, "Enable") { dialog, _ ->
                    startActivityForResult(activity,
                        Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS),
                        Common.LOCATION_PROVIDER_REQUEST_CODE, null)

                    dialog.dismiss()
                }
                setButton(DialogInterface.BUTTON_NEGATIVE, "Close App") { dialog, _ ->
                    activity.finish()
                    dialog.dismiss()
                }
            }.show()
    }

    /**
     * This method is used to check gps provider on settings.
     * @param activity The Activity in which the activity is running.
     * @param onDisabled The Callback when getting provider is disabled.
     */
    fun checkGPSProvider(activity: Activity, onDisabled: (() -> Unit)) {
        val locationManager: LocationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            onDisabled()
    }

    /**
     * This method is used to check gps provider on settings.
     * @param activity The Activity in which the activity is running.
     * @param onDisabled The Callback when getting provider is disabled.
     * @param onEnabled The Callback when getting provider is enabled.
     */
    fun checkGPSProvider(activity: Activity, onDisabled: (() -> Unit), onEnabled: (() -> Unit)) {
        val locationManager: LocationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            onDisabled()
        else
            onEnabled()
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