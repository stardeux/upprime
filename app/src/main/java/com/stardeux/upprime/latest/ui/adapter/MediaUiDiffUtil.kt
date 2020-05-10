package com.stardeux.upprime.latest.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stardeux.upprime.latest.ui.model.MediaUi

class MediaUiDiffUtil : DiffUtil.ItemCallback<MediaUi>() {

    override fun areItemsTheSame(oldItem: MediaUi, newItem: MediaUi): Boolean {
        return oldItem.amazonId == newItem.amazonId
    }

    override fun areContentsTheSame(oldItem: MediaUi, newItem: MediaUi): Boolean {
        return oldItem == newItem
    }

}