package com.stardeux.upprime.country.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stardeux.upprime.country.ui.model.CountryItemUi

class CountryDiffUtil : DiffUtil.ItemCallback<CountryItemUi>() {

    override fun areItemsTheSame(oldItem: CountryItemUi, newItem: CountryItemUi): Boolean {
        return oldItem.locale == oldItem.locale
    }

    override fun areContentsTheSame(oldItem: CountryItemUi, newItem: CountryItemUi): Boolean {
        return oldItem == newItem
    }
}