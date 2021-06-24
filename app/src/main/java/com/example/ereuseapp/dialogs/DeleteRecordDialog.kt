package com.example.ereuseapp.dialogs

import android.app.AlertDialog
import android.content.Context
import com.example.ereuseapp.R

class DeleteRecordDialog {
    companion object {
        fun create(context: Context, callback: () -> Unit, title: String, msg: String): AlertDialog {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setIcon(R.drawable.ic_warning)
            builder.setMessage(msg)
            builder.setPositiveButton(R.string.accept_dialog) { _, _ ->
                callback()
            }
            builder.setNegativeButton(R.string.cancel_dialog) { _, _ -> }
            builder.setCancelable(true)
            return builder.create()
        }
    }
}