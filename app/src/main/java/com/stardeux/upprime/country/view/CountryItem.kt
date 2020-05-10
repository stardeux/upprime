package com.stardeux.upprime.country.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.stardeux.upprime.R
import com.stardeux.upprime.core.extension.setLayout
import com.stardeux.upprime.country.ui.model.CountryItemUi

class CountryItem : ConstraintLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private val flagImageView by lazy { findViewById<ImageView>(R.id.countryFlag) }
    private val countryNameTextView by lazy { findViewById<TextView>(R.id.countryName) }

    init {
        setLayout(R.layout.item_country)
    }

    fun bind(countryItemUi: CountryItemUi) {
        countryNameTextView.text = countryItemUi.name
        with(Glide.with(context)) {
            clear(countryNameTextView)
            load(countryItemUi.flagUrl).into(flagImageView)
        }

        setOnClickListener {
            countryItemUi.onCountryClicked(countryItemUi)
        }
    }
}