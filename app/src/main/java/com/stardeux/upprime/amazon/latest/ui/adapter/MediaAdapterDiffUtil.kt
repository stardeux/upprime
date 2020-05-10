package com.stardeux.upprime.amazon.latest.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stardeux.upprime.amazon.common.view.DateSeparatorUi
import com.stardeux.upprime.amazon.common.view.MediaUi

class MediaAdapterDiffUtil : DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem is MediaUi && newItem is MediaUi) {
            oldItem.amazonId == newItem.amazonId
        } else if (oldItem is DateSeparatorUi && newItem is DateSeparatorUi) {
            oldItem.date == newItem.date
        } else {
            false
        }
    }

    @Suppress("USELESS_CAST")
    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem is MediaUi && newItem is MediaUi) {
            oldItem as MediaUi == newItem as MediaUi
        } else if (oldItem is DateSeparatorUi && newItem is DateSeparatorUi) {
            oldItem as DateSeparatorUi == newItem as DateSeparatorUi
        } else {
            false
        }
    }

}