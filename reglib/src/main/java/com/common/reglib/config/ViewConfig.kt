package com.common.reglib.config

import android.content.Context
import android.widget.FrameLayout
import android.widget.LinearLayout

/**
 * Object class for setting up basic view.
 */
object ViewConfig {

    /**
     * This method is used to get default layout value.
     */
    fun getViewLayout(): FrameLayout.LayoutParams {
        return FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT,
            android.R.attr.top
        )
    }

    /**
     * This method is used to get default layout with custom view.
     * @param context The Context in which the context is running.
     */
    fun getViewDialog(context: Context): LinearLayout {
        return LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(10, 10, 10, 10)
        }
    }
}