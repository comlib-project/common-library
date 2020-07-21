package com.common.reglib.intents.permission

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.common.reglib.R
import com.common.reglib.utils.Common

class Permissions(var context: Activity) {

    fun returnOnRestrictedPermission(permission: String) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
            return
    }

    fun request(permission: String, todo: (()-> Unit)) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
            when(permission) {
                Manifest.permission.CAMERA -> ActivityCompat.requestPermissions(context, arrayOf(permission), Common.CAMERA_PERMISSION_REQUEST_CODE)
                Manifest.permission.READ_EXTERNAL_STORAGE -> ActivityCompat.requestPermissions(context, arrayOf(permission), Common.READ_STORAGE_PERMISSION_REQUEST_CODE)
                Manifest.permission.WRITE_EXTERNAL_STORAGE -> ActivityCompat.requestPermissions(context, arrayOf(permission), Common.WRITE_STORAGE_PERMISSION_REQUEST_CODE)
                Manifest.permission.ACCESS_FINE_LOCATION -> ActivityCompat.requestPermissions(context, arrayOf(permission), Common.LOCATION_PERMISSION_REQUEST_CODE)
                else -> Toast.makeText(context, R.string.message_permission_not_found, Toast.LENGTH_LONG).show()
            }
        else
            todo()
    }

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