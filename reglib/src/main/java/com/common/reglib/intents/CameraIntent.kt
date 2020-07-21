package com.common.reglib.intents

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.core.app.ActivityCompat.startActivityForResult
import com.common.reglib.intents.permission.Permissions
import com.common.reglib.utils.Common

class CameraIntent() {
    lateinit var context: Activity
    lateinit var permission: Permissions

    constructor(context: Activity) : this() {
        this.context = context
        this.permission = Permissions(context)
    }

    fun request() {
        permission.request(Manifest.permission.CAMERA) {
            startActivityForResult(context, Intent(MediaStore.ACTION_IMAGE_CAPTURE), Common.CAMERA_PERMISSION_REQUEST_CODE, null)
        }
    }

    fun start(requestCode: Int, grantResults: IntArray) {
        permission.onGrantedPermission(requestCode, grantResults) {
            context.startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), Common.CAMERA_PERMISSION_REQUEST_CODE)
        }
    }

    fun getBitmapOnResult(requestCode: Int, resultCode: Int, data: Intent?, result: ((image: Bitmap) -> Unit), e: (() -> Unit)) {
        if (requestCode == Common.CAMERA_PERMISSION_REQUEST_CODE && resultCode == Activity.RESULT_OK)
            result(data!!.extras!!["data"] as Bitmap)
        else
            e()
    }
}