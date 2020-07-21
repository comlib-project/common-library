package com.common.reglib.handler

import android.app.Activity
import com.google.zxing.integration.android.IntentIntegrator

/**
 * Base class for handle scanning digital code such as QR code and Barcode.
 */
class ScannerHandler(activity: Activity) {
    private var context = activity

    /**
     * This method is used to perform scanning barcode.
     * @param requestCode The RequestCode that uses as differencing between request performer.
     * @param intent The Intent Class that used to redirect action to scanner.
     */
    fun <T> scanBarcode(requestCode: Int, intent: Class<T>) {
        val integrator = IntentIntegrator(context)
        integrator.captureActivity = intent
        integrator.setOrientationLocked(false)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES)
        integrator.setPrompt(getPromptText())
        integrator.setRequestCode(requestCode)
        integrator.initiateScan()
    }

    /**
     * This method is used to perform scanning QR code.
     * @param requestCode The RequestCode that uses as differencing between request performer.
     * @param intent The Intent Class that used to redirect action to scanner.
     */
    fun <T> scanQRCode(requestCode: Int, intent: Class<T>) {
        val integrator = IntentIntegrator(context)
        integrator.captureActivity = intent
        integrator.setOrientationLocked(false)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt(getPromptText())
        integrator.setRequestCode(requestCode)
        integrator.initiateScan()
    }

    /**
     * Get Prompting text.
     */
    private fun getPromptText(): String {
        return "Scanning..."
    }
}