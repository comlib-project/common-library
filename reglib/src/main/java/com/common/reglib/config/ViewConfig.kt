package com.common.reglib.config

import android.content.Context
import android.widget.FrameLayout
import android.widget.LinearLayout

object ViewConfig {

    fun getViewLayout(): FrameLayout.LayoutParams {
        return FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT,
            android.R.attr.top
        )
    }

    fun getViewDialog(context: Context): LinearLayout {
        return LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(10, 10, 10, 10)
        }
    }
}