package com.stardeux.upprime.latest.ui.model

import androidx.recyclerview.widget.DiffUtil

class MediaUiDiffUtil : DiffUtil.ItemCallback<MediaUi>() {

    override fun areItemsTheSame(oldItem: MediaUi, newItem: MediaUi): Boolean {
        return oldItem.amazonId == newItem.amazonId
    }

    override fun areContentsTheSame(oldItem: MediaUi, newItem: MediaUi): Boolean {
        return oldItem == newItem
    }

}