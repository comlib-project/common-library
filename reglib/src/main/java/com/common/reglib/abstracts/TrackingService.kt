package com.common.reglib.abstracts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.Location
import com.common.reglib.utils.Common
import com.google.android.gms.location.LocationResult
import io.paperdb.Paper

/**
 * Base class for code that receives location.
 */
abstract class TrackingService: BroadcastReceiver() {
    private val uid: String = Paper.book().read<String>(Common.USER_UID_SAVE_KEY)

    /**
     * This method is called when the tracking receiving the location
     * @param context The Context in which the receiver is running.
     * @param location The Location of device.
     * @param unique The Unique id that belong to device owner.
     */
    abstract fun onUpdateLocation(context: Context?, location: Location, unique: String)

    override fun onReceive(context: Context?, intent: Intent?) {
        Paper.init(context)

        if (intent != null) {
            val action = intent.action
            if (action == Common.ACTION_CODE_UPDATE_LOCATION) {
                val result = LocationResult.extractResult(intent)
                if (result != null) {
                    onUpdateLocation(context, result.lastLocation, uid)
                }
            }
        }
    }
}