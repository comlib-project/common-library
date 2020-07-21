package com.common.reglib.abstracts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.Location
import com.common.reglib.utils.Common
import com.google.android.gms.location.LocationResult
import io.paperdb.Paper

abstract class TrackingService: BroadcastReceiver() {
    private val uid: String = Paper.book().read<String>(Common.USER_UID_SAVE_KEY)

    abstract fun onUpdateLocation(context: Context?, location: Location, unique: String)

    override fun onReceive(context: Context?, intent: Intent?) {
        Paper.init(context)

        if (intent != null) {
            val action = intent.action
            if (action == Common.ACTION_CODE_UPDATE_LOCATION) {
                val result = LocationResult.extractResult(intent)
                if (result != null) {
                    val location = result.lastLocation
                    if (Common.signedUser != null) {
                        Common.signedUser!!.uid?.let { onUpdateLocation(context, location, it) }
                    } else {
                        onUpdateLocation(context, location, uid)
                    }
                }
            }
        }
    }
}