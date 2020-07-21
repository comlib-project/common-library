package com.common.reglib.intents.permission

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.common.reglib.R
import com.common.reglib.utils.Common

/**
 * Base class for handle all kind of permission.
 */
class Permissions(var context: Activity) {

    /**
     * This method is used to return or stopping the process for un authorize permission.
     * @param permission The Permission checking the permission authorization.
     */
    fun returnOnRestrictedPermission(permission: String) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
            return
    }

    /**
     * This method is used displaying permission request.
     * @param permission The Permission checking the permission authorization.
     * @param onSuccessListener The Callback for granted permission.
     */
    fun request(permission: String, onSuccessListener: (()-> Unit)) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
            when(permission) {
                Manifest.permission.CAMERA -> ActivityCompat.requestPermissions(context, arrayOf(permission), Common.CAMERA_PERMISSION_REQUEST_CODE)
                Manifest.permission.READ_EXTERNAL_STORAGE -> ActivityCompat.requestPermissions(context, arrayOf(permission), Common.READ_STORAGE_PERMISSION_REQUEST_CODE)
                Manifest.permission.WRITE_EXTERNAL_STORAGE -> ActivityCompat.requestPermissions(context, arrayOf(permission), Common.WRITE_STORAGE_PERMISSION_REQUEST_CODE)
                Manifest.permission.ACCESS_FINE_LOCATION -> ActivityCompat.requestPermissions(context, arrayOf(permission), Common.LOCATION_PERMISSION_REQUEST_CODE)
                else -> Toast.makeText(context, R.string.message_permission_not_found, Toast.LENGTH_LONG).show()
            }
        else
            onSuccessListener()
    }

    /**
     * This method is used to perform action when permission is granted.
     * @param requestCode The RequestCode the differencing between request performer.
     * @param grantResults The Return of answering permission request.
     * @param result The Callback of granted permission request.
     */
    fun onGrantedPermission(requestCode: Int, grantResults: IntArray, result: ((requestCode: Int) -> Unit)) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            result(requestCode)
        else
            Toast.makeText(context, R.string.message_permission_require, Toast.LENGTH_LONG).show()
    }

























//    fun handleRequestPermissionsResult(requestCode: Int, grantResults: IntArray) {
//        if (requestCode == Common.CAMERA_PERMISSION_REQUEST_CODE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                context.startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), Common.CAMERA_PERMISSION_REQUEST_CODE)
//            else
//                Toast.makeText(context, R.string.message_permission_require, Toast.LENGTH_LONG).show()
//        }
//
//        if (requestCode == Common.LOCATION_PERMISSION_REQUEST_CODE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                result()
//            else
//                Toast.makeText(context, R.string.message_permission_require, Toast.LENGTH_LONG).show()
//        }
//    }
}