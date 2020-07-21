package com.common.reglib.utils

import android.annotation.SuppressLint
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.common.reglib.models.User
import java.text.SimpleDateFormat
import java.util.*

object Common {
    const val ACTION_CODE_UPDATE_LOCATION = "com.common.location.UPDATE_LOCATION"

    const val SCANNER_REQUEST_CODE = 5001
    const val SCANNER_VALIDATION_REQUEST_CODE = 5011
    const val LOCATION_PERMISSION_REQUEST_CODE = 6001
    const val CAMERA_PERMISSION_REQUEST_CODE = 6002
    const val READ_STORAGE_PERMISSION_REQUEST_CODE = 7001
    const val WRITE_STORAGE_PERMISSION_REQUEST_CODE = 7002
    const val AUTH_REQUEST_CODE = 8001

    const val INVOICE = "NoFaktur"
    const val SIGN_IN_UID = "SignInUID"
    const val PUBLIC_LOCATION = "Location"

    const val ON_RECEIPT_ID = "IdForReceipt"
    const val ON_RECEIPT_NO_INVOICE = "NoFakturForReceipt"
    const val ON_RECEIPT_STATUS = "StatusForReceipt"
    const val ON_RECEIPT_DATE = "DateForReceipt"
    const val ON_RECEIPT_KOLI = "KoliForReceipt"
    const val ON_RECEIPT_SUBMITTED = "onReceiptSubmitted"

    const val USER_UID_SAVE_KEY = "UserIdSaveKey"

    var signedUser: User? = null

    @SuppressLint("ConstantLocale")
    private val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    val currentDate: String
        get() {
            return dateFormat.format(Date())
        }

    fun parseDate(date: Date?): String {
        return if (date != null)
            dateFormat.format(date)
        else
            dateFormat.format(Date())
    }

    fun reload(nav: BottomNavigationView, navigationDelivery: Int) {
        nav.selectedItemId = navigationDelivery
    }
}