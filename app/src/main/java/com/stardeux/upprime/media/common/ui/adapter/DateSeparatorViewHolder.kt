package com.stardeux.upprime.media.common.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.media.common.ui.model.DateSeparatorUi
import com.stardeux.upprime.media.latest.view.DateSeparatorItem

class DateSeparatorViewHolder(private val dateSeparatorItem: DateSeparatorItem) :
    RecyclerView.ViewHolder(dateSeparatorItem) {

    fun bind(dateSeparatorUi: DateSeparatorUi) {
        dateSeparatorItem.bind(dateSeparatorUi)
    }

}