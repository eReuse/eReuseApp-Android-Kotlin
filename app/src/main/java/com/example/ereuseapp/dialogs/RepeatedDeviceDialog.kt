package com.example.ereuseapp.dialogs

import android.app.AlertDialog
import android.content.Context
import com.example.ereuseapp.R

class RepeatedDeviceDialog {
    companion object {
        fun create(context: Context, title: String): AlertDialog {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setIcon(R.drawable.ic_warning)
            builder.setPositiveButton(R.string.accept_dialog) { _, _ -> }
            builder.setCancelable(true)
            return builder.create()
        }
    }
}