package com.stardeux.upprime.country.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stardeux.upprime.core.extension.createAsyncDifferConfig
import com.stardeux.upprime.country.ui.model.CountryItemUi
import com.stardeux.upprime.country.view.CountryItem

class CountryAdapter :
    ListAdapter<CountryItemUi, CountryViewHolder>(createAsyncDifferConfig(CountryDiffUtil())) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(CountryItem(parent.context))
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}