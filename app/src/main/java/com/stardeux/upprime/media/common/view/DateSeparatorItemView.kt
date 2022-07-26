package com.stardeux.upprime.media.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.stardeux.upprime.R
import com.stardeux.upprime.databinding.ItemDateSeparatorBinding
import com.stardeux.upprime.media.common.ui.model.DateSeparatorUi

class DateSeparatorItemView : ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private val binding = ItemDateSeparatorBinding.inflate(LayoutInflater.from(context), this)

    init {
        initLayout()
    }

    private fun initLayout() {
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        setLayoutParams(layoutParams)
    }

    fun bind(dateSeparatorUi: DateSeparatorUi) {
        binding.dateSeparatorText.text = dateSeparatorUi.date
    }
}