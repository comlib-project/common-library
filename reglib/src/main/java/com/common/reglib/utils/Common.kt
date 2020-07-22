package com.common.reglib.utils

import android.annotation.SuppressLint
import android.content.Context
import com.common.reglib.exception.CommonException
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.paperdb.Paper
import java.text.SimpleDateFormat
import java.util.*

/**
 * The Object class that store common logic and constant value.
 */
object Common {
    const val USER_UID_SAVE_KEY = "com.common.location.UID"
    const val ACTION_CODE_UPDATE_LOCATION = "com.common.location.UPDATE_LOCATION"

    const val LOCATION_PERMISSION_REQUEST_CODE = 6001
    const val CAMERA_PERMISSION_REQUEST_CODE = 6002
    const val READ_STORAGE_PERMISSION_REQUEST_CODE = 7001
    const val WRITE_STORAGE_PERMISSION_REQUEST_CODE = 7002
    const val AUTH_REQUEST_CODE = 8001

    /**
     * This method is used to get template for formatting date on string value.
     */
    @SuppressLint("ConstantLocale")
    private val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    /**
     * This method is used to get most up to date time with formatted string.
     */
    val currentDate: String
        get() {
            return dateFormat.format(Date())
        }

    /**
     * This method is used to parsing Date type data to formatted string.
     * @param date The Date value that used to convert as string.
     */
    fun parseDate(date: Date?): String {
        return if (date != null)
            dateFormat.format(date)
        else
            dateFormat.format(Date())
    }

    /**
     * This method is used to re load fragment from bottom navigation.
     * @param nav The View in which the BottomNavigationView is performed.
     * @param navigationDelivery The Fragment n which the Fragment is reloaded.
     */
    fun reload(nav: BottomNavigationView, navigationDelivery: Int) {
        nav.selectedItemId = navigationDelivery
    }

    /**
     * This method is used to load stored signed user id.
     * @param context The Context in which the receiver is running.
     */
    fun readSign(context: Context): String {
        Paper.init(context)
        val sign = Paper.book().read<String>(USER_UID_SAVE_KEY)
        if (sign == null)
            throw CommonException("Common-Lib doesn't found any signed unique id")
        else
            return sign
    }

    /**
     * This method is used to store signed user id to local storage.
     * @param context The Context in which the receiver is running.
     * @param sign The Sign unique id to be stored.
     */
    fun registerSign(context: Context, sign: String) {
        Paper.init(context)
        Paper.book().write(USER_UID_SAVE_KEY, sign)
    }
}