package com.stardeux.upprime.media.common.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.media.common.ui.model.MediaUi
import com.stardeux.upprime.media.common.view.MediaItemView

class MediaViewHolder(private val mediaItemView: MediaItemView) : RecyclerView.ViewHolder(mediaItemView) {

    fun bind(mediaUi: MediaUi) {
        mediaItemView.bind(mediaUi)
    }
}