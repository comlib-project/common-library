package com.common.reglib.handler

import android.app.Activity
import com.google.zxing.integration.android.IntentIntegrator

class ScannerHandler(activity: Activity) {
    private var context = activity

    fun <T> scanBarcode(requestCode: Int, intent: Class<T>) {
        val integrator = IntentIntegrator(context)
        integrator.captureActivity = intent
        integrator.setOrientationLocked(false)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES)
        integrator.setPrompt(getPromptText())
        integrator.setTimeout(10000)
        integrator.setRequestCode(requestCode)
        integrator.initiateScan()
    }

    fun <T> scanQRCode(requestCode: Int, intent: Class<T>) {
        val integrator = IntentIntegrator(context)
        integrator.captureActivity = intent
        integrator.setOrientationLocked(false)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt(getPromptText())
        integrator.setTimeout(10000)
        integrator.setRequestCode(requestCode)
        integrator.initiateScan()
    }

    private fun getPromptText(): String {
        return "Scanning..."
    }
}