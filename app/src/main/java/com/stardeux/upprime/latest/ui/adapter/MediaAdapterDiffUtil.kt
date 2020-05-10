package com.stardeux.upprime.latest.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stardeux.upprime.latest.ui.model.DateSeparatorUi
import com.stardeux.upprime.latest.ui.model.MediaUi

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