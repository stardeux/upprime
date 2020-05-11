package com.stardeux.upprime.country.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stardeux.upprime.country.ui.model.CountryUi

class CountryDiffUtil : DiffUtil.ItemCallback<CountryUi>() {

    override fun areItemsTheSame(oldItem: CountryUi, newItem: CountryUi): Boolean {
        return oldItem.availableCountry == oldItem.availableCountry
    }

    override fun areContentsTheSame(oldItem: CountryUi, newItem: CountryUi): Boolean {
        return oldItem == newItem
    }
}