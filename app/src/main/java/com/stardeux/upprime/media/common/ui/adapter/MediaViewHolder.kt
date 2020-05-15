package com.stardeux.upprime.media.common.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.media.common.ui.model.MediaItemUi
import com.stardeux.upprime.media.common.view.MediaItemView

class MediaViewHolder(private val mediaItemView: MediaItemView) : RecyclerView.ViewHolder(mediaItemView) {

    fun bind(mediaItemUi: MediaItemUi) {
        mediaItemView.bind(mediaItemUi)
    }
}