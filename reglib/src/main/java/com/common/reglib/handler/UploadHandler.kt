package com.common.reglib.handler

import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import com.common.reglib.R
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream

class UploadHandler(private var context: Context) {
    private val db = FirebaseStorage.getInstance()
    private lateinit var reference: StorageReference

    private val outputStream = ByteArrayOutputStream()

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

    fun uploadImageUri(dir: String, filename: String, image: Bitmap, result: ((url: String) -> Unit)) {
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

    private fun compressImage(image: Bitmap) {
        image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    }

}