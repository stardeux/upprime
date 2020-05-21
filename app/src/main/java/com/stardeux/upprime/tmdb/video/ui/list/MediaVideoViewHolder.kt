package com.stardeux.upprime.tmdb.video.ui.list

import androidx.recyclerview.widget.RecyclerView
import com.stardeux.upprime.tmdb.video.ui.MediaVideoItem
import com.stardeux.upprime.tmdb.video.ui.model.MediaVideoUi

class MediaVideoViewHolder(mediaVideoUi: MediaVideoItem) : RecyclerView.ViewHolder(mediaVideoUi) {

    fun bind(mediaVideoUi: MediaVideoUi) {
        (itemView as MediaVideoItem).bind(mediaVideoUi)
    }

}