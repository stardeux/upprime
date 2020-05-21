package com.stardeux.upprime.tmdb.credit.ui

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.stardeux.upprime.tmdb.credit.ui.model.CreditUi

class CreditItem : ConstraintLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    fun bind (creditUi: CreditUi) {

    }

}