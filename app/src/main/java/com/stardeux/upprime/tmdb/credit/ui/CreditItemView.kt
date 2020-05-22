package com.stardeux.upprime.tmdb.credit.ui

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.rippleResource
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            foreground = ContextCompat.getDrawable(context, context.theme.rippleResource())
            isClickable = true
        }
    }

    fun bind(creditUi: CreditUi) {
        Glide.with(this).load(creditUi.posterPath).into(creditImage).clearOnDetach()

        creditName.text = creditUi.name
        creditRole.text = creditUi.role
    }

}