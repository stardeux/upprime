package com.stardeux.upprime.country.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.stardeux.upprime.R
import com.stardeux.upprime.country.ui.model.CountryUi
import com.stardeux.upprime.databinding.ItemCountryBinding

class CountryItem : CardView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private val binding = ItemCountryBinding.inflate(LayoutInflater.from(context), this)

    init {
        initLayout()
    }

    private fun initLayout() {
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        setLayoutParams(layoutParams)
    }

    fun bind(countryUi: CountryUi) {
        binding.countryName.text = context.getString(countryUi.nameResId)
        Glide.with(this).load(countryUi.flagUrl).into(binding.countryFlag).clearOnDetach()

        setOnClickListener {
            countryUi.onCountryClicked(countryUi)
        }
    }
}