package com.stardeux.upprime.amazon.latest.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.setLayout
import com.stardeux.upprime.amazon.common.view.DateSeparatorUi

class DateSeparatorItem : ConstraintLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private val dateSeparatorText: TextView by lazy { findViewById<TextView>(R.id.dateSeparatorText) }

    init {
        setLayout(R.layout.date_separator_item)
        initLayout()
    }

    private fun initLayout() {
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        setLayoutParams(layoutParams)
    }

    fun bind(dateSeparatorUi: DateSeparatorUi) {
        dateSeparatorText.text = dateSeparatorUi.date
    }
}