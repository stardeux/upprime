package com.stardeux.upprime.media.common.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.media.common.ui.model.DateSeparatorUi
import com.stardeux.upprime.media.common.view.DateSeparatorItemView

class DateSeparatorViewHolder(private val dateSeparatorItemView: DateSeparatorItemView) :
    RecyclerView.ViewHolder(dateSeparatorItemView) {

    fun bind(dateSeparatorUi: DateSeparatorUi) {
        dateSeparatorItemView.bind(dateSeparatorUi)
    }

}