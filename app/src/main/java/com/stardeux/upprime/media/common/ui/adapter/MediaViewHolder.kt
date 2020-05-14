package com.stardeux.upprime.media.common.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.media.common.view.MediaUi
import com.stardeux.upprime.media.latest.view.MediaItemView

class MediaViewHolder(private val mediaItemView: MediaItemView) : RecyclerView.ViewHolder(mediaItemView) {

    fun bind(mediaUi: MediaUi) {
        mediaItemView.bind(mediaUi)
    }
}