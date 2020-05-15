package com.stardeux.upprime.media.common.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stardeux.upprime.media.common.ui.model.DateSeparatorUi
import com.stardeux.upprime.media.common.ui.model.MediaItemUi

class MediaAdapterDiffUtil : DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem is MediaItemUi && newItem is MediaItemUi) {
            oldItem.amazonId == newItem.amazonId
        } else if (oldItem is DateSeparatorUi && newItem is DateSeparatorUi) {
            oldItem.date == newItem.date
        } else {
            false
        }
    }

    @Suppress("USELESS_CAST")
    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem is MediaItemUi && newItem is MediaItemUi) {
            oldItem as MediaItemUi == newItem as MediaItemUi
        } else if (oldItem is DateSeparatorUi && newItem is DateSeparatorUi) {
            oldItem as DateSeparatorUi == newItem as DateSeparatorUi
        } else {
            false
        }
    }

}