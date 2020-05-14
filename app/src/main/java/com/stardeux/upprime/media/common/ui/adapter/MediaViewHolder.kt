package com.stardeux.upprime.media.common.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.media.common.view.MediaUi
import com.stardeux.upprime.media.latest.view.MediaItem

class MediaViewHolder(private val mediaItem: MediaItem) : RecyclerView.ViewHolder(mediaItem) {

    fun bind(mediaUi: MediaUi) {
        mediaItem.bind(mediaUi)
    }
}