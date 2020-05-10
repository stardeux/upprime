package com.stardeux.upprime.amazon.latest.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.amazon.common.model.ui.MediaUi
import com.stardeux.upprime.amazon.latest.view.MediaItem

class MediaViewHolder(private val mediaItem: MediaItem) : RecyclerView.ViewHolder(mediaItem) {

    fun bind(mediaUi: MediaUi) {
        mediaItem.bind(mediaUi)
    }
}