package com.common.reglib.handler

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import com.common.reglib.R
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream

/**
 * Base class for handle uploading file.
 */
class UploadHandler(private var context: Context) {
    private val outputStream = ByteArrayOutputStream()

    private val db = FirebaseStorage.getInstance()
    private lateinit var reference: StorageReference

    /**
     * This method is used to upload bitmap file.
     * @param dir The Directory name of upload file location.
     * @param filename The Name of upload file.
     * @param image The Bitmap file that uses to upload.
     * @param result The Callback of success full upload.
     */
    fun uploadImageBitmap(dir: String, filename: String, image: Bitmap, result: ((url: String) -> Unit)) {
        compressImage(image)
        reference = db.reference.child(dir).child(filename)
        reference.putBytes(outputStream.toByteArray()).apply {
            addOnSuccessListener {
                reference.downloadUrl.apply {
                    addOnSuccessListener { uri -> result(uri.toString()) }
                    addOnFailureListener { Toast.makeText(context, R.string.message_upload_failure, Toast.LENGTH_LONG).show() }
                }
            }
            addOnFailureListener { Toast.makeText(context, R.string.message_db_failure, Toast.LENGTH_LONG).show() }
        }
    }

    /**
     * This method is used to upload from uri file.
     * @param dir The Directory name of upload file location.
     * @param filename The Name of upload file.
     * @param image The Bitmap file that uses to upload.
     * @param result The Callback of success full upload.
     */
    fun uploadImageUri(dir: String, filename: String, image: Uri, result: ((url: String) -> Unit)) {
        reference = db.reference.child(dir).child(filename)
        reference.putBytes(outputStream.toByteArray()).apply {
            addOnSuccessListener {
                reference.downloadUrl.apply {
                    addOnSuccessListener { uri -> result(uri.toString()) }
                    addOnFailureListener { Toast.makeText(context, R.string.message_upload_failure, Toast.LENGTH_LONG).show() }
                }
            }
            addOnFailureListener { Toast.makeText(context, R.string.message_db_failure, Toast.LENGTH_LONG).show() }
        }
    }

    /**
     * Perform compress bitmap file.
     */
    private fun compressImage(image: Bitmap) {
        image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    }

}