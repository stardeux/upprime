package com.stardeux.upprime.amazon.common.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.amazon.common.view.DateSeparatorUi
import com.stardeux.upprime.amazon.latest.view.DateSeparatorItem

class DateSeparatorViewHolder(private val dateSeparatorItem: DateSeparatorItem) :
    RecyclerView.ViewHolder(dateSeparatorItem) {

    fun bind(dateSeparatorUi: DateSeparatorUi) {
        dateSeparatorItem.bind(dateSeparatorUi)
    }

}