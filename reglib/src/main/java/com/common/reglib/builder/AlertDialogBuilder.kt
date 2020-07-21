package com.common.reglib.builder

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getColor
import com.common.reglib.R

object AlertDialogBuilder {

    fun alertDialogMessage(context: Context, message: String): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)

        return alertDialog(builder)
    }

    fun alertDialogView(context: Context, view: View): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setView(view)

        return alertDialog(builder)
    }

    fun alertDialogView(context: Context, title: String, view: View): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setView(view)

        return alertDialog(builder)
    }

    private fun alertDialog(builder: AlertDialog.Builder): AlertDialog {
        return builder.create().apply {
            setOnShowListener {
                this.getButton(AlertDialog.BUTTON_POSITIVE)
                    .setTextColor(getColor(context, R.color.colorPrimary))
                this.getButton(AlertDialog.BUTTON_NEGATIVE)
                    .setTextColor(getColor(context, R.color.colorPrimary))
            }
        }
    }
}