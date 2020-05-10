package com.stardeux.upprime.country.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.country.ui.model.CountryUi
import com.stardeux.upprime.country.view.CountryItem

class CountryViewHolder(countryItem: CountryItem) : RecyclerView.ViewHolder(countryItem) {

    fun bind(countryUi: CountryUi) {
        (itemView as CountryItem).bind(countryUi)
    }

}