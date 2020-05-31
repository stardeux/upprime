package com.stardeux.upprime.rate.ui

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.stardeux.upprime.R

fun rateAppDialog(context: Context) {

    val alertDialog =
        AlertDialog.Builder(context).setPositiveButton(R.string.rate_app_answer_yes) { _, _ ->  }

}