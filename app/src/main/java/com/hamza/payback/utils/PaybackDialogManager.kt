package com.hamza.payback.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.WindowManager
import com.hamza.payback.R

class PaybackDialogManager() {

    companion object {
        var dialog: AlertDialog? = null

        @SuppressLint("ResourceAsColor")
        fun createProgressDialog(context: Activity): AlertDialog {
            val view =
                (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    R.layout.progress_bar,
                    null
                )

            val builder = AlertDialog.Builder(context).setView(view).setCancelable(false)
            return builder.create()
        }

        fun show(activity: Activity) {
            if (dialog != null && dialog!!.isShowing) dialog?.dismiss()

            dialog = createProgressDialog(activity)
            dialog?.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            if (!dialog!!.isShowing)
                dialog?.show()
        }

        fun dismiss() {
            dialog?.setCancelable(true)
            dialog?.dismiss()
        }
    }
}