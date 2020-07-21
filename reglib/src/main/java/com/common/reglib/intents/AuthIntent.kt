package com.common.reglib.intents

import android.app.Activity
import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import com.common.reglib.utils.Common
import com.firebase.ui.auth.AuthUI
import com.common.reglib.config.FirebaseConfig

class AuthIntent(val context: Activity){

    fun start(){
        startActivityForResult(context, FirebaseConfig.getAuthUIConfiguration(), Common.AUTH_REQUEST_CODE, null)
    }

    fun start(@DrawableRes logo: Int){
        startActivityForResult(context, FirebaseConfig.getAuthUIConfiguration(logo), Common.AUTH_REQUEST_CODE, null)
    }

    fun <T> out(intent: Class<T>) {
        AuthUI.getInstance()
            .signOut(context)
            .addOnCompleteListener {
                startActivity(context, Intent(context, intent), null)
                context.finish()
            }
    }
}