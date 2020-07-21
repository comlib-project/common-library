package com.common.reglib.config

import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.paging.PagedList
import com.common.reglib.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.AuthUI.IdpConfig.GoogleBuilder

object FirebaseConfig {

    // Fire store paged list config
    fun getPagedListConfiguration(): PagedList.Config {
        return PagedList.Config.Builder()
            .setInitialLoadSizeHint(10)
            .setPageSize(3)
            .build()
    }

    // Auth UI config
    fun getAuthUIConfiguration(@DrawableRes logo: Int): Intent {
        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(getAuthUIProviders())
            .setTheme(R.style.LoginTheme)
            .setLogo(logo)
            .setAlwaysShowSignInMethodScreen(true)
            .build()
    }

    // Auth UI config
    fun getAuthUIConfiguration(): Intent {
        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(getAuthUIProviders())
            .setTheme(R.style.LoginTheme)
            .setAlwaysShowSignInMethodScreen(true)
            .build()
    }

    private fun getAuthUIProviders(): List<AuthUI.IdpConfig> {
        return listOf(GoogleBuilder().build())
    }
}