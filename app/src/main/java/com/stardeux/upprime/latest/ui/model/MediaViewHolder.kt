package com.stardeux.upprime.latest.ui.model

import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.latest.view.MediaCell

class MediaViewHolder(private val mediaCell: MediaCell) : RecyclerView.ViewHolder(mediaCell) {

    fun bind(mediaUi: MediaUi) {
        mediaCell.bind(mediaUi)
    }
}