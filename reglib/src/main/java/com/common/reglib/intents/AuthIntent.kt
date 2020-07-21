package com.common.reglib.intents

import android.app.Activity
import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import com.common.reglib.utils.Common
import com.firebase.ui.auth.AuthUI
import com.common.reglib.config.FirebaseConfig

/**
 * Base class for handle Authentication intent.
 */
class AuthIntent(val context: Activity){

    /**
     * This method is used for requesting auth permission.
     */
    fun start(){
        startActivityForResult(context, FirebaseConfig.getAuthUIConfiguration(), Common.AUTH_REQUEST_CODE, null)
    }

    /**
     * This method is used for starting authUI intent.
     * @param theme The Theme that used as signing page style resource.
     * @param logo The Logo of Drawable resource that will displayed on top op signing form.
     */
    fun start(@StyleRes theme: Int, @DrawableRes logo: Int){
        startActivityForResult(context, FirebaseConfig.getAuthUIConfiguration(theme, logo), Common.AUTH_REQUEST_CODE, null)
    }

    /**
     * This method is used for signing out the user from AuthUI.
     * @param intent The Activity Class that used to redirecting when signing out is success.
     */
    fun <T> out(intent: Class<T>) {
        AuthUI.getInstance()
            .signOut(context)
            .addOnCompleteListener {
                startActivity(context, Intent(context, intent), null)
                context.finish()
            }
    }
}