package com.stardeux.upprime.tmdb.credit.ui

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.setLayout
import com.stardeux.upprime.tmdb.credit.ui.model.CreditUi

class CreditItemView : ConstraintLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private val creditImage: ImageView by lazy { findViewById<ImageView>(R.id.creditImage) }
    private val creditName: TextView by lazy { findViewById<TextView>(R.id.creditName) }
    private val creditRole: TextView by lazy { findViewById<TextView>(R.id.creditRole) }

    init {
        setLayout(R.layout.item_credit)
        initLayout()
    }

    private fun initLayout() {
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        setLayoutParams(layoutParams)
    }

    fun bind(creditUi: CreditUi) {
        Glide.with(this).clear(creditImage)
        Glide.with(this).load(creditUi.posterPath).into(creditImage)

        creditName.text = creditUi.name
        creditRole.text = creditUi.role
    }

}