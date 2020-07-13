package com.stardeux.upprime.common.view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.setLayout
import kotlinx.android.synthetic.main.view_error.view.*

class ErrorView : ConstraintLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    init {
        setLayout(R.layout.view_error)
    }

    fun setOnRetryClicked(onRetryClicked: () -> Unit) {
        errorCta.setOnClickListener { onRetryClicked() }
    }
}