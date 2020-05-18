package com.stardeux.upprime.core.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.stardeux.upprime.R

fun AppCompatActivity.applyCommonBack(toolbar: Toolbar) {
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
    toolbar.setNavigationOnClickListener { finish() }
}