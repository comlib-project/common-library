package com.common.reglib.builder

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getColor
import com.common.reglib.R

/**
 * Object class for creating alert dialog.
 */
object AlertDialogBuilder {

    /**
     * This method is used to perform simple Alert Dialog.
     * @param context The Context in which the context is running.
     * @param message The Textual message that used as dialog content.
     */
    fun alertDialogMessage(context: Context, message: String): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)

        return alertDialog(builder)
    }

    /**
     * This method is used to perform simple Alert Dialog with custom view.
     * @param context The Context in which the context is running.
     * @param view The View that used as dialog display.
     */
    fun alertDialogView(context: Context, view: View): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setView(view)

        return alertDialog(builder)
    }

    /**
     * This method is used to perform Alert Dialog with title and custom view.
     * @param context The Context in which the context is running.
     * @param title The Textual message that used as dialog title.
     * @param view The View that used as dialog display.
     */
    fun alertDialogView(context: Context, title: String, view: View): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setView(view)

        return alertDialog(builder)
    }

    /**
     * This method is used to customize Alert Dialog attribute such as button color.
     * @param builder The Builder of Alert Dialog that will be used to customize.
     */
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