package com.stardeux.upprime.country.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.country.ui.model.CountryItemUi
import com.stardeux.upprime.country.view.CountryItem

class CountryViewHolder(countryItem: CountryItem) : RecyclerView.ViewHolder(countryItem) {

    fun bind(countryItemUi: CountryItemUi) {
        (itemView as CountryItem).bind(countryItemUi)
    }

}