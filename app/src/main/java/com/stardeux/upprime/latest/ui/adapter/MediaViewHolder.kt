package com.stardeux.upprime.latest.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.latest.ui.model.MediaUi
import com.stardeux.upprime.latest.view.MediaItem

class MediaViewHolder(private val mediaItem: MediaItem) : RecyclerView.ViewHolder(mediaItem) {

    fun bind(mediaUi: MediaUi) {
        mediaItem.bind(mediaUi)
    }
}