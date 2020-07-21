package com.common.reglib.intents

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.core.app.ActivityCompat.startActivityForResult
import com.common.reglib.intents.permission.Permissions
import com.common.reglib.utils.Common

/**
 * Base class for handle Capturing image with camera intent.
 */
class CameraIntent() {
    lateinit var activity: Activity
    lateinit var permission: Permissions

    /**
     * This constructor of creating camera intent object.
     * @param activity The Activity in which the activity is running.
     */
    constructor(activity: Activity) : this() {
        this.activity = activity
        this.permission = Permissions(activity)
    }

    /**
     * This method is used for requesting camera permission.
     */
    fun request() {
        permission.request(Manifest.permission.CAMERA) {
            startActivityForResult(activity, Intent(MediaStore.ACTION_IMAGE_CAPTURE), Common.CAMERA_PERMISSION_REQUEST_CODE, null)
        }
    }

    /**
     * This method is used for starting camera media intent.
     * @param requestCode The RequestCode the differencing between request performer.
     * @param grantResults The Return of answering permission request.
     */
    fun start(requestCode: Int, grantResults: IntArray) {
        permission.onGrantedPermission(requestCode, grantResults) {
            activity.startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), Common.CAMERA_PERMISSION_REQUEST_CODE)
        }
    }

    /**
     * This method is used for getting bitmap from capturing image with camera.
     * @param requestCode The RequestCode the differencing between request performer.
     * @param resultCode The Return for checking if the camera is success on capturing image.
     * @param data The Data source that recorded after capturing image with camera.
     * @param result The Callback for getting bitmap image.
     * @param e The Callback for empty image captured.
     */
    fun getBitmapOnResult(requestCode: Int, resultCode: Int, data: Intent?, result: ((image: Bitmap) -> Unit), e: (() -> Unit)) {
        if (requestCode == Common.CAMERA_PERMISSION_REQUEST_CODE && resultCode == Activity.RESULT_OK)
            result(data!!.extras!!["data"] as Bitmap)
        else
            e()
    }
}