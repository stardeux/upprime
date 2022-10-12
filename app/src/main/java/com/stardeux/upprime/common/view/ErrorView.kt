package com.stardeux.upprime.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.stardeux.upprime.databinding.ViewErrorBinding

class ErrorView : ConstraintLayout {

    private val binding = ViewErrorBinding.inflate(LayoutInflater.from(context), this, true)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    constructor(
        context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    fun setOnRetryClicked(onRetryClicked: () -> Unit) {
        binding.errorCta.setOnClickListener { onRetryClicked() }
    }
}