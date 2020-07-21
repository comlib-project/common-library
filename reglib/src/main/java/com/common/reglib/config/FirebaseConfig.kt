package com.common.reglib.config

import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.paging.PagedList
import com.common.reglib.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.AuthUI.IdpConfig.GoogleBuilder

/**
 * Object class for setting up regular firebase.
 */
object FirebaseConfig {

    /**
     * This method is used to setup PagedList.
     */
    fun getPagedListConfiguration(): PagedList.Config {
        return PagedList.Config.Builder()
            .setInitialLoadSizeHint(10)
            .setPageSize(3)
            .build()
    }

    /**
     * This method is used to setup default AuthUI configuration.
     * @param theme The Theme that used as signing page style resource.
     * @param logo The Logo of Drawable resource that will displayed on top op signing form.
     */
    fun getAuthUIConfiguration(@StyleRes theme: Int, @DrawableRes logo: Int): Intent {
        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(getAuthUIProviders())
            .setTheme(theme)
            .setLogo(logo)
            .setAlwaysShowSignInMethodScreen(true)
            .build()
    }

    /**
     * This method is used to setup simple white AuthUI configuration.
     */
    fun getAuthUIConfiguration(): Intent {
        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(getAuthUIProviders())
            .setAlwaysShowSignInMethodScreen(true)
            .build()
    }

    /**
     * This method is used to setup simple AuthUI configuration.
     */
    fun getAuthUIConfigurations(): Intent {
        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(getAuthUIProviders())
            .setTheme(R.style.LoginTheme)
            .setLogo(R.drawable.img_logo)
            .setAlwaysShowSignInMethodScreen(true)
            .build()
    }

    /**
     * This method is used to setup AuthUI provider.
     */
    private fun getAuthUIProviders(): List<AuthUI.IdpConfig> {
        return listOf(GoogleBuilder().build())
    }
}